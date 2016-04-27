package com.sma.service;

import java.util.List;

import com.sma.model.Parent;

public interface ParentService {
	
	Parent findById(int id);
	
	void saveParent(Parent parent);
	
	void updateParent(Parent parent);
	
	void deleteParentByParentId(String parentId);
	
	List<Parent> findAllParents();
	
	Parent findParentByParentId(String parentId);
	
	boolean isParentIdUnique(Integer id, String parentId);
}
