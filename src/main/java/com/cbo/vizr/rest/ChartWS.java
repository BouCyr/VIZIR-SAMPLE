package com.cbo.vizr.rest;

import java.util.Random;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbo.vizr.BaseChart;
import com.cbo.vizr.builder.ChartBuilderFactory;
import com.cbo.vizr.builder.ChartCreationException;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
	produces=MediaType.APPLICATION_JSON, 
	path="/vizr")
public class ChartWS {
	Random r = new Random();
	
	@Autowired
	DataSource ds;
	
	public Double r(){
		return new Integer(  r.nextInt(20) ).doubleValue();
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/chart/{type}")
	public BaseChart testLC(@PathVariable(value="type", required=true  )String type) throws ChartCreationException{

		BaseChart chart = (BaseChart) ChartBuilderFactory.fromTable(ds, "grades").axisX("result").count().chart(type).build();
		
		
		//ds.setBackgroundColor("rgba(54, 162, 235, 0.2)");
		//ds.setBorderColor("rgba(54, 162, 235, 1)");

		
		
		//ds.setBackgroundColor("rgba(255, 99, 132, 0.2)");
		//ds.setBorderColor("rgba(255,99,132,1)");

		return chart;
	}
}
