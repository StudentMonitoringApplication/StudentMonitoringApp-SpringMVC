package com.sma.service.impl;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.StreamDao;
import com.sma.model.Stream;
import com.sma.service.StreamService;

@Service("streamService")
@Transactional
public class StreamServiceImpl implements StreamService {
	
	@Autowired
	private StreamDao dao;
	
	public Stream findById(int id) {
		return dao.findById(id);
	}

	public void saveStream(Stream stream) {
		dao.saveStream(stream);
	}

	public void updateStream(Stream stream) {
		Stream entity = dao.findById(stream.getId());
		if(entity!=null){
			entity.setStream(stream.getStream());
			entity.setClassName(stream.getClassName());
			entity.setYear(stream.getYear());
			entity.setTeacherId(stream.getTeacherId());
		}
	}

	public void deleteStream(int id) {
		dao.deleteStream(id);
		
	}

	public List<Stream> findAllStreams() {
		return dao.findAllStreams();
	}

	public Stream findStream(String stream, LocalDate year) {
		return dao.findStream(stream, year);
	}

	public boolean isStreamUnique(Integer id, String stream, LocalDate year) {
		Stream stream1 = findStream(stream, year);
		return ( stream1 == null || ((id!=null) && (stream1.getId()== id)));
	}

}
