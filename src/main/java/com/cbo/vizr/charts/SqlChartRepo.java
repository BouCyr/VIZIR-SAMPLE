package com.cbo.vizr.charts;

import org.springframework.data.repository.CrudRepository;

public interface SqlChartRepo extends CrudRepository<SqlChart, Integer>{

	public SqlChart findByName(String name); 
	
	

}
