package com.nissan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usertypetable")
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userTypeId;
	private String userType;

//	Default Constructor
	public UserType() {
		super();
		// TODO Auto-generated constructor stub
	}

//	setters and getters
	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

//	parameterized constructor
	public UserType(Integer userTypeId, String userType) {
		super();
		this.userTypeId = userTypeId;
		this.userType = userType;
	}

}
