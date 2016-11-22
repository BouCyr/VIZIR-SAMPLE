package com.cbo.vizr.reports;

import java.lang.reflect.Method;
import java.util.Collection;

import com.cbo.vizr.reports.lines.LineVariable;
import com.cbo.vizr.reports.lines.ReportLine;

public abstract class Variable{

	private String name;

	public Variable(String name, Collection<ReportLine> sourceData){
		super();
		this.name = name;
		init(sourceData);
	}

	private Method getter = null;

	protected Method getGetter(ReportLine line){
		if(getter == null){
			Method[] methods = line.getClass().getMethods();

			for(Method m : methods){
				if(m.isAnnotationPresent(LineVariable.class)){
					LineVariable annotation = m.getAnnotation(LineVariable.class);
					if(annotation.value().equals(this.name)){
						getter = m;
						break;
					}
				}
			}
		}
		return getter;
	}
	
	public abstract void init(Collection<ReportLine> source);

	public abstract Collection<ReportLine> filter(Collection<ReportLine> source);

}

