package com.sma.service;

import java.util.List;

import com.sma.model.SubjectResults;

public interface SubjectResultsService {

	SubjectResults findById(int id);
	
	void saveSubjectResults(SubjectResults subjectResults);
	
	void updateSubjectResults(SubjectResults subjectResults);
	
	void deleteSubjectResultsById(int id);
	
	List<SubjectResults> findAllSubjectResults();
	
	SubjectResults findSubjectResults(int test_id, int student_id);
	
	boolean isSubjectResultsIdUnique(Integer id, int test_id, int student_id);
}
