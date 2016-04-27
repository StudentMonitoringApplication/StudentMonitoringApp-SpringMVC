package com.sma.dao;

import java.util.List;

import com.sma.model.HeadOfDepartment;

public interface HeadOfDepartmentDao {
	HeadOfDepartment findById(int id);
	
	void saveHeadOfDepartment(HeadOfDepartment headOfDepartment);
		
	void deleteHeadOfDepartmentById(int id);

	List<HeadOfDepartment> findAllHeadOfDepartment(); 
	
	HeadOfDepartment findHeadOfDepartment(int teacher_id, int subject_id);
}
