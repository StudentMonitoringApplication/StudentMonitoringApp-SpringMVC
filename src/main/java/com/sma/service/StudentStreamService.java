package com.sma.service;

import java.util.List;

import com.sma.model.StudentStream;

public interface StudentStreamService {
	
	StudentStream findById(int id);
	
	void saveStudentStream(StudentStream studentStream);
	
	void updateStudentStream(StudentStream studentStream);
		
	void deleteStudentStreamId(int id);

	List<StudentStream> findAllStudentStreams(); 
	
	StudentStream findStudentStream(int student_id, int class_id);
	
	boolean isStudentStreamUnique(Integer id, int student_id, int class_id);
}
