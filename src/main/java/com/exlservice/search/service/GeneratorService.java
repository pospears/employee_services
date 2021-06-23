package com.exlservice.search.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exlservice.search.dao.EmployeeRepository;
import com.exlservice.search.model.Employee;
import com.exlservice.search.model.GeneratorOptions;
import com.exlservice.search.util.AgeGenerator;
import com.exlservice.search.util.DateGenerator;
import com.exlservice.search.util.EmailGenerator;
import com.exlservice.search.util.JobTitleGenerator;
import com.exlservice.search.util.NameGenerator;

@Component(value = "generatorService")
public class GeneratorService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static final Integer EMPLOYEE_SEED_SIZE = 1000;
	private static final Logger logger = LoggerFactory.getLogger(GeneratorService.class);

	public void seedEmployeeData() {
		logger.info(String.format("Seeding employee data..."));
		generateEmployees();
		logger.info(String.format("Finished seeding employee data... application ready!"));
		
	}

	public void clearSeedEmployeeData() {
		logger.info(String.format("Clearing seeded employee data..."));
		employeeRepository.deleteAll();
		logger.info(String.format("Finished clearing seeding employee data... application ready!"));
		
	}
	public void generateEmployees() {
		logger.info(String.format("Generating employees..."));

		GeneratorOptions defaultOptions = defaultGeneratorOptions();
		for (int i = 0; i < EMPLOYEE_SEED_SIZE; i++) {
			
			String firstName = new NameGenerator(defaultOptions).newRandomFirstName();
			String lastName = new NameGenerator(defaultOptions).newRandomLastName();
			String jobTitle = new JobTitleGenerator(defaultOptions).newRandomJobTileName(); 
			Integer age = new AgeGenerator().newRandomAge();
			LocalDate startDate = new DateGenerator(defaultOptions).newRandomStartDate();
			LocalDate endDate = new DateGenerator(defaultOptions).newRandomEndDate();
			String emailAddress = firstName + "." + lastName + new EmailGenerator(defaultOptions).newRandomEmail();
			
			Employee employee = new Employee(firstName, lastName, jobTitle, age, startDate, endDate, emailAddress.toLowerCase());
			//logger.info(String.format("Employee added to database %s...", employee.toString()));
			employeeRepository.save(employee);
		}
	}

	private GeneratorOptions defaultGeneratorOptions() {

		GeneratorOptions options = new GeneratorOptions();

		options.getFirstNames().add("Bill");
		options.getFirstNames().add("Elon");
		options.getFirstNames().add("Jeff");
		options.getFirstNames().add("Mark");
		options.getFirstNames().add("Sundar");
		options.getFirstNames().add("Tim");
		options.getFirstNames().add("Satya");
		options.getFirstNames().add("Marc");
		options.getFirstNames().add("Larry");
		options.getFirstNames().add("Steve");
		options.getFirstNames().add("Eric");
		options.getFirstNames().add("Jack");
		options.getFirstNames().add("Meg");
		options.getFirstNames().add("Susan");
		options.getFirstNames().add("Jensen");
		options.getFirstNames().add("Lisa");
		options.getFirstNames().add("Michael");
		options.getFirstNames().add("Reed");
		options.getFirstNames().add("Safra");
		options.getFirstNames().add("Whitney");

		options.getLastNames().add("Zuckerberg");
		options.getLastNames().add("Dell");
		options.getLastNames().add("Huateng");
		options.getLastNames().add("Cook");
		options.getLastNames().add("Nadella");
		options.getLastNames().add("Zhang");
		options.getLastNames().add("Li");
		options.getLastNames().add("Rometty");
		options.getLastNames().add("Pichai");
		options.getLastNames().add("Kaeser");
		options.getLastNames().add("Chesky");
		options.getLastNames().add("Bezos");
		options.getLastNames().add("Hamilton");
		options.getLastNames().add("Alvather");
		options.getLastNames().add("Parekh");
		options.getLastNames().add("Parekh");
		options.getLastNames().add("Malpass");
		options.getLastNames().add("Swan");
		options.getLastNames().add("Krone");
		options.getLastNames().add("Robbins");

		options.getJobTitles().add("Network Engineer");
		options.getJobTitles().add("Security Manager");
		options.getJobTitles().add("DevOps Engineer");
		options.getJobTitles().add("Software Director");
		options.getJobTitles().add("Software Manager");
		options.getJobTitles().add("Quality Assurance");
		options.getJobTitles().add("Solutions Architect");
		options.getJobTitles().add("Principal Engineer");
		options.getJobTitles().add("Software Engineer");
		options.getJobTitles().add("Business Analyst");
		
		options.getEmailDomains().add("@gmail.com");
		options.getEmailDomains().add("@hotmail.com");
		options.getEmailDomains().add("@yahoo.com");
		options.getEmailDomains().add("@aol.com");

		options.getStartDateRange().setStart(2018);
		options.getStartDateRange().setEnd(2019);

		options.getEndDateRange().setStart(2020);
		options.getEndDateRange().setEnd(2021);

		return options;
	}
}
