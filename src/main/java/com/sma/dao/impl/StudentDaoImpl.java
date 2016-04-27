package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.StudentDao;
import com.sma.model.Student;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Integer, Student> implements StudentDao{

	public Student findById(int id) {
		return getByKey(id);
	}

	public void saveStudent(Student student) {
		persist(student);
	}

	public void deleteStudentByStudentId(String studentId) {
		Query query = getSession().createSQLQuery("delete from student where student_id = :studentId");
		query.setString("studentId", studentId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Student> findAllStudents() {
		Criteria criteria = createEntityCriteria();
		return (List<Student>) criteria.list();
	}

	public Student findStudentByStudentId(String studentId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("studentId", studentId));
		return (Student) criteria.uniqueResult();
	}
}
