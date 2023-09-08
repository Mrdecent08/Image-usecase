package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class KafkaJsonConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@RetryableTopic(attempts = "3",backoff = @Backoff(delay=2000 , multiplier=3.0))
	@KafkaListener(id="consumer", topics = "kafkaJsonTopic" , groupId = "my-group")
	public void consumer(User user,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws InterruptedException {
		logger.info(String.format("Json Message Received to consumer is %s", user.toString()));
		Thread.sleep(500);
		logger.info(String.format("Json Message Received to consumer After Processing %s", user.toString()));
	}	
	
	@DltHandler
	public void dlt(User user,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws InterruptedException {
		logger.info(String.format("Message to dead letter is %s from topic %s", user.toString(),topic));
	}
	
}
