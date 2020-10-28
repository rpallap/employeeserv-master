package com.paypal.bfs.test.employeeserv.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.paypal.bfs.test.employeeserv.EmployeeservApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmployeeservApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeservApplicationTest {
	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void verifySaveEmployee() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{"
        		+ "    \"first_name\": \"Test\","
        		+ "    \"last_name\": \"Save\","
        		+ "    \"date_of_birth\": \"19890805\","
        		+ "    \"Address\": {"
        		+ "        \"line1\": \"12349 Metric Blvd\","
        		+ "        \"city\": \"Austin\","
        		+ "        \"state\": \"TX\","
        		+ "        \"country\": \"USA\","
        		+ "        \"zip_code\": \"78758\""
        		+ "    }"
        		+ "}")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.first_name").exists())
		.andExpect(jsonPath("$.last_name").exists())
		.andExpect(jsonPath("$.first_name").value("Test"))
		.andExpect(jsonPath("$.last_name").value("Save"))
		.andDo(print());
	}
}
