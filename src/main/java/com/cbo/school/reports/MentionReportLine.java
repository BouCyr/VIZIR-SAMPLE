package com.cbo.school.reports;

import com.cbo.school.Gender;
import com.cbo.school.Result;
import com.cbo.vizr.reports.lines.Aggregation;
import com.cbo.vizr.reports.lines.LineData;
import com.cbo.vizr.reports.lines.LineVariable;

public class MentionReportLine {
	
	private Integer region;
	
	private Integer school;
	
	private Gender gender;
	
	private Result result;

	public MentionReportLine(Integer region_id, Integer school_id, Gender gender, Result result) {
		super();
		this.region = region_id;
		this.school = school_id;
		this.gender = gender;
		this.result = result;
	}

	@LineVariable("region")
	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	@LineVariable("établissement")
	public Integer getSchool() {
		return school;
	}

	public void setSchool(Integer school) {
		this.school = school;
	}

	@LineVariable("gender")
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@LineData(name="Mention", aggregation=Aggregation.SUM)
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	

}
