package com.sma.service;

import java.util.List;

import com.sma.model.TeacherSubject;

public interface TeacherSubjectService {
	
	TeacherSubject findById(int id);
	
	void saveTeacherSubject(TeacherSubject teacherSubject);
	
	void updateTeacherSubject(TeacherSubject teacherSubject);
		
	void deleteTeacherSubjectById(int id);

	List<TeacherSubject> findAllTeacherSubject(); 
	
	TeacherSubject findTeacherSubject(int teacher_id, int subject_id);
	
	boolean isTeacherSubjectUnique(Integer id, int teacher_id, int subject_id);
}
