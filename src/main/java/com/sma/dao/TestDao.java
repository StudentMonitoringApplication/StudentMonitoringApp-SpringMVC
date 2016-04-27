package com.sma.dao;

import java.util.List;

import org.joda.time.LocalDate;

import com.sma.model.Test;

public interface TestDao {
	Test findById(int id);
	
	void saveTest(Test test);
		
	void deleteTestById(int id);

	List<Test> findAllTest(); 
	
	Test findTest(int term, LocalDate year, int test_type_id);
}
