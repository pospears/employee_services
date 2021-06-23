package com.exlservice.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exlservice.search.model.Employee;
import com.exlservice.search.service.EmployeeService;

//Allow search for employees  
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	// Bootstrap or Search for employees to display in the list
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public List<Employee> search() {

		logger.info(String.format("Accessing /search"));
		return employeeService.findEmployees();
	}

	// Add employee
	@RequestMapping(value = "/employees", method = { RequestMethod.POST })
	public List<Employee> addEmployee(@ModelAttribute Employee employee) {
		logger.info(String.format("Adding employees...", employee));

		employeeService.addEmployee(employee);
		return employeeService.findEmployees();
	}
}
