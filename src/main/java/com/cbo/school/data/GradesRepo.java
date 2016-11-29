package com.cbo.school.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GradesRepo extends CrudRepository<Grade, Integer>{

	List<Grade> findByRegion(Integer region);
	
	
}
