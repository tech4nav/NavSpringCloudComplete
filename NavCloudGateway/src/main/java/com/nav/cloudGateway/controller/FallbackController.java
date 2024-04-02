package com.nav.cloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	
	 @GetMapping("/departmentServiceFallBack")
	    public String departmentServiceFallBack() {
	        return "Department Service is down!";
	    }

	    @GetMapping("/employeeServiceFallBack")
	    public String employeeServiceFallBack() {
	        return "Employee Service is down!";
	    }

}
