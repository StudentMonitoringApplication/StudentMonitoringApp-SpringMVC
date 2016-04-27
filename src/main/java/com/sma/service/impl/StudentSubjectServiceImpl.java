package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.StudentSubjectDao;
import com.sma.model.StudentSubject;
import com.sma.service.StudentSubjectService;

@Service("studentSubjectService")
@Transactional
public class StudentSubjectServiceImpl implements StudentSubjectService {

	@Autowired
	private StudentSubjectDao dao;

	public StudentSubject findById(int id) {
		return dao.findById(id);
	}

	public void saveStudentSubject(StudentSubject StudentSubject) {
		dao.saveStudentSubject(StudentSubject);
	}

	
	public void updateStudentSubject(StudentSubject StudentSubject) {
		StudentSubject entity = dao.findById(StudentSubject.getId());
		if(entity!=null){
			entity.setStudent_id(StudentSubject.getStudent_id());
			entity.setSubject_id(StudentSubject.getSubject_id());
			
		}
	}

	public void deleteStudentSubjectId(int id) {
		dao.deleteStudentSubjectId(id);
	}

	public List<StudentSubject> findAllStudentSubjects() {
		return dao.findAllStudentSubjects();
	}

	public StudentSubject findStudentSubject(Integer student_id, Integer subject_id) {
		return dao.findStudentSubject(student_id, subject_id);
	}

	public boolean isStudentSubjectUnique(Integer id, Integer student_id, Integer subject_id) {
		StudentSubject StudentSubject = findStudentSubject(student_id, subject_id);
		return ( StudentSubject == null || ((id != null) && (StudentSubject.getId() == id)));
	}	
}
