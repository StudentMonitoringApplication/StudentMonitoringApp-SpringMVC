package com.sma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PARENT")
public class Parent {
	
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@NotEmpty
		@Column(name = "PARENT_ID", unique=true, nullable = false)
		private String parentId;
		
		@Size(min=3, max=50)
		@Column(name = "NAME", nullable = false)
		private String name;
		
		@Size(min=3, max=15)
		@Column(name = "PHONE_NUMBER", nullable = false)
		private String phoneNumber;
		
		@Size(min=3, max=50)
		@Column(name = "EMAIL", nullable = false)
		private String email;
		
		@Size(min=3, max=8)
		@Column(name = "STATUS", nullable = false)
		private String status;
		
		@Size(min=8, max=50)
		@Column(name = "PASSWORD", nullable = false)
		private String password;

		
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getParentId() {
			return parentId;
		}


		public void setParentId(String parentId) {
			this.parentId = parentId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getPhoneNumber() {
			return phoneNumber;
		}


		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Parent))
				return false;
			Parent other = (Parent) obj;
			if (id != other.id)
				return false;
			if (parentId == null) {
				if (other.parentId != null)
					return false;
			} else if (!parentId.equals(other.parentId))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "Parent [parentId=" + parentId + ", name=" + name + ", phoneNumber="
					+ phoneNumber + ", email=" + email + ", status=" + status + ", password=" + password + "]";
		}
	}


