package com.cbo.vizr.builder;

public abstract class  MeasureBase implements Measure {

	protected String name ;
	protected String columnName ;
	
	public MeasureBase(String columName){
		this(columName, columName);
	}
	
	public MeasureBase(String resultName, String columName){
		this.name = resultName;
		this.columnName = columName;
	}

	public String getColumnName() {
		
		return columnName;
	}
	
	public String getName() {
		
		return name;
	}
}
