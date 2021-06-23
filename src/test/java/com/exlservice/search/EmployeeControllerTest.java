package com.exlservice.search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.exlservice.search.dao.EmployeeRepository;
import com.exlservice.search.model.Employee;
import com.exlservice.search.service.EmployeeService;
import com.exlservice.search.service.GeneratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Resource
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private GeneratorService generatorService;
	
	@BeforeEach
    public void init() {
		generatorService.seedEmployeeData();		
    	assertThat(employeeRepository.findAll().size()).isEqualTo(GeneratorService.EMPLOYEE_SEED_SIZE);
    }

    @AfterEach
    public void teardown() {
    	generatorService.clearSeedEmployeeData();
    	assertThat(employeeRepository.findAll().size()).isEqualTo(0);
    }
	
	@Test
	public void search() throws Exception {
		// Search with new employee
		MvcResult mvcResult = mvc.perform(get("/search").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		List<Employee> employeelist =  mapEmployeeFromJson(content);
		assertThat(employeelist.size()).isEqualTo(GeneratorService.EMPLOYEE_SEED_SIZE);
	}

	@Test
	public void addEmployee() throws Exception {
		
		assertThat(employeeRepository.findAll().size()).isEqualTo(GeneratorService.EMPLOYEE_SEED_SIZE);

		// Add new employee
		MvcResult mvcResult = mvc.perform(post("/employees").flashAttr("employee", new Employee("testFirstName", "testLastName", "testJobTitle", 99, LocalDate.now(), LocalDate.now(), "test@email.com")).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		List<Employee> employeelist = mapEmployeeFromJson(content);
		assertThat(employeelist.size()).isEqualTo(GeneratorService.EMPLOYEE_SEED_SIZE+1);
	}
	
	@Test
    public void insertRetrieveUser() {
		assertThat(employeeRepository.findAll().size()).isEqualTo(GeneratorService.EMPLOYEE_SEED_SIZE);

		Employee employee = new Employee("testFirstName", "testLastName", "testJobTitle", 99, LocalDate.now(), LocalDate.now(), "test@email.com");
        employeeService.addEmployee(employee);
        employeeService.addEmployee(employee);
        
        assertThat(employeeRepository.findAll().size()).isEqualTo(GeneratorService.EMPLOYEE_SEED_SIZE+1);
        
        List<Employee> retrievedEmployee = employeeRepository.findByEmail(employee.getEmail());
        assertThat(retrievedEmployee.get(0).getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(retrievedEmployee.get(0).getLastName()).isEqualTo(employee.getLastName());
        assertThat(retrievedEmployee.get(0).getJobTitle()).isEqualTo(employee.getJobTitle());
        assertThat(retrievedEmployee.get(0).getAge()).isEqualTo(employee.getAge());
        assertThat(retrievedEmployee.get(0).getStartDate()).isEqualTo(employee.getStartDate());
        assertThat(retrievedEmployee.get(0).getEndDate()).isEqualTo(employee.getEndDate());
        assertThat(retrievedEmployee.get(0).getEmail()).isEqualTo(employee.getEmail());
        
        
        retrievedEmployee = employeeRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndStartDateAndEndDate(
				employee.getFirstName(), employee.getLastName(), employee.getStartDate(), employee.getEndDate());
        assertThat(retrievedEmployee.get(0).getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(retrievedEmployee.get(0).getLastName()).isEqualTo(employee.getLastName());
        assertThat(retrievedEmployee.get(0).getJobTitle()).isEqualTo(employee.getJobTitle());
        assertThat(retrievedEmployee.get(0).getAge()).isEqualTo(employee.getAge());
        assertThat(retrievedEmployee.get(0).getStartDate()).isEqualTo(employee.getStartDate());
        assertThat(retrievedEmployee.get(0).getEndDate()).isEqualTo(employee.getEndDate());
        assertThat(retrievedEmployee.get(0).getEmail()).isEqualTo(employee.getEmail());
    
     }
	
	private String mapEmployeeToJson(Employee employee) throws JsonProcessingException {
	      ObjectMapper objectMapper =
	      new ObjectMapper()
	      .registerModule(new ParameterNamesModule())
	      .registerModule(new Jdk8Module())
	      .registerModule(new JavaTimeModule()); 
	      return objectMapper.writeValueAsString(employee);
	   }
	
	private List<Employee> mapEmployeeFromJson(String json)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper =
	    		     new ObjectMapper()
	    		      .registerModule(new ParameterNamesModule())
	    		      .registerModule(new Jdk8Module())
	    		      .registerModule(new JavaTimeModule()); 
	    		 
	      return objectMapper.readValue(json, new TypeReference<List<Employee>>(){});
	   }
}
