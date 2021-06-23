package com.exlservice.search.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.exlservice.search.model.Employee;

@Component
public class EmployeeResponse 
{
	List<Employee> employees = null;
	Integer totalEmployees = 0;
	
	public EmployeeResponse() {}

	public EmployeeResponse(List<Employee> employees, Integer dbCount) {
		this.employees = employees;
		this.totalEmployees = dbCount;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Integer getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(Integer totalEmployees) {
		this.totalEmployees = totalEmployees;
	}
}
