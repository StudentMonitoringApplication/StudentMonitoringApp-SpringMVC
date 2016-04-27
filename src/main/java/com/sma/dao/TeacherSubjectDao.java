package com.sma.dao;

import java.util.List;

import com.sma.model.TeacherSubject;

public interface TeacherSubjectDao {
	TeacherSubject findById(int id);
	
	void saveTeacherSubject(TeacherSubject teacherSubject);
		
	void deleteTeacherSubjectById(int id);

	List<TeacherSubject> findAllTeacherSubject(); 
	
	TeacherSubject findTeacherSubject(int teacher_id, int subject_id);
}
