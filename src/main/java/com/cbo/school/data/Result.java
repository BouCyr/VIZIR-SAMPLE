package com.cbo.school.data;

public enum Result {

	F,
	E,
	D,
	C,
	B,
	A;
	
	public static Result fromGrades(Grade grd){
		
		
		float avg = (grd.getGeo()+grd.getHistory()+grd.getMath()+grd.getSport())/4.0f;
		
		
		//to 0.01
		avg = (Math.round(100.0f*avg))/100.0f;
		grd.setAverage(avg);
		
		
		if(avg >= 16.0f)
			return Result.A;
		else if(avg >= 14.0f)
			return Result.B;
		else if(avg >= 12.0f)
			return Result.C;
		else if(avg >= 10.0f)
			return Result.D;
		else if(avg >= 8.0f)
			return Result.E;
		else 
			return Result.F;
	}
}
