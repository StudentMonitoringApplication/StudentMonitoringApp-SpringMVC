package com.sma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="SUBJECT_RESULTS")
public class SubjectResults {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "JOINING_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate date;
    
    @NotNull
	@Digits(integer=3, fraction = 2)
	@Column(name = "MARKS", nullable = false)
    private float marks;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "TEST_ID", nullable = false)
    private int test_id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "STUDENT_ID", nullable = false)
    private int student_id;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "CLASS_ID", nullable = false)
    private int class_id;
    
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getMarks() {
		return marks;
	}

	public void setMarks(float marks) {
		this.marks = marks;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
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
		if (!(obj instanceof SubjectResults))
			return false;
		SubjectResults other = (SubjectResults) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "subject_results [date=" + date + ", marks=" + marks + ", test_id="
				+ test_id + ", student_id=" + student_id + ", class_id=" + class_id + ", subject_id=" + subject_id + "]";
      
	}
}
