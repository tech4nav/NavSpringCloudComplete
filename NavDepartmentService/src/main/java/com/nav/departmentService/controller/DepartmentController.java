package com.nav.departmentService.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nav.departmentService.model.Department;
import com.nav.departmentService.model.Employee;
import com.nav.departmentService.services.IDepartmentService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/deptApi/v1/")
public class DepartmentController {
	
	
	
	
	private IDepartmentService deptService;
	
	public DepartmentController(IDepartmentService deptService) {
		this.deptService = deptService;
	}

	@PostMapping("/departments")
	public Department createDepartment(@RequestBody Department dep) {
		return deptService.createDepartment(dep);
	}
	
	@GetMapping("/departments")
	public List<Department> getAllDepartments()
	{
		return deptService.getAllDepartments();
	}
	
	@DeleteMapping("/department/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
		boolean res=deptService.deleteDepartment(id);
		return ResponseEntity.ok("is department deleted"+res);
	}
	
	@PutMapping("/department/{id}")
	private ResponseEntity<Department> updateDepartment(@PathVariable Long id,@RequestBody Department dep){
		Department dp=deptService.updateDepartment(id, dep);
		
		return ResponseEntity.ok(dp);
		
	}
	
	@GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Department dpt = null;
		dpt = deptService.getDeptDetailsById(id);
		
		/*
		 * Employee[] employees = fetchByDirectlyCallingUsingRestTemp();
		 * dpt.setEmpList(Arrays.asList(employees));
		 */
        return ResponseEntity.ok(dpt);
    }

	
	  


}
