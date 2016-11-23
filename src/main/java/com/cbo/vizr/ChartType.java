package com.cbo.vizr;

public enum ChartType {
	BAR("bar"),
	PIE("pie"), 
	LINE("line");

	private String type;

	private ChartType(String type) {
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}

	public static ChartType parse(String value) {

		for(ChartType entry : ChartType.values()){
			if(entry.getType().equals(value))
				return entry;
		}
		throw new IllegalArgumentException(value+" is not a valid name for ChartType.");
	}
	
	@Override
	public String toString() {
		return getType();
	}
}
