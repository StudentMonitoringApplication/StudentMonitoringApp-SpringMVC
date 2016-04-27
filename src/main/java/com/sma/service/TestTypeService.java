package com.sma.service;

import java.util.List;

import com.sma.model.TestType;

public interface TestTypeService {
	TestType findById(int id);
	
	void saveTestType(TestType testType);
	
	void updateTestType(TestType testType);
	
	void deleteTestTypeById(String category);
	
	List<TestType> findAllTestTypes();
	
	TestType findTestTypeByCategory(String category);
	
	boolean isTestTypeIdUnique(Integer id, String category);
}
