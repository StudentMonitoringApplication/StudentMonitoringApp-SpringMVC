package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.StreamHasSubjectPaperDao;
import com.sma.model.StreamHasSubjectPaper;
import com.sma.service.StreamHasSubjectPaperService;

@Service("streamHasSubjectPaperService")
@Transactional
public class StreamHasSubjectPaperServiceImpl implements StreamHasSubjectPaperService {

	@Autowired
	private StreamHasSubjectPaperDao dao;
	
	public StreamHasSubjectPaper findById(int id) {
		return dao.findById(id);
	}

	public void saveStreamHasSubjectPaper(StreamHasSubjectPaper streamHasSubjectPaper) {
		dao.saveStreamHasSubjectPaper(streamHasSubjectPaper);
	}

	public void updateStreamHasSubjectPaper(StreamHasSubjectPaper streamHasSubjectPaper) {
		StreamHasSubjectPaper entity = dao.findById(streamHasSubjectPaper.getId());
		if(entity!=null){
			entity.setStreamId(streamHasSubjectPaper.getStreamId());
			entity.setSubject_paper_id(streamHasSubjectPaper.getSubject_paper_id());
			entity.setTeacher_id(streamHasSubjectPaper.getTeacher_id());
		}
	}

	public void deleteStreamHasSubjectPaperById(int id) {
		dao.deleteStreamHasSubjectPaper(id);
	}

	public List<StreamHasSubjectPaper> findAllStreamHasSubjectPapers() {
		return dao.findAllStreamHasSubjectPaper();
	}

	public StreamHasSubjectPaper findStreamHasSubjectPaper(int streamId, int subject_paper_id) {
		return dao.findStreamHasSubjectPaper(streamId, subject_paper_id);
	}

	public boolean isStreamHasSubjectPaperIdUnique(Integer id, int streamId, int subject_paper_id) {
		StreamHasSubjectPaper streamHasSubjectPaper = findStreamHasSubjectPaper(streamId, subject_paper_id);
		return ( streamHasSubjectPaper == null || ((id != null) && (streamHasSubjectPaper.getId() == id)));
	}

}
