package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.HeadOfDepartmentDao;
import com.sma.model.HeadOfDepartment;

@Repository("headOfDepartmentDao")
public class HeadOfDepartmentDaoImpl extends AbstractDao<Integer, HeadOfDepartment> implements HeadOfDepartmentDao {

	public HeadOfDepartment findById(int id) {
		return getByKey(id);
	}

	public void saveHeadOfDepartment(HeadOfDepartment headOfDepartment) {
		persist(headOfDepartment);
	}

	public void deleteHeadOfDepartmentById(int id) {
		Query query = getSession().createSQLQuery("delete from head_of_department where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<HeadOfDepartment> findAllHeadOfDepartment() {
		Criteria criteria = createEntityCriteria();
		return (List<HeadOfDepartment>) criteria.list();
	}

	public HeadOfDepartment findHeadOfDepartment(int teacher_id, int subject_id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("teacher_id", teacher_id));
		criteria.add(Restrictions.eq("subject_id", subject_id));
		return (HeadOfDepartment) criteria.uniqueResult();
	}

}
