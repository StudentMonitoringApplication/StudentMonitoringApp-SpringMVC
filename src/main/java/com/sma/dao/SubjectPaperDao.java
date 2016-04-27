package com.sma.dao;

import java.util.List;

import com.sma.model.SubjectPaper;

public interface SubjectPaperDao {
	SubjectPaper findById(int id);
	
	void saveSubjectPaper(SubjectPaper subjectPaper);
		
	void deleteSubjectPaperByCode(String code);

	List<SubjectPaper> findAllSubjectPapers(); 
	
	SubjectPaper findSubjectPaperByCode(String code);
}
