package com.sma.service;

import java.util.List;

import com.sma.model.SubjectPaper;

public interface SubjectPaperService {
	
	SubjectPaper findById(int id);
	
	void saveSubjectPaper(SubjectPaper subjectPaper);
	
	void updateSubjectPaper(SubjectPaper subjectPaper);
		
	void deleteSubjectPaperByCode(String code);

	List<SubjectPaper> findAllSubjectPapers(); 
	
	SubjectPaper findSubjectPaperByCode(String code);
	
	boolean isSubjectPaperUnique(Integer id, String code);
}
