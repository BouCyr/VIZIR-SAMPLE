package com.cbo.vizr.builder.measures;

import com.cbo.vizr.builder.MeasureBase;

public class Min extends MeasureBase {
	public Min(String field) {
		super(field);
	}




	@Override
	public String toSQLParametrized() {
		return "max("+columnName+") as "+name;
	}
}

