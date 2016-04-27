package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.TestTypeDao;
import com.sma.model.TestType;
import com.sma.service.TestTypeService;

@Service("testTypeService")
@Transactional
public class TestTypeServiceImpl implements TestTypeService {

	@Autowired
	private TestTypeDao dao;
	
	public TestType findById(int id) {
		return dao.findById(id);
	}

	public void saveTestType(TestType testType) {
		dao.saveTestType(testType);
	}

	public void updateTestType(TestType testType) {
		TestType entity = dao.findById(testType.getId());
		if(entity!=null){
			entity.setCategory(testType.getCategory());
			entity.setName(testType.getName());
		}
	}

	public void deleteTestTypeById(String category) {
		dao.deleteTestTypeByCategory(category);
	}

	public List<TestType> findAllTestTypes() {
		return dao.findAllTestTypes();
	}

	public TestType findTestTypeByCategory(String category) {
		return dao.findTestTypeByCategory(category);
	}

	public boolean isTestTypeIdUnique(Integer id, String category) {
		TestType testType = findTestTypeByCategory(category);
		return ( testType == null || ((id != null) && (testType.getId() == id)));
	}

}
