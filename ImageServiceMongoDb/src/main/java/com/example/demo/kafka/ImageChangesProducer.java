package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Image;

@Service
public class ImageChangesProducer {

	private static final Logger logger = LoggerFactory.getLogger(ImageChangesProducer.class);

	private KafkaTemplate<String, Image> kafkatemplate;

	public ImageChangesProducer(KafkaTemplate<String, Image> kafkatemplate) {
		super();
		this.kafkatemplate = kafkatemplate;
	}

	public void sendMessage(Image data) {

		logger.info(String.format("Message sent -> %s", data.toString()));

		org.springframework.messaging.Message<Image> msg = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "ImageTopic").build();

		kafkatemplate.send(msg);

	}

}
