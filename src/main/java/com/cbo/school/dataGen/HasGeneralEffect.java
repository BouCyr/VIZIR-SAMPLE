package com.cbo.school.dataGen;

import com.cbo.school.Grades;

public class HasGeneralEffect extends HasEffect {

	public float factor;
	
	

	public HasGeneralEffect(int id,float factor) {
		super(id);
		this.factor = factor;
	}



	@Override
	public Grades effect(Grades original) {
		original.setMath(original.getMath() * this.factor);
		original.setMath(original.getGeo() * this.factor);
		original.setMath(original.getHistory() * this.factor);
		original.setMath(original.getSport() * this.factor);
		
		return original;
	}

	
}
