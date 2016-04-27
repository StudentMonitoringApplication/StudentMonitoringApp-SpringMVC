package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.SubjectResultsDao;
import com.sma.model.SubjectResults;
import com.sma.service.SubjectResultsService;

@Service("subjectResultsService")
@Transactional
public class SubjectResultsServiceImpl implements SubjectResultsService {

	@Autowired
	private SubjectResultsDao dao;
	
	public SubjectResults findById(int id) {
		return dao.findById(id);
	}

	public void saveSubjectResults(SubjectResults subjectResults) {
		dao.saveSubjectResults(subjectResults);
	}

	public void updateSubjectResults(SubjectResults subjectResults) {
		SubjectResults entity = dao.findById(subjectResults.getId());
		if(entity!=null){
			entity.setClass_id(subjectResults.getClass_id());
			entity.setDate(subjectResults.getDate());
			entity.setMarks(subjectResults.getMarks());
			entity.setStudent_id(subjectResults.getStudent_id());
			entity.setSubject_id(subjectResults.getSubject_id());
			entity.setTest_id(subjectResults.getTest_id());
		}
	}

	public void deleteSubjectResultsById(int id) {
		dao.deleteSubjectResultsById(id);
	}

	public List<SubjectResults> findAllSubjectResults() {
		return dao.findAllSubjectResults();
	}

	public SubjectResults findSubjectResults(int test_id, int student_id) {
		return dao.findSubjectResults(test_id, student_id);
	}

	public boolean isSubjectResultsIdUnique(Integer id, int test_id, int student_id) {
		SubjectResults subjectResults = findSubjectResults(test_id, student_id);
		return ( subjectResults == null || ((id != null) && (subjectResults.getId() == id)));
	}

}
