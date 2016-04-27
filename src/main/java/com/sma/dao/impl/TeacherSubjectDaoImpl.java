package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.TeacherSubjectDao;
import com.sma.model.TeacherSubject;

@Repository("teacherSubjectDao")
public class TeacherSubjectDaoImpl extends AbstractDao<Integer, TeacherSubject> implements TeacherSubjectDao {

	public TeacherSubject findById(int id) {
		return getByKey(id);
	}

	public void saveTeacherSubject(TeacherSubject teacherSubject) {
		persist(teacherSubject);
	}

	public void deleteTeacherSubjectById(int id) {
		Query query = getSession().createSQLQuery("delete from teacher_has_subject where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<TeacherSubject> findAllTeacherSubject() {
		Criteria criteria = createEntityCriteria();
		return (List<TeacherSubject>) criteria.list();
	}

	public TeacherSubject findTeacherSubject(int teacher_id, int subject_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("teacher_id", teacher_id));
		criteria.add(Restrictions.eq("subject_id", subject_id));
		return (TeacherSubject) criteria.uniqueResult();
	}

}
