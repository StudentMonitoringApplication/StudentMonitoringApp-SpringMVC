package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.StreamHasSubjectPaperDao;
import com.sma.model.StreamHasSubjectPaper;

@Repository("streamHasSubjectPaperDao")
public class StreamHasSubjectPaperDaoImpl extends AbstractDao<Integer, StreamHasSubjectPaper> implements StreamHasSubjectPaperDao{

	public StreamHasSubjectPaper findById(int id) {
		return getByKey(id);
	}

	public void saveStreamHasSubjectPaper(StreamHasSubjectPaper streamHasSubjectPaper) {
		persist(streamHasSubjectPaper);
		
	}

	public void deleteStreamHasSubjectPaper(int id) {
		Query query = getSession().createSQLQuery("delete from Teacher where id = :id");
		query.setInteger("id", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<StreamHasSubjectPaper> findAllStreamHasSubjectPaper() {
		Criteria criteria = createEntityCriteria();
		return (List<StreamHasSubjectPaper>) criteria.list();
	}

	public StreamHasSubjectPaper findStreamHasSubjectPaper(int stream_id, int subject_paper_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("stream_id", stream_id));
		criteria.add(Restrictions.eq("subject_paper_id", subject_paper_id));
		return (StreamHasSubjectPaper) criteria.uniqueResult();
	}

}
