package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeService {
	
	public Employee employeeGetById(long id);
	public Employee saveEmployee(Employee employee);
}
