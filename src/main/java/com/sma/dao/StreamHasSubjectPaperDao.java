package com.sma.dao;

import java.util.List;

import com.sma.model.StreamHasSubjectPaper;

public interface StreamHasSubjectPaperDao {
	
	StreamHasSubjectPaper findById(int id);
	
	void saveStreamHasSubjectPaper(StreamHasSubjectPaper streamHasSubjectPaper);

	void deleteStreamHasSubjectPaper(int id);
	
	List<StreamHasSubjectPaper> findAllStreamHasSubjectPaper();
	
	StreamHasSubjectPaper findStreamHasSubjectPaper(int stream_id, int subject_paper_id);
}
