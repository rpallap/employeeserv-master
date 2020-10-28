package com.paypal.bfs.test.employeeserv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeservApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeservApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner setup(EmployeeRepository employeeRepository) { 
    	return (args) -> {
    		
    		Employee employee1 = new Employee();
            employee1.setFirstName("BFS");
            employee1.setLastName("Developer");
            employee1.setDateOfBirth("19890805");
            
            Employee employee2 = new Employee();
            employee2.setFirstName("Raghuram");
            employee2.setLastName("Pallapotu");
            employee2.setDateOfBirth("19890805");
            
            Address address = new Address();
            address.setLine1("12349 Metric Blvd");
            address.setCity("Austin");
            address.setState("TX");
            address.setCountry("USA");
            address.setZipCode("78758");
            
            employee1.setAddress(address);
    		employeeRepository.save(employee1);
    		
    		employee2.setAddress(address);
    		employeeRepository.save(employee2);
    	};
    }
}