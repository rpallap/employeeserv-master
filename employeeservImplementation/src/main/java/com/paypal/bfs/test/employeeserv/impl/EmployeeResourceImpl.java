package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeResourceImpl.class);
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<Employee> employeeGetById(long id) 
			throws EmployeeException {
		logger.info("employeeGetById:" + id);
		Employee employee = employeeService.employeeGetById(id);
		if (employee == null || employee.getId() <= 0) {
			throw new EmployeeException("Employee doesn't exist for id:" + id);
		}
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> saveEmployee(Employee employee) 
			throws EmployeeException {
		logger.info("Employee to save: "+employee);
		
		if(!isEmployeeValid(employee)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(
				employeeService.saveEmployee(employee), HttpStatus.OK);
	}
	
	//Validating Employee details
	public boolean isEmployeeValid(Employee employee) {
		if(!validField(employee.getFirstName())) return false;
		if(!validField(employee.getLastName())) return false;
		if(!validDOB(employee.getDateOfBirth())) return false;
		if(!validField(employee.getAddress().getLine1())) return false;
		if(!validField(employee.getAddress().getCity())) return false;
		if(!validField(employee.getAddress().getState())) return false;
		if(!validField(employee.getAddress().getCountry())) return false;
		if(!validField(employee.getAddress().getZipCode())) return false;
		
		return true;
	}
	
	//Checks min length as 1 and max length as 255
	public boolean validField(String field) {
		if(field != null && field.length() >= 1 && field.length() <= 255)
			return true;
		else return false;
	}
	
	//Checks if DOB is 8 digits 
	public boolean validDOB(String field) {
		if(field != null && field.length() == 8 && field.matches(".*\\d.*"))
			return true;
		else return false;
	}
}
