package com.sma.dao;

import java.util.List;

import org.joda.time.LocalDate;

import com.sma.model.Stream;

public interface StreamDao {
	
	Stream findById(int id);
	
	void saveStream(Stream stream);
	
	void deleteStream(int id);
	
	List<Stream> findAllStreams();
	
	Stream findStream(String stream, LocalDate year);
	
	
	
	
}
