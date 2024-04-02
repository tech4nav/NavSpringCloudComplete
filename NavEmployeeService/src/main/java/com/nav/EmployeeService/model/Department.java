package com.nav.EmployeeService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	private long deptId;
	
	private String deptName;
	
	private String hod;
}
