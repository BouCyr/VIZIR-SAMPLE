package com.cbo.school.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GradesRepo extends CrudRepository<Grade, Integer>{

	List<Grade> findByRegion(Integer region);
	
	Long countByAverageLessThan(Float maxValue);
	
	Long countByAverageGreaterThan(Float minValue);
	
	@Query("select gender, AVG(math), AVG(geo), AVG(history), AVG(sport),AVG(average) from grades group by gender ")
	List<Object> avgByGender();
	
	
}
