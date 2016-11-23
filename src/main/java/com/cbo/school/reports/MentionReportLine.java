package com.cbo.school.reports;

import com.cbo.school.Result;

public class MentionReportLine {
	
	private Result result;
	private Long count;
	
	public MentionReportLine(Result result, Long count) {
		super();
		this.result = result;
		this.count = count;
	}
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	

}
