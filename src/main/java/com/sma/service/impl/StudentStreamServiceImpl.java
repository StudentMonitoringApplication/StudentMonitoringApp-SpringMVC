package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.StudentStreamDao;
import com.sma.model.StudentStream;
import com.sma.service.StudentStreamService;

@Service("studentStreamService")
@Transactional 
public class StudentStreamServiceImpl implements StudentStreamService {
	
	@Autowired
	private StudentStreamDao dao;
	
	public StudentStream findById(int id) {
		return dao.findById(id);
	}

	public void saveStudentStream(StudentStream studentStream) {
		dao.saveStudentStream(studentStream);
		
	}

	public void updateStudentStream(StudentStream studentStream) {
		StudentStream entity = dao.findById(studentStream.getStream_id());
		if(entity!=null){
			entity.setStudent_id(studentStream.getStudent_id());
			entity.setStream_id(studentStream.getStream_id());
		}
		
	}

	public void deleteStudentStreamId(int id) {
		dao.deleteStudentStreamId(id);
		
	}

	public List<StudentStream> findAllStudentStreams() {
		return dao.findAllStudentStreams();
	}

	public StudentStream findStudentStream(int student_id, int stream_id) {
		return dao.findStudentStream(student_id, stream_id);
	}

	public boolean isStudentStreamUnique(Integer id, int student_id, int stream_id) {
		StudentStream studentStream = findStudentStream(student_id, stream_id);
		return (studentStream == null || ((id!= null) && (studentStream.getId() == id )));
	}

}
