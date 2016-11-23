package com.cbo.vizr.builder.measures;

import com.cbo.vizr.builder.MeasureBase;

public class Sum extends MeasureBase{

	public Sum(String resultName, String columName) {
		super(resultName, columName);
	}

	public Sum(String columName) {
		super(columName, columName);
	}


	@Override
	public String toSQLParametrized() {
		return "max("+columnName+") as "+name;
	}

}
