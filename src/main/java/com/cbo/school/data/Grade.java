package com.cbo.school.data;

import java.text.MessageFormat;

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

@Entity(name="grades")
public class Grade {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Basic
	@NotNull
	private Integer region;
	
	@Basic
	@NotNull
	private Integer school;
	
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
	
	@Min(0)
	@Max(20)
	private Float average;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Result result;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	protected Grade(){
		super();
	}
	
	public Grade(Integer id, Integer region_id, Integer school_id, Float math, Float geo, Float history, Float sport, Gender gender) {
		super();
		this.id = id;
		this.region = region_id;
		this.school = school_id;
		this.math = math;
		this.geo = geo;
		this.history = history;
		this.sport = sport;
		this.gender = gender;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Integer getSchool() {
		return school;
	}

	public void setSchool(Integer school_id) {
		this.school = school_id;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Float getAverage() {
		return average;
	}

	public void setAverage(Float average) {
		this.average = average;
	}

	@Override
	public String toString() {

		return MessageFormat.format("{0} : {1}={2} (maths:{3},history:{4},geo:{5},sport:{6})",
				id,average, result, math, history,geo, sport);
	}
	
	
	
	
}
