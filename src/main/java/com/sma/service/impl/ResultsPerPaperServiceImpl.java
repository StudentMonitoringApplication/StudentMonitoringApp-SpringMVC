package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.ResultsPerPaperDao;
import com.sma.model.ResultsPerPaper;
import com.sma.service.ResultsPerPaperService;

@Service("resultsPerPaperService")
@Transactional
public class ResultsPerPaperServiceImpl implements ResultsPerPaperService{

	@Autowired
	private ResultsPerPaperDao dao;
	
	public ResultsPerPaper findById(int id) {
		return dao.findById(id);
	}

	public void saveResultsPerPaper(ResultsPerPaper resultsPerPaper) {
		dao.saveResultsPerPaper(resultsPerPaper);
	}

	public void updateResultsPerPaper(ResultsPerPaper resultsPerPaper) {
		ResultsPerPaper entity = dao.findById(resultsPerPaper.getId());
		if(entity!=null){
			entity.setClass_id(resultsPerPaper.getClass_id());
			entity.setDate(resultsPerPaper.getDate());
			entity.setMarks(resultsPerPaper.getMarks());
			entity.setStudent_id(resultsPerPaper.getStudent_id());
			entity.setSubject_paper_id(resultsPerPaper.getSubject_paper_id());
			entity.setTest_id(resultsPerPaper.getTest_id());
		}
	}

	public void deleteResultsPerPaperById(int id) {
		dao.deleteResultsPerPaperById(id);
	}

	public List<ResultsPerPaper> findAllResultsPerPapers() {
		return dao.findAllResultsPerPaper();
	}

	public ResultsPerPaper findResultsPerPaper(int test_id, int student_id) {
		return dao.findResultsPerPaper(test_id, student_id);
	}

	public boolean isResultsPerPaperIdUnique(Integer id, int test_id, int student_id) {
		ResultsPerPaper resultsPerPaper = findResultsPerPaper(test_id, student_id);
		return ( resultsPerPaper == null || ((id != null) && (resultsPerPaper.getId() == id)));
	}

}
