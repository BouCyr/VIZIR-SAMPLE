package com.cbo;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbo.school.Grades;
import com.cbo.school.GradesRepo;
import com.cbo.school.dataGen.GradeGenerator;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
	produces=MediaType.APPLICATION_JSON, 
	path="/school")
public class SchoolWs {

	@Autowired 
	private GradesRepo repo;
	
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/")
	public Collection<Grades> get(){
		
		
		Collection<Grades> result = new ArrayList<>();
		repo.findAll().forEach(g -> result.add(g));
			
		return result;
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.POST, 
			path="/")
	public Collection<Grades> create(){
		
		Collection<Grades> result = new ArrayList<>();
		Collection<Grades> generated = GradeGenerator.generateGrades(9000);
		repo.save(generated).forEach(g -> result.add(g));
			
		return result;
	}
}
