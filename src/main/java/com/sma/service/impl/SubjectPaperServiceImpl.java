package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.SubjectPaperDao;
import com.sma.model.SubjectPaper;
import com.sma.service.SubjectPaperService;

@Service("subjectPaperService")
@Transactional
public class SubjectPaperServiceImpl implements SubjectPaperService {
	
	@Autowired
	private SubjectPaperDao dao;
	
	public SubjectPaper findById(int id) {
		return dao.findById(id);
	}

	public void saveSubjectPaper(SubjectPaper subjectPaper) {
		dao.saveSubjectPaper(subjectPaper);
	}

	public void updateSubjectPaper(SubjectPaper subjectPaper) {
		SubjectPaper entity = dao.findById(subjectPaper.getId());
		if(entity!=null){
			entity.setCode(subjectPaper.getCode());
			entity.setFinalScoreAverage(subjectPaper.getFinalScoreAverage());
			entity.setName(subjectPaper.getName());
			entity.setNumber(subjectPaper.getNumber());
			entity.setSubjectId(subjectPaper.getSubjectId());
		}
		
	}

	public void deleteSubjectPaperByCode(String code) {
		dao.deleteSubjectPaperByCode(code);
		
	}

	public List<SubjectPaper> findAllSubjectPapers() {
		return dao.findAllSubjectPapers();
	}

	public SubjectPaper findSubjectPaperByCode(String code) {
		return dao.findSubjectPaperByCode(code);
	}

	public boolean isSubjectPaperUnique(Integer id, String code) {
		SubjectPaper subjectPaper = findSubjectPaperByCode(code);
		return ( subjectPaper == null || ((id != null) && (subjectPaper.getId() == id)));
	}

}
