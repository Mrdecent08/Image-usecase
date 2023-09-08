package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "kafkaTopic" , groupId = "my-group")
	public void consume(String message) {
		logger.info(String.format("Message Received %s", message));
	}
}
