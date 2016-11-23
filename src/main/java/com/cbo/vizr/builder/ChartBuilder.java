package com.cbo.vizr.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.cbo.vizr.BaseChart;
import com.cbo.vizr.ChartType;
import com.cbo.vizr.DataSet;
import com.cbo.vizr.builder.measures.Average;
import com.cbo.vizr.builder.measures.Count;
import com.cbo.vizr.builder.measures.Max;
import com.cbo.vizr.builder.measures.Min;
import com.cbo.vizr.builder.measures.Sum;

public class ChartBuilder<U> {


	private DataSource ds;	
	private String tableName;

	private String axisX = null;;
	private String dataset = null;

	private List<MeasureBase> axisY = new ArrayList<>();

	private ChartType type ;

	public ChartBuilder(DataSource ds, String tableName) {
		this.ds = ds;
		this.tableName = tableName;
	}

	public ChartBuilder<U> chart(ChartType type){
		this.type = type;
		return this;
	}
	
	public ChartBuilder<U> chart(String type){
		this.type = ChartType.parse(type);
		return this;
	}

	public ChartBuilder<U> dataSet(String field){
		dataset = field;
		return this;
	}

	public ChartBuilder<U> axisX(String field){
		axisX = field ;
		return this;
	}

	public ChartBuilder<U> count(){
		axisY.add(new Count());
		return this;
	}

	public ChartBuilder<U> sum(String... fields){
		
		Arrays.asList(fields).forEach(field -> axisY.add(new Sum(field)) );
		return this;
	}

	public ChartBuilder<U> avg(String fields){
		Arrays.asList(fields).forEach(field -> axisY.add(new Average(field)) );
		return this;
	}

	public ChartBuilder<U> min(String fields){
		Arrays.asList(fields).forEach(field -> axisY.add(new Min(field)) );
		return this;
	}

	public ChartBuilder<U> max(String fields){
		Arrays.asList(fields).forEach(field -> axisY.add(new Max(field)) );
		return this;
	}

	public BaseChart build() throws ChartCreationException{

		if(axisX == null){
			throw new ChartCreationException("You must define an X axis.");
		}
		
		if(axisY.size() == 0){
			throw new ChartCreationException("You must define at least one field to display on Y axis.");
		}
		
		if(dataset !=null && axisY.size() >1){
			throw new ChartCreationException("You cannot measure several fields on several datasets ; either define no dataset (measurements will be displayed as dataset) or select only one measure.");
		}
		
		
		String fullSQL = buildSQL();

		System.err.println(fullSQL);

		List<List<Object>> lines = executeSql(fullSQL);

		
		if(dataset != null ){
			return buildChartUsingDataSets(lines);
		}else{
			return buildChartUsingYValues(lines);
		}
		
	}



	private BaseChart buildChartUsingYValues(List<List<Object>> lines) {
		//pour chaque ligne, on a un X, et un ou plusieurs Y qui iront chacun dans leur dataset
		
		HashMap<String, List<Double>> datasetsData = new HashMap<>();
		List<String> xValues = new ArrayList<>();
		for(List<Object> line : lines){
			xValues.add( line.get(0).toString() );
			
			for(int i=0; i < axisY.size(); i++){
				String dataSetName = axisY.get(0).name ;
				if(!datasetsData.containsKey(dataSetName)){
					datasetsData.put(dataSetName, new ArrayList<>());
				}
				
				datasetsData.get(dataSetName).add( Double.valueOf( line.get(i+1).toString() ));
			}
		}

		BaseChart chart = new BaseChart("test");
		chart.setType(type.getType());
		
		chart.getData().setLabels(xValues);
		
		for(String dataSetName : datasetsData.keySet()){
			DataSet ds = new DataSet();
			ds.setLabel(dataSetName);
			ds.setData(datasetsData.get(dataSetName));
			chart.getData().getDatasets().add(ds);
		}
		
		return chart;
	}

	private BaseChart buildChartUsingDataSets(List<List<Object>> lines) {
		
		//pour chaque ligne, on a un X, un Y et un dataset
		
		HashSet<String> xValues = new HashSet<>();
		lines.forEach(o -> xValues.add(o.get(0).toString()));
		
		
		Map<String, List<Double>> datasets = new HashMap<>();
		lines.forEach(o -> datasets.put(o.get(1).toString(), new ArrayList<Double>() ));
		
		for(List<Object> line : lines){
			String xValue = line.get(0).toString();
		}
		
		
		BaseChart chart = new BaseChart("test");
		throw new RuntimeException();//TODO
		
		
	}

	private List<List<Object>> executeSql(String fullSQL) throws ChartCreationException  {
		Connection con =null;
		PreparedStatement st = null;

		try{
			con = this.ds.getConnection();
			st = con.prepareStatement(fullSQL);

			ResultSet rs = st.executeQuery();
			int columnsNb = 1 + axisY.size();

			List<String> columns = new ArrayList<>();
			for(int i = 0 ; i < columnsNb ; i++)
			{
				columns.add(rs.getMetaData().getColumnLabel(i+1));
			}
			List<List<Object>> lines = new ArrayList<>();


			while(rs.next()){
				ArrayList<Object> line = new ArrayList<>();
				lines.add(line);
				for(int i = 0 ; i < columnsNb ; i++)
				{
					line.add(rs.getObject(i+1));
				}
			}
			return lines;
		}catch(SQLException e){
			throw new ChartCreationException(e);
		}
		finally {
			try{
				if(st != null)
					st.close();
				if(con != null)
					con.close();
			}catch(SQLException e){
				throw new ChartCreationException(e);
			}
		}

	}

	private String buildSQL() {
		StringBuffer sql = new StringBuffer();


		List<String> groupBys = new ArrayList<>();

		groupBys.add(axisX);
		if(dataset != null)
			groupBys.add(dataset);

		sql.append("SELECT ");
		for(int  i = 0; i< groupBys.size() ; i++){
			if(i!=0)
				sql.append(", ");

			sql.append(groupBys.get(i));

		}


		for(int i = 0 ; i < axisY.size() ; i++){

			MeasureBase measure = axisY.get(i);
			sql.append(", ");
			sql.append(measure.toSQLParametrized());

		}

		sql.append(" FROM ").append(tableName).append(" ");

		for(int  i = 0; i< groupBys.size() ; i++){
			if(i!=0)
				sql.append(", ");
			else
				sql.append("group by ");

			sql.append(groupBys.get(i));
		}

		sql.append(";");

		return sql.toString();
	}
}
