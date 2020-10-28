package com.paypal.bfs.test.employeeserv.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testEmployeeGetById() {
		Employee employee1 = new Employee();
		employee1.setId(1);
        employee1.setFirstName("BFS");
        employee1.setLastName("Developer");
        employee1.setDateOfBirth("19890805");
        
        Address address = new Address();
        address.setLine1("12349 Metric Blvd");
        address.setCity("Austin");
        address.setState("TX");
        address.setCountry("USA");
        address.setZipCode("78758");
        
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        Employee result = employeeService.employeeGetById(1);
        assertEquals(1, result.getId());
        assertEquals("BFS", result.getFirstName());
        assertEquals("Developer", result.getLastName());
	}
	
	@Test
	public void testSaveEmployee() {
		Employee employee1 = new Employee();
		employee1.setId(1);
        employee1.setFirstName("BFS");
        employee1.setLastName("Developer");
        employee1.setDateOfBirth("19890805");
        
        Address address = new Address();
        address.setLine1("12349 Metric Blvd");
        address.setCity("Austin");
        address.setState("TX");
        address.setCountry("USA");
        address.setZipCode("78758");
        
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        Employee result = employeeService.saveEmployee(employee1);
        assertEquals(1, result.getId());
        assertEquals("BFS", result.getFirstName());
        assertEquals("Developer", result.getLastName());
	}
}
