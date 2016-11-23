package com.cbo.vizr;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseChart  {

	private ChartType chartType;
	
	private String name;
	private Data data = new Data();
	private Options options = new Options();
	
	protected BaseChart(){
		super();
	}
	public BaseChart(String name){
		super();
		
		this.setChartType(ChartType.BAR);
		this.setName(name);
	}
	



	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	@JsonIgnore
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Options getOptions() {
		return options;
	}
	
	public void setOptions(Options options) {
		this.options = options;
	}
	
	public ChartType getChartType() {
		return chartType ;
	}
	
	public void setChartType(ChartType type){
		this.chartType = type;
	}
	
	public BaseChart(ChartType type){
		super();
		this.chartType = type;
	}
	
	public String getType(){
		return chartType.getType();
	}
	
	public void setType(String type){
		this.chartType = ChartType.parse(type);
	}

}
