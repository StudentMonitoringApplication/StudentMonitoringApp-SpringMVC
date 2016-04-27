package com.sma.service;

import java.util.List;

import org.joda.time.LocalDate;

import com.sma.model.Test;

public interface TestService {

	Test findById(int id);
	
	void saveTest(Test test);
	
	void updateTest(Test test);
	
	void deleteTestById(int id);
	
	List<Test> findAllTests();
	
	Test findTest(int term, LocalDate year, int test_type_id);
	
	boolean isTestIdUnique(Integer id, int term, LocalDate year, int test_type_id);
}
