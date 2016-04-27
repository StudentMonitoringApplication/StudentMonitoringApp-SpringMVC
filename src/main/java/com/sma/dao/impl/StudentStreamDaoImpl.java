package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.StudentStreamDao;
import com.sma.model.StudentStream;

@Repository("studentStreamDao")
public class StudentStreamDaoImpl extends AbstractDao<Integer, StudentStream> implements StudentStreamDao {

	public StudentStream findById(int id) {
		return getByKey(id);
	}

	public void saveStudentStream(StudentStream studentStream) {
		persist(studentStream);
	}

	public void deleteStudentStreamId(int id) {
		Query query = getSession().createSQLQuery("delete from student_in_class where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<StudentStream> findAllStudentStreams() {
		Criteria criteria = createEntityCriteria();
		return (List<StudentStream>) criteria.list();
	}

	public StudentStream findStudentStream(int student_id, int class_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("class_id", class_id));
		criteria.add(Restrictions.eq("student_id", student_id));
		return (StudentStream) criteria.uniqueResult();
	}

	

}
