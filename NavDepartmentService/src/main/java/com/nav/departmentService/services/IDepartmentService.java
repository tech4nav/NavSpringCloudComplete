package com.nav.departmentService.services;

import java.util.List;

import com.nav.departmentService.entity.DepartmentEntity;
import com.nav.departmentService.model.Department;

public interface IDepartmentService {
	
	Department createDepartment(Department dept);

    List<Department> getAllDepartments();

    boolean deleteDepartment(Long id);

    Department getDeptDetailsById(Long id);

    Department updateDepartment(Long id, Department dpt);

}
