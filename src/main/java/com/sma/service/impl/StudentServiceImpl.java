package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.StudentDao;
import com.sma.model.Student;
import com.sma.service.StudentService;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao dao;

	public Student findById(int id){
		return dao.findById(id);
	}

	public void saveStudent(Student student){
		dao.saveStudent(student);
	}

	public void updateStudent(Student student){
		Student entity = dao.findById(student.getId());
		if(entity!=null){
			entity.setStudent_id(student.getStudent_id());
			entity.setStudent_first_name(student.getStudent_first_name());
			entity.setStudent_first_name(student.getStudent_first_name());
			entity.setParent_first_name(student.getParent_first_name());
			entity.setParent_first_name(student.getParent_first_name());
			entity.setParent_email(student.getParent_email());
			entity.setParent_phone_number(student.getParent_phone_number());
			entity.setClass_id(student.getClass_id());
		}
	}

	public void deleteStudentByStudentId(String studentId){
		dao.deleteStudentByStudentId(studentId);
	}

	public List<Student> findAllStudents(){
		return dao.findAllStudents();
	}

	public Student findStudentByStudentId(String studentId){
		return dao.findStudentByStudentId(studentId);
	}

	public boolean isStudentIdUnique(Integer id, String studentId){
		Student student = findStudentByStudentId(studentId);
		return (student == null || ((id != null) && (student.getId() == id)));
	}
}
