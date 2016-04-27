package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.TeacherSubjectDao;
import com.sma.model.TeacherSubject;
import com.sma.service.TeacherSubjectService;

@Service("teacherSubjectService")
@Transactional 
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	
	@Autowired
	private TeacherSubjectDao dao;
	
	public TeacherSubject findById(int id) {
		return dao.findById(id);
	}

	public void saveTeacherSubject(TeacherSubject teacherSubject) {
		dao.saveTeacherSubject(teacherSubject);
		
	}

	public void updateTeacherSubject(TeacherSubject teacherSubject) {
		TeacherSubject entity = dao.findById(teacherSubject.getId());
		if(entity!=null){
			entity.setSubject_id(teacherSubject.getSubject_id());
			entity.setTeacher_id(teacherSubject.getTeacher_id());
		}
		
	}

	public void deleteTeacherSubjectById(int id) {
		dao.deleteTeacherSubjectById(id);
		
	}

	public List<TeacherSubject> findAllTeacherSubject() {
		return dao.findAllTeacherSubject();
	}

	public TeacherSubject findTeacherSubject(int teacher_id, int subject_id) {
		return dao.findTeacherSubject(teacher_id, subject_id);
	}

	public boolean isTeacherSubjectUnique(Integer id, int teacher_id, int subject_id) {
		TeacherSubject teacherSubject = findTeacherSubject(teacher_id, subject_id);
		return (teacherSubject == null || ((id!= null) && (teacherSubject.getId() == id )));
	}

}
