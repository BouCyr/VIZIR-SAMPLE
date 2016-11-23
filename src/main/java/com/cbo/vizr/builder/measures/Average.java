package com.cbo.vizr.builder.measures;

import com.cbo.vizr.builder.MeasureBase;

public class Average extends MeasureBase {

	public Average(String field) {
		super(field);
	}




	@Override
	public String toSQLParametrized() {
		return "avg("+columnName+") as "+name;
	}
}
