package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.StreamDao;
import com.sma.model.Stream;

@Repository("streamDao")
public class StreamDaoImpl extends AbstractDao<Integer, Stream> implements StreamDao {


	public Stream findById(int id) {
		return getByKey(id);
	}

	public void saveStream(Stream stream) {
		persist(stream);
		
	}

	public void deleteStream(int id) {
		Query query = getSession().createSQLQuery("delete from stream where id = :id");
		query.setInteger(id, id);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Stream> findAllStreams() {
		Criteria criteria = createEntityCriteria();
		return (List<Stream>) criteria.list();
	}

	public Stream findStream(String stream, LocalDate year) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("stream", stream));
		criteria.add(Restrictions.eq("year", year));
		return (Stream) criteria.uniqueResult();
		
	}

}
