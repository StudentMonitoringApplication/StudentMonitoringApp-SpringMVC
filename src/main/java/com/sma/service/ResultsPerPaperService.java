package com.sma.service;

import java.util.List;

import com.sma.model.ResultsPerPaper;

public interface ResultsPerPaperService {

	ResultsPerPaper findById(int id);
	
	void saveResultsPerPaper(ResultsPerPaper resultsPerPaper);
	
	void updateResultsPerPaper(ResultsPerPaper resultsPerPaper);
	
	void deleteResultsPerPaperById(int id);
	
	List<ResultsPerPaper> findAllResultsPerPapers();
	
	ResultsPerPaper findResultsPerPaper(int test_id, int student_id);
	
	boolean isResultsPerPaperIdUnique(Integer id, int test_id, int student_id);
}
