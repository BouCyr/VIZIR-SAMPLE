package com.cbo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbo.school.Grades;
import com.cbo.school.GradesRepo;
import com.cbo.school.dataGen.GradeGenerator;
import com.cbo.school.reports.MentionReport;
import com.cbo.vizr.builder.ChartBuilder;
import com.cbo.vizr.builder.ChartBuilderFactory;
import com.cbo.vizr.builder.ChartCreationException;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
	produces=MediaType.APPLICATION_JSON, 
	path="/school")
public class SchoolWs {

	@Autowired 
	private GradesRepo repo;
	
	@Autowired
	DataSource ds;
	
	@RequestMapping(method=RequestMethod.GET,path="/mentions")
	public Collection<MentionReport> getMention() throws ChartCreationException{
		
		//select result, count(1) from grades group by result;
		ChartBuilderFactory.fromTable(ds, "grades").axisX("result").count().build();
		
		/*
select region_id, 
result,avg(math),avg(geo),avg(history),avg(sport),sum(sport), count(1) from grades group by region_id, result ;

		 */
		
		ChartBuilderFactory.fromTable(ds, "grades")
			.axisX("region")
			.avg("math")
			.avg("geo")
			.avg("history")
			.avg("sport")
			.build();
		
		return repo.agregateMentions();
		
		
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/")
	public Collection<Grades> get(){
		Collection<Grades> result = new ArrayList<>();
		repo.findAll().forEach(g -> result.add(g));
		return result;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{region}")
	public Collection<Grades> getByRegion(@PathVariable("region") Integer region){
		return repo.findByRegion(region);
			
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/")
	public int create(){
		repo.save(GradeGenerator.generateGrades(9000));
		return 9000;
	}
}
