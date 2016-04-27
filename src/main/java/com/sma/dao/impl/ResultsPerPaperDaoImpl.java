package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.ResultsPerPaperDao;
import com.sma.model.ResultsPerPaper;

@Repository("resultsPerPaperDao")
public class ResultsPerPaperDaoImpl extends AbstractDao<Integer, ResultsPerPaper> implements ResultsPerPaperDao {

	public ResultsPerPaper findById(int id) {
		return getByKey(id);
	}

	public void saveResultsPerPaper(ResultsPerPaper resultsPerPaper) {
		persist(resultsPerPaper);
	}

	public void deleteResultsPerPaperById(int id) {
		Query query = getSession().createSQLQuery("delete from results_per_paper where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ResultsPerPaper> findAllResultsPerPaper() {
		Criteria criteria = createEntityCriteria();
		return (List<ResultsPerPaper>) criteria.list();
	}

	public ResultsPerPaper findResultsPerPaper(int test_id, int student_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("test_id", test_id));
		criteria.add(Restrictions.eq("student_id", student_id));
		return (ResultsPerPaper) criteria.uniqueResult();
	}

}
