package com.cbo.vizr;

import java.util.Collection;

import com.cbo.vizr.charts.ChartCreationException;

public interface ChartProvider {

	public String getName();
	
	public Collection<RestChart> getCharts() throws ChartCreationException;
}
