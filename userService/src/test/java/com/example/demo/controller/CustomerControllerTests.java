package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class CustomerControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	   WebApplicationContext webApplicationContext;
	

	@Test
	public void givenCustomerObject_whenCreateCustomer_thenReturnSavedCustomer() throws Exception {
		Customer customer = new Customer(1, "Srinivas", "9963");
		String uri = "/customers";
		String inputJson = objectMapper.writeValueAsString(customer);;
		MvcResult mvcResult = MockMvcBuilders.webAppContextSetup(webApplicationContext).build().perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		assertThat(content).isNotNull();
	}

	@Test
	public void getCustomersList() throws Exception {
	   String uri = "/customers";
	   Customer customer = new Customer(1, "Srinivas", "9963");
	   MvcResult mvcResult = MockMvcBuilders.webAppContextSetup(webApplicationContext).build().perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Customer[] customerlist = objectMapper.readValue(content, Customer[].class);
	   assertTrue(customerlist.length >= 0);
	}
	
	@Test
	public void deleteCustomer() throws Exception {
	   String uri = "/customers/2";
	   MvcResult mvcResult = MockMvcBuilders.webAppContextSetup(webApplicationContext).build().perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Successful deleted the Customer with Id : 2");
	}
	
	@Test
	   public void updateCustomer() throws Exception {
	      String uri = "/customers/2";
	      Customer customer = new Customer();
	      customer.setUsername("Lemon");
	      String inputJson = objectMapper.writeValueAsString(customer);
	      MvcResult mvcResult = MockMvcBuilders.webAppContextSetup(webApplicationContext).build().perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertThat(content).isNotNull();
	   }
}
