package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kafka.KafkaJsonProducer;
import com.example.demo.model.User;

@RestController
@RequestMapping("/kafka/json")
public class JsonMessageController {

	private KafkaJsonProducer kafkaProducer;

	public JsonMessageController(KafkaJsonProducer kafkaProducer) {
		super();
		this.kafkaProducer = kafkaProducer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<?> publish(@RequestBody User user){
		kafkaProducer.sendMessage(user);
		return ResponseEntity.ok("Json Message sent to kafka Topic");
	}
}
