package com.cbo.vizr;

import com.cbo.vizr.rest.charts.Chart;

public abstract class BaseChart implements Chart {

	private ChartType chartType;
	
	
	@Override
	public ChartType getChartType() {
		return chartType ;
	}
	
	public void setChartType(ChartType type){
		this.chartType = type;
	}
	
	protected BaseChart(){
		super();
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
