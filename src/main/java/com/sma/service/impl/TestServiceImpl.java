package com.sma.service.impl;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.TestDao;
import com.sma.model.Test;
import com.sma.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;
	
	public Test findById(int id) {
		return dao.findById(id);
	}

	public void saveTest(Test test) {
		dao.saveTest(test);
	}

	public void updateTest(Test test) {
		Test entity = dao.findById(test.getId());
		if(entity!=null){
			entity.setTerm(test.getTerm());
			entity.setTest_type_id(test.getTest_type_id());
			entity.setYear(test.getYear());
		}
	}

	public void deleteTestById(int id) {
		dao.deleteTestById(id);
	}

	public List<Test> findAllTests() {
		return dao.findAllTest();
	}

	public Test findTest(int term, LocalDate year, int test_type_id) {
		return dao.findTest(term, year, test_type_id);
	}

	public boolean isTestIdUnique(Integer id, int term, LocalDate year, int test_type_id) {
		Test test = findTest(term, year, test_type_id);
		return ( test == null || ((id != null) && (test.getId() == id)));
	}

}
