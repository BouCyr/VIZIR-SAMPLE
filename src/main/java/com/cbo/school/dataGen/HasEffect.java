package com.cbo.school.dataGen;

import com.cbo.school.Grades;

public abstract class HasEffect {

	int id;
	
	public HasEffect(int id){
		super();
		this.id = id;
	}
	
	public abstract Grades effect(Grades original);
	
}
