package com.cbo.school;

public enum Result {

	FAILED,
	REPECHAGE,
	OK,
	GOOD,
	EXCELLENT,
	OUTSTANDING;
	
	public static Result fromGrades(Grades grd){
		
		
		float avg = (grd.getGeo()+grd.getHistory()+grd.getMath()+grd.getSport())/4.0f;
		
		
		//to 0.01
		avg = (Math.round(100.0f*avg))/100.0f;
		grd.setAverage(avg);
		
		
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
