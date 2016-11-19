package com.cbo.rest;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbo.rest.charts.DataSet;
import com.cbo.rest.charts.LineChart;

@RestController()
public class ChartService {

	@RequestMapping(consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON, method=RequestMethod.GET, path="/lineChart")
	public LineChart testLC(){
		
		
		LineChart chart = new LineChart();
		chart.setType("bar");
		
		chart.getData().getLabels().addAll(Arrays.asList(new String[]{"Red", "Blue", "Yellow", "Green", "Purple", "Orange"}));

		DataSet ds = new DataSet();
		ds.getBackgroundColor().addAll(Arrays.asList(new String[]{"rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)"}));
		
		ds.setBorderColor(new ArrayList<>());
		ds.getBorderColor().addAll(Arrays.asList(new String[] {"rgba(255,99,132,1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)"}));
		
		ds.setData(new ArrayList<>());
		ds.getData().addAll(Arrays.asList(new Integer[]{12, 19, 3, 5, 2, 3}));
		
		chart.getData().getDatasets().add(ds);
		return chart;
	}
}
