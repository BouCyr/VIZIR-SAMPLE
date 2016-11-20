package com.cbo.vizr;

public enum ChartType {
	LINE("line");

	private String type;

	private ChartType(String type) {
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
}
