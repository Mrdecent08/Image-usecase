package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.serviceImpl.CustomerServiceImpl;

public class CustomerServiceTests {

	private CustomerRepository customerRepository;
	private CustomerServiceImpl customerService;
	Customer customer;

	@BeforeEach
	public void setup() {
		customerRepository = Mockito.mock(CustomerRepository.class);
		customerService = new CustomerServiceImpl(customerRepository);
		customer = new Customer(1, "Srinivas", "9963");
	}

	@DisplayName("JUnit test for saveCustomer method")
	@Test
	public void givenCustomerObject_whenSaveCustomer_thenReturnCustomerObject() throws InterruptedException {
		// given - precondition or setup

		given(customerRepository.save(customer)).willReturn(customer);

		Customer savedCustomer = customerService.consume(customer);

		assertThat(savedCustomer).isNotNull();
	}

	
	@DisplayName("JUnit test for getAllCustomers method")
	@Test
	public void givenCustomersList_whenGetAllCustomers_thenReturnCustomersList() {
		// given - precondition or setup

		Customer customer1 = new Customer(2,"Shyam","1234");

		given(customerRepository.findAll()).willReturn(List.of(customer,customer1));

		List<Customer> CustomerList = customerService.getAllCustomers();

		assertThat(CustomerList).isNotNull();
		assertThat(CustomerList.size()).isEqualTo(2);
	}

	

	@DisplayName("JUnit test for getCustomerById method")
	@Test
	public void givenCustomerId_whenGetCustomerById_thenReturnCustomerObject() {
		
		given(customerRepository.findById(1L)).willReturn(Optional.of(customer));

		Customer savedCustomer = customerService.getCustomerById(customer.getId()).get();

		assertThat(savedCustomer).isNotNull();

	}

	@DisplayName("JUnit test for deleteCustomer method")
	@Test
	public void givenCustomerId_whenDeleteCustomer_thenNothing() {
		// given - precondition or setup
		long CustomerId = 1L;

		willDoNothing().given(customerRepository).deleteById(CustomerId);

		customerService.deleteCustomerById(CustomerId);

		// then - verify the output
		verify(customerRepository, times(1)).deleteById(CustomerId);
	}

}
