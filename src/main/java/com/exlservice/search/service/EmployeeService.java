package com.exlservice.search.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exlservice.search.dao.EmployeeRepository;
import com.exlservice.search.model.Employee;

@Component(value = "employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public boolean employeeExists(Employee employee) {
		  if (employee != null && employee.validEmployeeInfo() && employeeRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndStartDateAndEndDate(
					employee.getFirstName(), employee.getLastName(), employee.getStartDate(), employee.getEndDate()).size() > 0) {
		  logger.info(String.format("Employees exists in db ...")); return true; }
		
		return false;
	}

	public List<Employee> findEmployees() {
			return (List<Employee>) employeeRepository.findAll();
	}
	
	// Adding employee if provided data is valid and employee is not already in database
	public void addEmployee(Employee employee) {
		logger.info(String.format("Attempting to add employees to database..."));

		if (employee == null || !employee.validEmployeeInfo() || employeeExists(employee))
			return;
					
		logger.info(String.format("Adding employee to database %s", employee.toString()));
		employeeRepository.save(employee);
	}
}
