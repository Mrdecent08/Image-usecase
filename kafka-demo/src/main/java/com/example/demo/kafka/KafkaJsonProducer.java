package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.*;

import com.example.demo.model.User;

@Service
public class KafkaJsonProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaJsonProducer.class);
	
	private KafkaTemplate<String, User> kafkaTemplate;

	public KafkaJsonProducer(KafkaTemplate<String, User> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(User data) {
		
		logger.info(String.format("Message sent -> %s", data.toString()));
		
		Message<User> msg = MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC,"kafkaJsonTopic")
				.build();
		
		kafkaTemplate.send(msg);
		
	}
	
	
}
