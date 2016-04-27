package com.sma.service;

import java.util.List;

import com.sma.model.HeadOfDepartment;

public interface HeadOfDepartmentService {

	HeadOfDepartment findById(int id);
	
	void saveHeadOfDepartment(HeadOfDepartment headOfDepartment);
	
	void updateHeadOfDepartment(HeadOfDepartment headOfDepartment);
	
	void deleteHeadOfDepartmentById(int id);
	
	List<HeadOfDepartment> findAllHeadOfDepartments();
	
	HeadOfDepartment findHeadOfDepartment(int teacher_id, int subject_id);
	
	boolean isHeadOfDepartmentIdUnique(Integer id, int teacher_id, int subject_id);
}
