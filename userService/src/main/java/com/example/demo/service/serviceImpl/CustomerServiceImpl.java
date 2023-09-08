package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoUserFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	
	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}


	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

//	@KafkaListener(topics = "CustomerTopic" , groupId = "customerData-group")
//	public Customer saveCustomer(Customer customer) {
//		logger.info(String.format("Received Customer Details %s", customer.toString()));
//		Customer c = customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
//		if(c.equals(null)) {
//			logger.info(String.format("--------- %s", customer.toString()));
//			return customerRepository.save(customer);
//		}
//		else {
//			logger.info(String.format("************************* %s", customer.toString()));
//			throw new NoUserFoundException("User Exists");
//
//		}
//	}
	
	@RetryableTopic(attempts = "3",backoff = @Backoff(delay=2000 , multiplier=3.0))
	@KafkaListener(topics = "CustomerTopic" , groupId = "customerData-group")
	public Customer consume(Customer user) throws InterruptedException {
		logger.info(String.format("Json Message Received %s", user.toString()));
		Thread.sleep(10000);
		logger.info(String.format("User Saved to Database %s", user.toString()));
		return customerRepository.save(user);
	}
	
	@KafkaListener(topics = "CustomerTopic" , groupId = "customerData-group-2")
	public void consumer(Customer user) throws InterruptedException {
		logger.info(String.format("Json Message Received to Consumer 2 %s", user.toString()));
		customerRepository.save(user);
	}

	@DltHandler
	public void dlt(Customer user,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws InterruptedException {
		logger.info(String.format("Message to dead letter is %s from topic %s", user.toString(),topic));
	}
	
	public Optional<Customer> getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
			throw new NoUserFoundException("No User Found");
		}
		else {
			return customer;
		}
	}
	
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}


	public Customer updateCustomer(Customer customer, Long id) {
		if(customerRepository.findById(id)==null) {
			throw new NoUserFoundException("No User Found with ID :"+id );
		}
		else {
			Customer c = customerRepository.findById(id).get();
			c.setId(customer.getId());
			c.setUsername(customer.getUsername());
			c.setPassword(customer.getPassword());
			return customerRepository.save(c);
		}
	}


	public Customer login(String username, String password) {
		return customerRepository.findByUsernameAndPassword(username,password);
	}

}
