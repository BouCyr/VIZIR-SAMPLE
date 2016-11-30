package com.cbo.vizr.charts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cbo.vizr.ChartProvider;
import com.cbo.vizr.Data;
import com.cbo.vizr.DataSet;
import com.cbo.vizr.RestChart;
import com.cbo.vizr.charts.palettes.Color;
import com.cbo.vizr.charts.palettes.GradientPalette;

@Component
public class DBChartProvider implements ChartProvider {

	@Autowired
	SqlChartRepo repo;


	@Autowired
	DataSource source;

	public SqlChart findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public RestChart getChart(String name) throws ChartCreationException{
		return fromSQLChart(findByName(name));
	}

	@Override
	public Collection<RestChart> getCharts() throws ChartCreationException {
		ArrayList<RestChart> chartsInDb = new ArrayList<>();


		for(SqlChart sqlChart : repo.findAll()){

			chartsInDb.add(fromSQLChart(sqlChart));
		}



		return chartsInDb;
	}

	@Override
	public String getName() {	
		return "Database store";
	}

	@Override
	public List<String> getChartNames() {
		return repo.findNames();
	}


	public RestChart fromSQLChart(SqlChart chartFromDB) throws ChartCreationException {
		RestChart chart = new RestChart(chartFromDB.getName());
		chart.setType(chartFromDB.getType());

		try {
			chart.setData(treatSQL(chartFromDB.getSql(), chartFromDB.getAxisX(), chartFromDB.getAxisY()));
		} catch (SQLException e) {
			throw new ChartCreationException(e);
		}


		GradientPalette palette = new GradientPalette(new Color(255, 205, 46, 0.8f), new Color(204, 34, 0, 0.8f));
		//PlainPalette palette = new PlainPalette(new Color(244, 121, 32, 0.8f));
		palette.applyPalette(chart.getData().getDatasets().get(0));
		

		return chart;
	}

	private Data treatSQL(String sql, String axisX, List<String> axisY) throws SQLException {

		Data data = new Data();

		if(StringUtils.isEmpty(sql) || StringUtils.isEmpty(axisX) || axisY == null || axisY.size() == 0 ){
			return data;
		}


		Map<String, DataSet> dataSetsByName = new HashMap<>();
		for(String y : axisY){
			DataSet dataSet=  new DataSet();
			dataSet.setLabel(y);

			data.getDatasets().add(dataSet);
			dataSetsByName.put(y, dataSet);
		}

		if(StringUtils.isEmpty(sql)){
			throw new SQLException("SQL cannot be null.");
		}
		Connection con= null;
		PreparedStatement st = null;

		try{
			con = source.getConnection();
			st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while(rs.next()){

				String x = rs.getString(axisX);
				
				
				for(String y : axisY){
					Double yValue = rs.getDouble(y);
					dataSetsByName.get(y).getData().add(yValue);
				}
				data.getLabels().add(x);
				

			}


		}catch(SQLException e) {
			throw e;
		}finally {
			try {
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return data;

	}




}
