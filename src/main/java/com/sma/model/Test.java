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
@Table(name="TEST")

public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Digits(integer=1, fraction = 0)
	@Column(name = "TERM", nullable = false)
	private int term;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy")
	@Column(name = "JOINING_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate year;
	
	@NotNull
	@Digits(integer=1, fraction = 0)
	@Column(name = "TEST_TYPE_ID", nullable = false)
	private int test_type_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public LocalDate getYear() {
		return year;
	}

	public void setYear(LocalDate year) {
		this.year = year;
	}

	public int getTest_type_id() {
		return test_type_id;
	}

	public void setTest_type_id(int test_type_id) {
		this.test_type_id = test_type_id;
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
		if (!(obj instanceof Test))
			return false;
		Test other = (Test) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "test [term=" + term + ", year=" + year + ", test_type_id=" + "]";
        
	}
	
}
