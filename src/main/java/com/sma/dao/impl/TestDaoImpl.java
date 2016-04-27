package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.TestDao;
import com.sma.model.Test;

@Repository("testDao")
public class TestDaoImpl extends AbstractDao<Integer, Test> implements TestDao {

	public Test findById(int id) {
		return getByKey(id);
	}

	public void saveTest(Test test) {
		persist(test);
	}

	public void deleteTestById(int id) {
		Query query = getSession().createSQLQuery("delete from test where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Test> findAllTest() {
		Criteria criteria = createEntityCriteria();
		return (List<Test>) criteria.list();
	}

	public Test findTest(int term, LocalDate year, int test_type_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("term", term));
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("test_type_id", test_type_id));
		return (Test) criteria.uniqueResult();
	}

}
