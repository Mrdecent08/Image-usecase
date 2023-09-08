package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class KafkaJsonConsumer1 {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(id="consumer1",topics = "kafkaJsonTopic" , groupId = "my-group-a")
	public void consumer1(User user) throws InterruptedException {
		logger.info(String.format("Json Message Received to consumer 1 is %s", user.toString()));
		Thread.sleep(5000);
		logger.info(String.format("Json Message Received to consumer 1 After Processing %s", user.toString()));
	}	
	
}
