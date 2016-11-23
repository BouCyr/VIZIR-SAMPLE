package com.cbo.vizr.builder;

public interface Measure {

	public String getColumnName();
	
	public String getName();
	
	public String toSQLParametrized() ;
}
