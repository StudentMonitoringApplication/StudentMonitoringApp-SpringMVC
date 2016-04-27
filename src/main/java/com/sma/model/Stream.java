package com.sma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="CLASS")
public class Stream {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min=3, max=10)
	@Column(name = "STREAM", nullable = false)
	private String stream;
	
	@Size(min=3, max=50)
	@Column(name = "CLASS_NAME", nullable = false)
	private String className;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy")
	@Column(name = "JOINING_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate year;
	
	@NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "TEACHER_ID", nullable = false)
	private int teacherId;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStream() {
		return stream;
	}


	public void setStream(String stream) {
		this.stream = stream;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public LocalDate getYear() {
		return year;
	}


	public void setYear(LocalDate year) {
		this.year = year;
	}


	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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
		if (!(obj instanceof Stream))
			return false;
		Stream other = (Stream) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Stream [stream=" + stream + ", class_name=" + className + ", year="
				+ year + ", teacher_id=" + teacherId + "]";
	}
}
