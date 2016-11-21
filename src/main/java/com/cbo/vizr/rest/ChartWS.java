package com.cbo.vizr.rest;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbo.vizr.rest.charts.DataSet;
import com.cbo.vizr.rest.charts.LineChart;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
	produces=MediaType.APPLICATION_JSON, 
	path="/vizr")
public class ChartWS {

	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/chart")
	public LineChart testLC(){


		LineChart chart = new LineChart("TEST");
		chart.setType("bar");

		chart.getData().getLabels().addAll(Arrays.asList(new String[]{"Red", "Blue", "Yellow", "Green", "Purple", "Orange"}));

		DataSet ds = new DataSet();
		ds.setLabel("HISTOIRE");
		ds.setBackgroundColor("rgba(54, 162, 235, 0.2");
				

		ds.setBorderColor("rgba(54, 162, 235, 1)");

		ds.setData(new ArrayList<>());
		ds.getData().addAll(Arrays.asList(new Integer[]{12, 19, 3, 5, 2, 3}));

		chart.getData().getDatasets().add(ds);
		
		
		ds = new DataSet();
		ds.setLabel("MATHS");
		ds.setBackgroundColor("rgba(255, 99, 132, 0.2)");

		ds.setBorderColor("rgba(255,99,132,1)");

		ds.setData(new ArrayList<>());
		ds.getData().addAll(Arrays.asList(new Integer[]{12, 19, 3, 5, 2, 3}));

		chart.getData().getDatasets().add(ds);
		return chart;
	}
}
