package com.cbo.vizr.charts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cbo.vizr.ChartProvider;
import com.cbo.vizr.Data;
import com.cbo.vizr.DataSet;
import com.cbo.vizr.RestChart;
import com.cbo.vizr.charts.palettes.Color;
import com.cbo.vizr.charts.palettes.PlainPalette;

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

	
	public RestChart fromSQLChart(SqlChart chartFromDB) throws ChartCreationException {
		RestChart chart = new RestChart(chartFromDB.getName());
		chart.setType(chartFromDB.getType());
		
		try {
			chart.setData(treatSQL(chartFromDB.getSql(), chartFromDB.getAxisX(), chartFromDB.getAxisY()));
		} catch (SQLException e) {
			throw new ChartCreationException(e);
		}


		PlainPalette palette = new PlainPalette(new Color(54, 162, 235));
		//DataPalette palette = new DataPalette(new Color(255, 0, 0), new Color(0, 255, 0));
		
		palette.applyPalette(chart.getData().getDatasets().get(0));

		//ds.setBackgroundColor("rgba(255, 99, 132, 0.2)");
		//ds.setBorderColor("rgba(255,99,132,1)");
		return chart;
	}

	private Data treatSQL(String sql, String axisX, String axisY) throws SQLException {

		Data data = new Data();
		
		DataSet dataSet=  new DataSet();
		dataSet.setLabel(axisY);
		data.getDatasets().add(dataSet);

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
				Double y = rs.getDouble(axisY);
				data.getLabels().add(x);
				dataSet.getData().add(y);

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return data;

	}


}
