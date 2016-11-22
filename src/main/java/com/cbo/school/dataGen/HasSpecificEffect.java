package com.cbo.school.dataGen;

import com.cbo.school.Grades;

public class HasSpecificEffect extends HasEffect{
	int id;

	private Float math;
	private Float geo;
	private Float history;
	private Float sport;

	public HasSpecificEffect(int id, Float math, Float geo, Float history, Float sport) {
		super(id);
		this.math = math;
		this.geo = geo;
		this.history = history;
		this.sport = sport;
	}

	@Override
	public Grades effect(Grades original) {
		original.setMath(original.getMath() * this.math);
		original.setGeo(original.getGeo() * this.geo);
		original.setHistory(original.getHistory() * this.history);
		original.setSport(original.getSport() * this.sport);
		
		return original;
	}

}
