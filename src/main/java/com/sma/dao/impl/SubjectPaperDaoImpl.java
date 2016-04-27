package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.SubjectPaperDao;
import com.sma.model.SubjectPaper;

@Repository("subjectPaperDao")
public class SubjectPaperDaoImpl extends AbstractDao<Integer, SubjectPaper> implements SubjectPaperDao {

	public SubjectPaper findById(int id) {
		return getByKey(id);
	}

	public void saveSubjectPaper(SubjectPaper subjectPaper) {
		persist(subjectPaper);
	}

	public void deleteSubjectPaperByCode(String code) {
		Query query = getSession().createSQLQuery("delete from subject_paper where code = :code");
		query.setString("code", code);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<SubjectPaper> findAllSubjectPapers() {
		Criteria criteria = createEntityCriteria();
		return (List<SubjectPaper>) criteria.list();
	}

	public SubjectPaper findSubjectPaperByCode(String code) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
		return (SubjectPaper) criteria.uniqueResult();
	}

}
