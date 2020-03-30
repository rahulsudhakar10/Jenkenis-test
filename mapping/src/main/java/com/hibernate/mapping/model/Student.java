package com.hibernate.mapping.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student extends AbstractClass{
	
	private static final long serialVersionUID = -7196985891972185273L;

	@Column(name="f_name")
	private String firstName;
	
	@Column(name="age")
	private int age;
	
	@Embedded
	private College college;
	
	public Student() {
		
	}
	
	public Student(Long id,String firstName, int age,Calendar createDate,College college) {
		super(id, createDate);
		this.firstName = firstName;
		this.age = age;
		this.college = college;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}	


}
