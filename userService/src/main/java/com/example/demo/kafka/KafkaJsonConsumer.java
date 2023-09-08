package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;

@Service
public class KafkaJsonConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaJsonConsumer.class);

	@KafkaListener(topics = "CustomerTopic" , groupId = "customerData-group")
	public void consume(Customer user) {
		logger.info(String.format("Json Message Received %s", user.toString()));
	}
}
