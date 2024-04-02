package com.nav.departmentService.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nav.departmentService.entity.DepartmentEntity;
import com.nav.departmentService.model.Department;
import com.nav.departmentService.model.Employee;
import com.nav.departmentService.repository.DepartmentRepository;

@Service
public class DepartmentServices implements IDepartmentService {

	private DepartmentRepository depRep;
	
	@Autowired
	private RestTemplate restTemp;
	
	public DepartmentServices(DepartmentRepository depRep) {
		this.depRep = depRep;
	}

	@Override
	public Department createDepartment(Department dept) {
		DepartmentEntity dptEntity= new DepartmentEntity();
		BeanUtils.copyProperties(dept, dptEntity);
		depRep.save(dptEntity);
		return dept;
	}

	@Override
	public List<Department> getAllDepartments() {
		List<DepartmentEntity> lst= depRep.findAll();
		List<Department> rlt=lst.stream().map(m ->new Department(m.getDeptId(), m.getDeptName(), m.getHod(),null))
		.collect(Collectors.toList());
		
		return rlt;
	}

	@Override
	public boolean deleteDepartment(Long id) {
		depRep.deleteById(id);
		return true;
	}

	@Override
	public Department getDeptDetailsById(Long id) {
		DepartmentEntity de= depRep.findById(id).get();
		Department d= new Department();
		BeanUtils.copyProperties(de, d);
		// direct call using restTemplate 
		Employee[] employees = fetchByDirectlyCallingUsingRestTemp();
		d.setEmpList(Arrays.asList(employees));
		return d;
		
	}
	
	private Employee[] fetchByDirectlyCallingUsingRestTemp() {
		//RestTemplate restTemp= new RestTemplate();
		ResponseEntity<Employee[]> response =
				restTemp.getForEntity(
				 // "http://localhost:8080/empApi/v1/employees",
						"http://NAV-EMPLOYEE-SERVICE/empApi/v1/employees",
				  Employee[].class);
				Employee[] employees = response.getBody();
		return employees;
	}

	@Override
	public Department updateDepartment(Long id, Department dpt) {
		DepartmentEntity de=depRep.findById(id).get();
		de.setDeptName(dpt.getDeptName());
		de.setHod(dpt.getHod());
		depRep.save(de);
		return dpt;
	}

}
