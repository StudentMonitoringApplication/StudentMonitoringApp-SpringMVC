package com.sma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="CLASS_HAS_SUBJECT_PAPER")

public class StreamHasSubjectPaper {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "STREAM_ID", nullable = false)
	private int stream_id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "SUBJECT_PAPER_ID", nullable = false)
	private int subject_paper_id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "TEACHER_ID", nullable = false)
	private int teacher_id;
    
      
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStream_id() {
		return stream_id;
	}

	public void setStream_id(int stream_id) {
		this.stream_id = stream_id;
	}

	public int getSubject_paper_id() {
		return subject_paper_id;
	}

	public void setSubject_paper_id(int subject_paper_id) {
		this.subject_paper_id = subject_paper_id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StreamHasSubjectPaper))
			return false;
		StreamHasSubjectPaper other = (StreamHasSubjectPaper) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "class_has_subject_paper [stream_id=" + stream_id + ", subject_paper_id=" + subject_paper_id + ", teacher_id=" + teacher_id + "]";        
	}
}
