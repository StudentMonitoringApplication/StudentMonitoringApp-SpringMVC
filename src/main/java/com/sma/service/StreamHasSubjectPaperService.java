package com.sma.service;

import java.util.List;

import com.sma.model.StreamHasSubjectPaper;

public interface StreamHasSubjectPaperService {

	StreamHasSubjectPaper findById(int id);
	
	void saveStreamHasSubjectPaper(StreamHasSubjectPaper streamHasSubjectPaper);
	
	void updateStreamHasSubjectPaper(StreamHasSubjectPaper streamHasSubjectPaper);
	
	void deleteStreamHasSubjectPaperById(int id);
	
	List<StreamHasSubjectPaper> findAllStreamHasSubjectPapers();
	
	StreamHasSubjectPaper findStreamHasSubjectPaper(String streamId, String subject_paper_id);
	
	boolean isStreamHasSubjectPaperIdUnique(Integer id, String streamId, String subject_paper_id);

}
