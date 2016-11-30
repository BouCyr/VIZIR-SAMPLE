package com.cbo.school;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbo.school.data.Grade;
import com.cbo.school.data.GradesRepo;
import com.cbo.school.dataGen.GradeGenerator;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
	produces=MediaType.APPLICATION_JSON, 
	path="/school")

public class SchoolWs {

	@Autowired 
	private GradesRepo repo;
	
	@Autowired
	DataSource ds;
	
	@RequestMapping(method=RequestMethod.GET,path="/")
	public Collection<Grade> get(){
		Collection<Grade> result = new ArrayList<>();
		repo.findAll().forEach(g -> result.add(g));
		return result;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{region}")
	public Collection<Grade> getByRegion(@PathVariable("region") Integer region){
		return repo.findByRegion(region);
			
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/")
	public int create(){
		repo.save(GradeGenerator.generateGrades(9000));
		return 9000;
	}
}
