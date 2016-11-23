package com.cbo.school;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cbo.school.reports.MentionReport;

public interface GradesRepo extends CrudRepository<Grades, Integer>, JpaSpecificationExecutor<MentionReport>{

	List<Grades> findByRegion(Integer region);
	
	
	@Query(value = "select new com.cbo.school.reports.MentionReport(g.result, count(g)) from grades g group by result")
	public List<MentionReport> agregateMentions();
}
