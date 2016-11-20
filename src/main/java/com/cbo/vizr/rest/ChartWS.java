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
				/*.addAll(Arrays.asList(new String[]{"rgba(255, 99, 132, 0.2)",
				"rgba(54, 162, 235, 0.2)",
				"rgba(255, 206, 86, 0.2)",
				"rgba(75, 192, 192, 0.2)",
				"rgba(153, 102, 255, 0.2)",
		"rgba(255, 159, 64, 0.2)"}));*/

		ds.setBorderColor("rgba(54, 162, 235, 1)");
		
				/*new ArrayList<>());
		ds.getBorderColor().addAll(Arrays.asList(new String[] {"rgba(255,99,132,1)",
				"rgba(54, 162, 235, 1)",
				"rgba(255, 206, 86, 1)",
				"rgba(75, 192, 192, 1)",
				"rgba(153, 102, 255, 1)",
		"rgba(255, 159, 64, 1)"}));*/

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
