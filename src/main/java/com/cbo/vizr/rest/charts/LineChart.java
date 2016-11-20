package com.cbo.vizr.rest.charts;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LineChart {

	private String type = "line";
	private String name;
	private Data data = new Data();
	private Options options = new Options();
	
	protected LineChart(){
		super();
	}
	public LineChart(String name){
		super();
		this.setName(name);
	}
	


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
