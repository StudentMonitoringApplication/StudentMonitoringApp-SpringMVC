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
@Table(name="RESULTS_PER_PAPER")
public class ResultsPerPaper {
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
	@Column(name = "STUDENT_ID", nullable = false)
    private int student_id;
    
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
	@Column(name = "TEST_ID", nullable = false)
    private int test_id;
    
    
    
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

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
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

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
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
		if (!(obj instanceof ResultsPerPaper))
			return false;
		ResultsPerPaper other = (ResultsPerPaper) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "results_per_paper [date=" + date + ", marks=" + marks + ", student_id="
				+ student_id + ", stream_id=" + stream_id + ", subject_paper_id=" + subject_paper_id + ", test_id=" + test_id + "]";
        
	}
}
