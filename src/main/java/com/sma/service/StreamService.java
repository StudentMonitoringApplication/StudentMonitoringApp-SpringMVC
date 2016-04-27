package com.sma.service;

import java.util.List;

import org.joda.time.LocalDate;

import com.sma.model.Stream;

public interface StreamService {
	Stream findById(int id);
	
	void saveStream(Stream stream);
	
	void updateStream(Stream stream);
	
	void deleteStream(int id);
	
	List<Stream> findAllStreams();
	
	Stream findStream(String stream, LocalDate year);
	
	boolean isStreamUnique(Integer id, String stream, LocalDate year);
}
