package com.nav.departmentService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class DepartmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deptId;
	
	private String deptName;
	
	private String hod;
	

}
