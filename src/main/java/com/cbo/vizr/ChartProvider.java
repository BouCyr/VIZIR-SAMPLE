package com.cbo.vizr;

import java.util.Collection;
import java.util.List;

import com.cbo.vizr.charts.ChartCreationException;

public interface ChartProvider {

	public String getName();

	public List<String> getChartNames();
	
	public RestChart getChart(String name) throws ChartCreationException;
	
	public Collection<RestChart> getCharts() throws ChartCreationException;
}
