package com.cbo.vizr.builder.measures;

import com.cbo.vizr.builder.MeasureBase;

public class Max extends MeasureBase {
	public Max(String field) {
		super(field);
	}



	@Override
	public String toSQLParametrized() {
		return "max("+columnName+") as "+name;
	}

}
