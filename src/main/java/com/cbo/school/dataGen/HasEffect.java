package com.cbo.school.dataGen;

import com.cbo.school.data.Grade;

public abstract class HasEffect {

	int id;
	
	public HasEffect(int id){
		super();
		this.id = id;
	}
	
	public abstract Grade effect(Grade original);
	
}
