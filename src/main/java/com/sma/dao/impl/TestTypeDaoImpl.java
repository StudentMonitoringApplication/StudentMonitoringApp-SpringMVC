package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.TestTypeDao;
import com.sma.model.TestType;

@Repository("testTypeDao")
public class TestTypeDaoImpl extends AbstractDao<Integer, TestType> implements TestTypeDao {

	public TestType findById(int id) {
		return getByKey(id);
	}

	public void saveTestType(TestType testType) {
		persist(testType);
	}

	public void deleteTestTypeByCategory(String category) {
		Query query = getSession().createSQLQuery("delete from test_type where category = :category");
		query.setString("category", category);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<TestType> findAllTestTypes() {
		Criteria criteria = createEntityCriteria();
		return (List<TestType>) criteria.list();
	}

	public TestType findTestTypeByCategory(String category) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("category", category));
		return (TestType) criteria.uniqueResult();
	}

}
