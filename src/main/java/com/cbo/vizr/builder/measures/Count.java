package com.cbo.vizr.builder.measures;

import com.cbo.vizr.builder.MeasureBase;

public class Count extends MeasureBase {

	public Count() {
		super("count","1");
	}

	public Count(String field) {
		super(field);
	}


	@Override
	public String toSQLParametrized() {
		return "count(1)";
	}
}
