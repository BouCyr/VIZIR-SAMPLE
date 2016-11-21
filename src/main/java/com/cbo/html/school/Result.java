package com.cbo.html.school;

public enum Result {

	FAILED,
	REPECHAGE,
	OK,
	GOOD,
	EXCELLENT,
	OUTSTANDING;
	
	public static Result fromGrades(Grades grd){
		
		
		float avg = (grd.getGeo()+grd.getHistory()+grd.getMath()+grd.getSport())/5.0f;
		
		if(avg >= 16.0f)
			return Result.OUTSTANDING;
		else if(avg >= 14.0f)
			return Result.EXCELLENT;
		else if(avg >= 12.0f)
			return Result.GOOD;
		else if(avg >= 10.0f)
			return Result.OK;
		else if(avg >= 9.5f)
			return Result.REPECHAGE;
		else 
			return Result.FAILED;
	}
}
