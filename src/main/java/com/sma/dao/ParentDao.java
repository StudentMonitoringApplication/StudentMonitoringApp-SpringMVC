package com.sma.dao;

import java.util.List;

import com.sma.model.Parent;

public interface ParentDao {
	
	Parent findById(int id);
	
	void saveParent(Parent parent);
	
	void deleteParentByParentId(String parentId);
	
	List<Parent> findAllParents();
	
	Parent findParentByParentId(String parentId);

}
