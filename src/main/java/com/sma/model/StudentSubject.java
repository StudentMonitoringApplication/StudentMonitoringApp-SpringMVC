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
@Table(name="STUDENT_HAS_SUBJECT")
public class StudentSubject {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "STUDENT_ID", nullable = false)
    private int student_id;
    
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

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
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
		if (!(obj instanceof StudentSubject))
			return false;
		StudentSubject other = (StudentSubject) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "student_has_subject [student_id=" + student_id + ", subject_id=" + subject_id + "]";
       
	}
}
