package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;

@Service
public class CustomerChangesProducer {

	private static final Logger logger = LoggerFactory.getLogger(CustomerChangesProducer.class);
	
	private KafkaTemplate<String, Customer> kafkaTemplate;
	
	public CustomerChangesProducer(KafkaTemplate<String, Customer> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(Customer data) {
		
		logger.info(String.format("Message sent -> %s", data.toString()));
		
		Message<Customer> msg = MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC,"CustomerTopic")
				.build();
		
		kafkaTemplate.send(msg);
		
	}
	
	
}

