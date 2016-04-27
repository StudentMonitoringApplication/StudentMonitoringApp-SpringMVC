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
@Table(name="STUDENT")
public class Student {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty
	@Column(name = "STUDENT_ID", unique=true, nullable = false)
    private String student_id;
    
    @Size(min=3, max=50)
	@Column(name = "STUDENT_FIRST_NAME", nullable = false)
    private String student_first_name;
    
    @Size(min=3, max=50)
	@Column(name = "STUDENT_LAST_NAME", nullable = false)
    private String student_last_name;
    
    @Size(min=3, max=50)
	@Column(name = "PARENT_FIRST_NAME", nullable = false)
    private String parent_first_name;
    
    @Size(min=3, max=50)
	@Column(name = "PARENT_LAST_NAME", nullable = false)
    private String parent_last_name;
    
    @Size(min=3, max=50)
	@Column(name = "PARENT_EMAIL", nullable = false)
    private String parent_email;
    
    @Size(min=3, max=50)
	@Column(name = "PARENT_PHONE_NUMBER", nullable = false)
    private String parent_phone_number;
    
    @NotNull
	@Digits(integer=4, fraction = 0)
	@Column(name = "CLASS_ID", nullable = false)
    private int class_id;
    
      
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_first_name() {
		return student_first_name;
	}

	public void setStudent_first_name(String student_first_name) {
		this.student_first_name = student_first_name;
	}

	public String getStudent_last_name() {
		return student_last_name;
	}

	public void setStudent_last_name(String student_last_name) {
		this.student_last_name = student_last_name;
	}

	public String getParent_first_name() {
		return parent_first_name;
	}

	public void setParent_first_name(String parent_first_name) {
		this.parent_first_name = parent_first_name;
	}

	public String getParent_last_name() {
		return parent_last_name;
	}

	public void setParent_last_name(String parent_last_name) {
		this.parent_last_name = parent_last_name;
	}

	public String getParent_email() {
		return parent_email;
	}

	public void setParent_email(String parent_email) {
		this.parent_email = parent_email;
	}

	public String getParent_phone_number() {
		return parent_phone_number;
	}

	public void setParent_phone_number(String parent_phone_number) {
		this.parent_phone_number = parent_phone_number;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((student_id == null) ? 0 : student_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (student_id == null) {
			if (other.student_id != null)
				return false;
		} else if (!student_id.equals(other.student_id))
			return false;
		return true;
	}
    
    @Override
	public String toString() {
        return "student [student_id=" + student_id + ", student_first_name=" + student_first_name + ", student_last_name="
				+ student_last_name + ", parent_first_name=" + parent_first_name + ", parent_last_name="
                + parent_last_name + ", parent_email=" + parent_email + ", parent_phone_number=" + parent_phone_number + ", class_id=" 
                + class_id + "]";   
	}
}
