package com.cbo.vizr.reports;

import java.lang.reflect.Method;
import java.util.Collection;

import com.cbo.vizr.reports.lines.LineVariable;

public abstract class Variable<U>{

	private String name;

	public Variable(String name, Collection<U> sourceData){
		super();
		this.name = name;
		init(sourceData);
	}

	private Method getter = null;

	protected Method getGetter(U line){
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
	
	public abstract void init(Collection<U> source);

	public abstract Collection<U> filter(Collection<U> source);

}

