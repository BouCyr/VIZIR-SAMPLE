package com.cbo.vizr.reports;

import java.lang.reflect.Method;
import java.util.Collection;

import com.cbo.vizr.reports.lines.LineVariable;


public abstract class Report<U> {

	
	public Report(){
		
		if(!this.getClass().isAnnotationPresent(LineType.class))
			throw new RuntimeException("REPORTS MUST BE ANNOTATED WITH @ReportType.");
		
		Class<?> reportType = this.getClass().getAnnotation(LineType.class).value();
		
		
		for(Method m : reportType.getMethods()){
			if(m.isAnnotationPresent(LineVariable.class)){
				
			}
		}
		
		
	}
	
	public abstract Collection<U> getData(Collection<Variable<U>> variables);
	
	
	
	

}
