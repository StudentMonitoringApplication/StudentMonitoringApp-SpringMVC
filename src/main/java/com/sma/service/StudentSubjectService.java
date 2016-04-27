package com.sma.service;

import java.util.List;

import com.sma.model.StudentSubject;

public interface StudentSubjectService {
	
	StudentSubject findById(int id);
	
	void saveStudentSubject(StudentSubject studentSubject);
	
	void updateStudentSubject(StudentSubject studentSubject);
	
	void deleteStudentSubjectId(int id);

	List<StudentSubject> findAllStudentSubjects(); 
	
	StudentSubject findStudentSubject(Integer student_id, Integer subject_id);

	boolean isStudentSubjectUnique(Integer id, Integer student_id, Integer subject_id);
}
