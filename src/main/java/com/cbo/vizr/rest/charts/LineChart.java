package com.cbo.vizr.rest.charts;

import com.cbo.vizr.BaseChart;
import com.cbo.vizr.ChartType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LineChart extends BaseChart {

	private String name;
	private Data data = new Data();
	private Options options = new Options();
	
	protected LineChart(){
		super();
	}
	public LineChart(String name){
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
	
	
	
	
	
	
}
