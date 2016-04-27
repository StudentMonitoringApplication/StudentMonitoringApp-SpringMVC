package com.sma.dao;

import java.util.List;

import com.sma.model.Student;

public interface StudentDao {

	Student findById(int id);
	
	void saveStudent(Student student);
		
	void deleteStudentByStudentId(String studentId);

	List<Student> findAllStudents(); 
	
	Student findStudentByStudentId(String studentId);
}
