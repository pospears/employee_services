package com.exlservice.search.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exlservice.search.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Employee findById(long id);
  List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndStartDateAndEndDate(String firstName, String lastName, LocalDate startDate, LocalDate endDate);
   List<Employee> findByEmail(String email);
}