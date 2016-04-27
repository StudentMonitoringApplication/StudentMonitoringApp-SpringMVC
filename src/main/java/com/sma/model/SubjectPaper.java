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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SUBJECT_PAPER")
public class SubjectPaper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Column(name = "CODE", unique=true, nullable = false)
	private String code;
	
	@Size(min=3, max=50)
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@NotNull
	@Digits(integer=1, fraction = 0)
	@Column(name = "NUMBER", nullable = false)
	private int number;
	
	@NotNull
	@Digits(integer=1, fraction = 0)
	@Column(name = "FINAL_SCORE_AVERAGE", nullable = false)
	private int finalScoreAverage;
	
	@NotNull
	@Digits(integer=1, fraction = 0)
	@Column(name = "SUBJECT_ID", nullable = false)
	private int subjectId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFinalScoreAverage() {
		return finalScoreAverage;
	}

	public void setFinalScoreAverage(int finalScoreAverage) {
		this.finalScoreAverage = finalScoreAverage;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof SubjectPaper))
			return false;
		SubjectPaper other = (SubjectPaper) obj;
		if(id != other.id)
			return false;
		if(code == null){
			if(other.code != null)
				return false;
		}
		else if(!code.equals(other.code))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Subject [id=" + id + ", code=" + code + ", name="
                + name + ", number=" + number + "finalScoreAverage=" + finalScoreAverage + ", subjectId=" + subjectId + "]";
	}
	
}
