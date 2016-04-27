package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.SubjectResultsDao;
import com.sma.model.SubjectResults;

@Repository("subjectResultsDao")
public class SubjectResultsDaoImpl extends AbstractDao<Integer, SubjectResults> implements SubjectResultsDao {

	public SubjectResults findById(int id) {
		return getByKey(id);
	}

	public void saveSubjectResults(SubjectResults subjectResults) {
		persist(subjectResults);
	}

	public void deleteSubjectResultsById(int id) {
		Query query = getSession().createSQLQuery("delete from subject_results where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<SubjectResults> findAllSubjectResults() {
		Criteria criteria = createEntityCriteria();
		return (List<SubjectResults>) criteria.list();
	}

	public SubjectResults findSubjectResults(int test_id, int student_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("test_id", test_id));
		criteria.add(Restrictions.eq("student_id", student_id));
		return (SubjectResults) criteria.uniqueResult();
	}

}
