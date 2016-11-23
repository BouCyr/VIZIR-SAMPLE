package com.cbo.vizr.builder;

import javax.sql.DataSource;

public class ChartBuilderFactory {

	public static <U> ChartBuilder<U> fromTable(DataSource ds, String tableName){
		return new ChartBuilder<U>(ds, tableName);
	}
}
