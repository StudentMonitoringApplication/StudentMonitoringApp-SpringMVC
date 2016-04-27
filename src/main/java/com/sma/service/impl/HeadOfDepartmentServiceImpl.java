package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.HeadOfDepartmentDao;
import com.sma.model.HeadOfDepartment;
import com.sma.service.HeadOfDepartmentService;

@Service("headOfDepartmentService")
@Transactional
public class HeadOfDepartmentServiceImpl implements HeadOfDepartmentService {

	@Autowired
	private HeadOfDepartmentDao dao;
	
	public HeadOfDepartment findById(int id) {
		return dao.findById(id);
	}

	public void saveHeadOfDepartment(HeadOfDepartment headOfDepartment) {
		dao.saveHeadOfDepartment(headOfDepartment);
	}

	public void updateHeadOfDepartment(HeadOfDepartment headOfDepartment) {
		HeadOfDepartment entity = dao.findById(headOfDepartment.getId());
		if(entity!=null){
			entity.setSubject_id(headOfDepartment.getSubject_id());
			entity.setTeacher_id(headOfDepartment.getTeacher_id());		
		}
	}

	public void deleteHeadOfDepartmentById(int id) {
		dao.deleteHeadOfDepartmentById(id);
	}

	public List<HeadOfDepartment> findAllHeadOfDepartments() {
		return dao.findAllHeadOfDepartment();
	}

	public HeadOfDepartment findHeadOfDepartment(int teacher_id, int subject_id) {
		return dao.findHeadOfDepartment(teacher_id, subject_id);
	}

	public boolean isHeadOfDepartmentIdUnique(Integer id, int teacher_id, int subject_id) {
		HeadOfDepartment headOfDepartment = findHeadOfDepartment(teacher_id, subject_id);
		return ( headOfDepartment == null || ((id != null) && (headOfDepartment.getId() == id)));
	}

}
