package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table
@Data
public class Student {
	
	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)
	private Long id;
	private String name;
	private String email;
	private String mobileNo;
	private String address;
	private LocalDate dob;
	
	@Transient
	private int age;
	
	public Student() {
		
	}

	public Student(Long id, String name, String email, LocalDate dob, String mobileNo, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.dob = dob;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	
	public Student(String name, String email, LocalDate dob) {
		this.name = name;
		this.age = age;
		this.dob = dob;
		this.email = email;
	}
	
	public int getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(int age) {
		this.age = age;
	}


	

}
