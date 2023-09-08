package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoUserFoundException;
import com.example.demo.kafka.CustomerChangesProducer;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
		name = "CRUD REST APIs for Customer DB"
	)
public class CustomerController {
	
	private CustomerChangesProducer customerChangesProducer;

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private CustomerService customerService;

	
	public CustomerController(CustomerChangesProducer customerChangesProducer, CustomerService customerService) {
		super();
		this.customerChangesProducer = customerChangesProducer;
		this.customerService = customerService;
	}

	@Operation(
			summary = "Retrieve All Customers Rest API"
	)
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		logger.info("Retrieving All Customers Using PostgreSQL DB");
		return customerService.getAllCustomers();
	}
	
	@Operation(
			summary = "Create Customer Rest API",
			description = "Creating a new Customer into DB"
	)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		logger.info("Saving of Customer Details IN PostgreSQL DB");
		customerChangesProducer.sendMessage(customer);
		logger.info(String.format("Message To Kafka is %s", customer.toString()));		
//		return customerService.saveCustomer(customer);
		return customer;
	}
	
	@PostMapping("/customers/register")
	public Customer registerNewCustomer(@RequestBody Customer customer) {
		logger.info("Saving of Customer Details IN PostgreSQL DB");
		customerChangesProducer.sendMessage(customer);
		logger.info(String.format("Message To Kafka is %s", customer.toString()));		
//		return customerService.saveCustomer(customer);
		return customer;
	}
	
	@Operation(
			summary = "Retrieving a Customer Using ID Rest API"
	)
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		logger.info("Retrieving of Customer Based On Customer ID Using PostgreSQL DB");
		Optional<Customer> customer = customerService.getCustomerById(id);
		if(customer==null) {
			throw new NoUserFoundException("No User Found with ID : "+id);
		}
		return customer.get();
	}
	
	@Operation(
			summary = "Deleting a Customer Using ID Rest API"
	)
	@DeleteMapping("/customers/{id}")
	public String deleteCustomerById(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
		return "Successful deleted the Customer with Id : "+id;
	}
	
	@Operation(
			summary = "Updating a Customer Using ID Rest API"
	)
	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable Long id) {
		logger.info("Updating of Customer Details Based On Id In PostgreSQL DB");
		customer.setId(id);
		return customerService.updateCustomer(customer,id);
	}
	
	@Operation(
			summary = "Login Customer Rest API"
	)
	@GetMapping("/customers/login")
	public boolean customerLogin(@RequestParam("username") String username,@RequestParam("password") String password) {
		logger.info("Login Functionality!");
		return customerService.login(username,password) != null;
	}
}
