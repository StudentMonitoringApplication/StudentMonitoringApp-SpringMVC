package com.sma.dao;

import java.util.List;

import com.sma.model.ResultsPerPaper;

public interface ResultsPerPaperDao {
	ResultsPerPaper findById(int id);
	
	void saveResultsPerPaper(ResultsPerPaper resultsPerPaper);
		
	void deleteResultsPerPaperById(int id);

	List<ResultsPerPaper> findAllResultsPerPaper(); 
	
	ResultsPerPaper findResultsPerPaper(int test_id, int student_id);
}
