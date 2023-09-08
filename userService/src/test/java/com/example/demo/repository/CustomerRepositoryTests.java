package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTests {

	@Autowired
	private CustomerRepository customerRepository;

	@DisplayName("Junit Test to save Customer !")
	@Test
	public void givenCustomerObject_whenSave_thenReturnSuccess() {
		Customer customer = new Customer(1, "Srinivas", "9963");
		Customer savedCustomer = customerRepository.save(customer);
		Assertions.assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isGreaterThan(0);

	}
	
	@DisplayName("Junit Test to get All Customers")
	@Test
	public void givenCustomerList_whenFindAll_thenReturnCustomerList(){
		Customer c1 = new Customer(1,"Srinivas","9963");
		Customer c2 = new Customer(2,"Shaym","5143");
		customerRepository.save(c1);
		customerRepository.save(c2);
		
		List<Customer> customerList = customerRepository.findAll();
		assertThat(customerList).isNotNull();
		assertThat(customerList.size()).isGreaterThanOrEqualTo(2);
	}
	
	@DisplayName("Junit Test to get Customer By Id")
	@Test
	public void givenCustomerObject_whenFindById_thenReturnCustomerObject(){
		Customer c1 = new Customer(1,"Srinivas","9963");
		customerRepository.save(c1);
		
		Optional<Customer> customer = customerRepository.findById((long) 1);
		assertThat(customer).isNotNull();
	}
	
//	@DisplayName("Junit Test to Update Customer By Id")
//	@Test
//	public void givenCustomerObject_whenUpdateById_thenReturnCustomerObject(){
//		Customer c1 = new Customer(1,"Srinivas","9963");
//		customerRepository.save(c1);
//		
//		Customer customer = customerRepository.findById(c1.getId()).get();
//		customer.setUsername("Soulcynic");
//		
//		Customer updatedCustomer = customerRepository.save(customer);
//		System.out.print(updatedCustomer+" "+customer);
//		assertThat(updatedCustomer.getUsername()).isEqualTo("Soulcynic");
//	}
	
	@DisplayName("Junit Test to Delete Customer By Id")
	@Test
	public void givenCustomerObject_whenDeleteById_thenRemoveCustomerObject(){
		Customer c1 = new Customer(1,"Srinivas","9963");
		customerRepository.save(c1);
		
		customerRepository.deleteById((long) 1);
		Optional<Customer> deletedCustomer = customerRepository.findById((long) 1);		
		assertThat(deletedCustomer).isEmpty();
	}
}
