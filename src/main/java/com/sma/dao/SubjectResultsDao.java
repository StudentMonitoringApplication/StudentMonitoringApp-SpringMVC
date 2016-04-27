package com.sma.dao;

import java.util.List;

import com.sma.model.SubjectResults;

public interface SubjectResultsDao {
	SubjectResults findById(int id);
	
	void saveSubjectResults(SubjectResults subjectResults);
		
	void deleteSubjectResultsById(int id);

	List<SubjectResults> findAllSubjectResults(); 
	
	SubjectResults findSubjectResults(int test_id, int student_id);
}
