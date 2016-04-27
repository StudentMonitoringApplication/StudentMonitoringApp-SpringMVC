package com.sma.dao;

import java.util.List;

import com.sma.model.StudentSubject;

public interface StudentSubjectDao {
	StudentSubject findById(int id);
	
	void saveStudentSubject(StudentSubject studentSubject);
		
	void deleteStudentSubjectId(int id);

	List<StudentSubject> findAllStudentSubjects(); 
	
	StudentSubject findStudentSubject(int student_id, int subject_id);
}
