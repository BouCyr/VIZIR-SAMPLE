package com.cbo.html.school;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Grades {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Basic
	@NotNull
	private Integer region_id;
	
	@Basic
	@NotNull
	private Integer school_id;
	
	@Min(0)
	@Max(20)
	private Float math;
	@Min(0)
	@Max(20)
	private Float geo;
	@Min(0)
	@Max(20)
	private Float history;
	@Min(0)
	@Max(20)
	private Float sport;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Result result;

	public Grades(Integer id, Integer region_id, Integer school_id, Float math, Float geo, Float history, Float sport) {
		super();
		this.id = id;
		this.region_id = region_id;
		this.school_id = school_id;
		this.math = math;
		this.geo = geo;
		this.history = history;
		this.sport = sport;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Float getMath() {
		return math;
	}

	public void setMath(Float math) {
		this.math = math;
	}

	public Float getGeo() {
		return geo;
	}

	public void setGeo(Float geo) {
		this.geo = geo;
	}

	public Float getHistory() {
		return history;
	}

	public void setHistory(Float history) {
		this.history = history;
	}

	public Float getSport() {
		return sport;
	}

	public void setSport(Float sport) {
		this.sport = sport;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
	
	
	
	
}
