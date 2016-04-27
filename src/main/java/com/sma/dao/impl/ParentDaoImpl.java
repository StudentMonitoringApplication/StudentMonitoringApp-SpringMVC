package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.ParentDao;
import com.sma.model.Parent;

@Repository("parentDao")
public class ParentDaoImpl extends AbstractDao<Integer, Parent> implements ParentDao {
	
	public Parent findById(int id) {
		return getByKey(id);
	}

	public void saveParent(Parent parent) {
		persist(parent);
	}

	public void deleteParentByParentId(String parentId) {
		Query query = getSession().createSQLQuery("delete from Parent where parent_id = :parentId");
		query.setString("parentId", parentId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Parent> findAllParents() {
		Criteria criteria = createEntityCriteria();
		return (List<Parent>) criteria.list();
	}

	public Parent findParentByParentId(String parentId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("parentId", parentId));
		return (Parent) criteria.uniqueResult();
	}

}
