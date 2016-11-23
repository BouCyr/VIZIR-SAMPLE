package com.cbo.school.dataGen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.cbo.school.Gender;
import com.cbo.school.Grades;
import com.cbo.school.Result;

public class GradeGenerator {


	public static Collection<Grades> generateGrades(int nb){

		

		List<HasEffect> regions = new ArrayList<>();
		{
			int i =0;
			regions.add(new HasGeneralEffect(i++, 0.9f));
			regions.add(new HasGeneralEffect(i++, 1.0f));
			regions.add(new HasGeneralEffect(i++, 1.1f));
		}



		Map<HasEffect, List<HasEffect>> schoolsByRegion = new HashMap<>();
		{
			for(int i = 0; i < (nb/100) ; i++){
				
				HasEffect region = regions.get(i % regions.size());
				HasEffect school = randomEffect(i);
				if(!schoolsByRegion.containsKey(region))
					schoolsByRegion.put(region, new ArrayList<>());
				
				schoolsByRegion.get(region).add(school);
				
			}
		}
		
		List<HasEffect> genders = new ArrayList<>();
		{
			//dérangeant, mais représente la culture ambiante...
			genders.add(new HasSpecificEffect(1, 1.1f, 0.9f, 0.9f, 1.1f));
			genders.add(new HasSpecificEffect(2, 0.9f, 1.1f, 1.1f, 0.9f));
			
			
		}
		
		Collection<Grades> allGrades = new ArrayList<>();
		
		for(int i = 0 ; i < nb ; i++){
			
			HasEffect region = random(regions);
			HasEffect school = random(schoolsByRegion.get(region));
			HasEffect gender = random(genders);
			
			
			Grades baseStudent = generateStudent(i, region, school, gender);//depends only of external factors
			//i.e every male student of a specific shcool will hacve the same grades
			
			
			Grades specificStudent = randomEffect(i).effect(baseStudent);//the specific result, representing
			//the students specific performances on the exam day.

			//round to nearest 0.5
			round(specificStudent);
			
			//compute its result from the grades
			specificStudent.setResult(Result.fromGrades(specificStudent));
			
			allGrades.add(specificStudent);
		}

		return allGrades;
	}


	private static void round(Grades specificStudent) {
		specificStudent.setMath(semiRound(specificStudent.getMath()));
		specificStudent.setGeo(semiRound(specificStudent.getGeo()));
		specificStudent.setHistory(semiRound(specificStudent.getHistory()));
		specificStudent.setSport(semiRound(specificStudent.getSport()));
	}


	private static Grades generateStudent(int id, HasEffect region, HasEffect school, HasEffect gender ){
		
		Grades grades = new Grades(id ,0,0,11.0f,11.0f,11.0f,11.0f,Gender.MALE);
		
		//effect of origin region
		grades = region.effect(grades);
		grades.setRegion(region.id);
		
		//effect of school
		grades = school.effect(grades);
		grades.setSchool(school.id);
		
		//effect of gender
		grades = gender.effect(grades);
		grades.setGender(gender.id == 1 ?Gender.MALE:Gender.FEMALE);
		
		

		
		return grades;
	}
	
	
	private static float semiRound(float f){
		
		f = (Math.round(2.0*f))/2.0f;
		
		f = f> 20.0f ? 20.0f : f;
		f= f<0.0f ? 0.0f : f;
		
		return f;
		
	}
	
	private static HasEffect random(List<HasEffect> effects){

		
		return effects.get(r.nextInt(effects.size()));
	}
	
	private static HasSpecificEffect randomEffect(int i) {
		float global = randomFactor();
		return randomEffect(i, global);
	}
	
	private static HasSpecificEffect randomEffect(int i, float global) {
		return new HasSpecificEffect(i,
				global*randomFactor(), 
				global*randomFactor(), 
				global*randomFactor(),
				global*randomFactor());
	}
	
	
	private static Random r = new Random();
	private static float randomFactor(){
		
		//between 0.8f & 1.2f, with quadratic.
		
		float f = r.nextFloat();//[0,1], linear
		f = f*f;//[0,1] skewed toward 0
		f = r.nextBoolean()? -f : f; //[-1,1] skewed toward 0
		f = f/5.0f; //[-.2,.2] skewed toward 0
		f = 1.0f + f;//[0.8,1.2] skewed toward 1.0
		
		return f;
		
		
		
	}


}
