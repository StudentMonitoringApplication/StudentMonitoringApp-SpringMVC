package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.ParentDao;
import com.sma.model.Parent;
import com.sma.service.ParentService;

@Service("parentService")
@Transactional
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentDao dao;

	public Parent findById(int id){
		return dao.findById(id);
	}

	public void saveParent(Parent parent){
		dao.saveParent(parent);
	}

	public void updateParent(Parent parent){
		Parent entity = dao.findById(parent.getId());
		if(entity!=null){
			entity.setParentId(parent.getParentId());
			entity.setName(parent.getName());
			entity.setPhoneNumber(parent.getPhoneNumber());
			entity.setEmail(parent.getEmail());
			entity.setStatus(parent.getStatus());
			entity.setPassword(parent.getPassword());
		}
	}

	public void deleteParentByParentId(String parentId){
		dao.deleteParentByParentId(parentId);
	}

	public List<Parent> findAllParents(){
		return dao.findAllParents();
	}

	public Parent findParentByParentId(String parentId){
		return dao.findParentByParentId(parentId);
	}

	public boolean isParentIdUnique(Integer id, String parentId){
		Parent parent = findParentByParentId(parentId);
		return ( parent == null || ((id != null) && (parent.getId() == id)));
	}

}
