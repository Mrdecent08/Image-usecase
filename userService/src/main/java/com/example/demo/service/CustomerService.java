package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer login(String username, String password);

	Customer updateCustomer(Customer customer, Long id);

	void deleteCustomerById(Long id);

	Optional<Customer> getCustomerById(Long id);
	
	

//	private CustomerRepository customerRepository;
//	
//	private Logger logger = LoggerFactory.getLogger(CustomerService.class);
//	
//	@Autowired
//	public CustomerService(CustomerRepository customerRepository) {
//		super();
//		this.customerRepository = customerRepository;
//	}
//
//
//	public List<Customer> getAllCustomers() {
//		return customerRepository.findAll();
//	}
//
////	@KafkaListener(topics = "CustomerTopic" , groupId = "customerData-group")
////	public Customer saveCustomer(Customer customer) {
////		logger.info(String.format("Received Customer Details %s", customer.toString()));
////		Customer c = customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
////		if(c.equals(null)) {
////			logger.info(String.format("--------- %s", customer.toString()));
////			return customerRepository.save(customer);
////		}
////		else {
////			logger.info(String.format("************************* %s", customer.toString()));
////			throw new NoUserFoundException("User Exists");
////
////		}
////	}
//
//	@KafkaListener(topics = "CustomerTopic" , groupId = "customerData-group")
//	public Customer consume(Customer user) {
//		logger.info(String.format("Json Message Received %s", user.toString()));
//		return customerRepository.save(user);
//	}
//
//	public Optional<Customer> getCustomerById(Long id) {
//		Optional<Customer> customer = customerRepository.findById(id);
//		if(customer.isEmpty()) {
//			throw new NoUserFoundException("No User Found");
//		}
//		else {
//			return customer;
//		}
//	}
//	
//	public void deleteCustomerById(Long id) {
//		customerRepository.deleteById(id);
//	}
//
//
//	public Customer updateCustomer(Customer customer, Long id) {
//		if(customerRepository.findById(id)==null) {
//			throw new NoUserFoundException("No User Found with ID :"+id );
//		}
//		else {
//			Customer c = customerRepository.findById(id).get();
//			c.setId(customer.getId());
//			c.setUsername(customer.getUsername());
//			c.setPassword(customer.getPassword());
//			return customerRepository.save(c);
//		}
//	}
//
//
//	public Customer login(String username, String password) {
//		return customerRepository.findByUsernameAndPassword(username,password);
//	}

}
