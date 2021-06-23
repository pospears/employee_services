package com.exlservice.search.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "employeeId", nullable = false)
	private Long employeeId;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "jobTitle", nullable = false)
	private String jobTitle;
	
	@Column(name = "age", nullable = false)
	private Integer age =-1;

	@Column(name = "startDate", nullable = false)
	private LocalDate startDate = null;
	
	@Column(name = "endDate", nullable = false)
	private LocalDate endDate = null;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	public Employee() {}

	public Employee(String firstName, String lastName, String jobTitle, Integer age, LocalDate startDate, LocalDate endDate, String email) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.jobTitle = jobTitle;
	    this.age = age;
	    this.startDate = startDate;
	    this.endDate = endDate;
	    this.email = email;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	public boolean validEmployeeInfo() {
		
		return this != null && this.firstName != null && this.firstName != "" &&
		       this.lastName != null && this.lastName != "" &&
               this.jobTitle != null && this.jobTitle != "" &&
		       this.age != -1 && 
			   this.startDate != null &&
			   this.endDate != null;
	}


	@Override
	public String toString() {
		return String.format("Employee[id=%d, firstName='%s', lastName='%s', jobTitle='%s', age='%d', startDate='%s', endDate='%s', email='%s']", employeeId, firstName, lastName, jobTitle, age, startDate, endDate, email);
	}
}
