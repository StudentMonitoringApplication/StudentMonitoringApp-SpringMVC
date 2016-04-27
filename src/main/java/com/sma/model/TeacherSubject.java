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
@Table(name="TEACHER_HAS_SUBJECT")
public class TeacherSubject {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "TEACHER_ID", nullable = false)
    private int teacher_id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "SUBJECT_ID", nullable = false)
    private int subject_id;
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
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
		if (!(obj instanceof TeacherSubject))
			return false;
		TeacherSubject other = (TeacherSubject) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "teacher_has_subject [teacher_id=" + teacher_id + ", subject_id=" + subject_id + "]";
      
	}
}
