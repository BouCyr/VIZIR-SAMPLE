package com.cbo.vizr.reports;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import com.cbo.vizr.reports.lines.ReportLine;

public class RangeVariable extends Variable{

	private Double asboluteMin;
	private Double absoluteMax;

	private Double selectedMin;
	private Double selectedMax;

	public RangeVariable(String name, Collection<ReportLine> lines) {
		super(name, lines);
	}

	@Override
	public Collection<ReportLine> filter(Collection<ReportLine> source) {

		ArrayList<ReportLine> lines = new ArrayList<>();
		for(ReportLine line : source){
			Object o;
			try {
				o = this.getGetter(line).invoke(line);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				continue;
			}
			if(o==null){
				continue;
			}

			Double number = Double.valueOf(o.toString());

			if(number >= selectedMin && number <= selectedMax){
				lines.add(line);
			}
		}
		return lines;

	}

	@Override
	public void init(Collection<ReportLine> source) {
		
		for(ReportLine line : source){
			Object o;
			try {
				o = this.getGetter(line).invoke(line);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				continue;
			}
			if(o==null){
				continue;
			}

			Double number = Double.valueOf(o.toString());
			if(number < asboluteMin)
				asboluteMin = number;
			if(number>absoluteMax)
				absoluteMax=number;
		}
		
	}
}