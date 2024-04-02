package com.nav.EmployeeService.services;

import java.util.List;

import com.nav.EmployeeService.model.Employee;

public interface IEmployeeService {
	
	Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);
}
