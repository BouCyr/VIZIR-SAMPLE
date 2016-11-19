package com.cbo.rest.charts;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	private String label;
	private List<Integer> data = new ArrayList<>();
	private List<String> backgroundColor = new ArrayList<>();
	private List<String> borderColor = new ArrayList<>();
	private Integer borderWidth = 1;
	
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	public List<String> getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(List<String> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public List<String> getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(List<String> borderColor) {
		this.borderColor = borderColor;
	}
	public Integer getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}
	
	
}
