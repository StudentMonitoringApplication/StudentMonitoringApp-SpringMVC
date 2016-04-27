package com.sma.dao;

import java.util.List;

import com.sma.model.TestType;

public interface TestTypeDao {

	TestType findById(int id);
	
	void saveTestType(TestType testType);
		
	void deleteTestTypeByCategory(String category);

	List<TestType> findAllTestTypes(); 
	
	TestType findTestTypeByCategory(String category);
}
