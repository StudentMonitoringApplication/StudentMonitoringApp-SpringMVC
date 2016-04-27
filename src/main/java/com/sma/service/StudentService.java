package com.sma.service;

import java.util.List;

import com.sma.model.Student;

public interface StudentService {

	Student findById(int id);
	
	void saveStudent(Student student);
	
	void updateStudent(Student student);
	
	void deleteStudentByStudentId(String studentId);

	List<Student> findAllStudents(); 
	
	Student findStudentByStudentId(String studentId);

	boolean isStudentIdUnique(Integer id, String studentId);
}
