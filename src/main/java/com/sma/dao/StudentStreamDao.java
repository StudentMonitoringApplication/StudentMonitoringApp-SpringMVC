package com.sma.dao;

import java.util.List;

import com.sma.model.StudentStream;

public interface StudentStreamDao {
	StudentStream findById(int id);
	
	void saveStudentStream(StudentStream studentStream);
		
	void deleteStudentStreamId(int id);

	List<StudentStream> findAllStudentStreams(); 
	
	StudentStream findStudentStream(int student_id, int class_id);
}
