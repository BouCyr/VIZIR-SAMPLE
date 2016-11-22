package com.cbo.vizr.reports;

import java.util.ArrayList;
import java.util.Collection;

import com.cbo.vizr.reports.lines.ReportLine;

public class Report<U extends ReportLine> {

	private Collection<U> sourceData;
	
	public Report(Collection<U> data){
		
	}
	
	public Collection<U> getSourceData(){
		return sourceData;
	}
	
	
	
	
	public Collection<ReportLine> applyVariables(Collection<Variable> variables){
		
		Collection<ReportLine> filtered = new ArrayList<>(sourceData);
		
		for(Variable v : variables  ){
			filtered = v.filter(filtered);
		}
	
		return filtered ;
	}
}
