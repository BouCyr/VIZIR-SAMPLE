package com.cbo.vizr.reports;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.cbo.vizr.reports.lines.ReportLine;

public class SelectVariable extends Variable{

	private Set<String> pickList;

	public SelectVariable(String name, Collection<ReportLine> lines) {
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

			String value = o.toString();

			if(pickList.contains(value)){
				lines.add(line);
			}
		}
		
		return lines;
	}

	@Override
	public void init(Collection<ReportLine> source) {
		// TODO Auto-generated method stub
		
	}
}