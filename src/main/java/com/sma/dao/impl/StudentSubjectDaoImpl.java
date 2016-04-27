package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.StudentSubjectDao;
import com.sma.model.StudentSubject;

@Repository("studentSubjectDao")
public class StudentSubjectDaoImpl extends AbstractDao<Integer, StudentSubject> implements StudentSubjectDao {

	public StudentSubject findById(int id) {
		return getByKey(id);
	}

	public void saveStudentSubject(StudentSubject studentSubject) {
		persist(studentSubject);
	}

	public void deleteStudentSubjectId(int id) {
		Query query = getSession().createSQLQuery("delete from student_has_subject where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<StudentSubject> findAllStudentSubjects() {
		Criteria criteria = createEntityCriteria();
		return (List<StudentSubject>) criteria.list();
	}

	public StudentSubject findStudentSubject(int student_id, int subject_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("subject_id", subject_id));
		criteria.add(Restrictions.eq("student_id", student_id));
		return (StudentSubject) criteria.uniqueResult();
	}

}
