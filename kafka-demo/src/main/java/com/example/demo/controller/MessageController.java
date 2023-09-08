package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kafka.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class MessageController {
	
	private KafkaProducer kafkaProducer;

	public MessageController(KafkaProducer kafkaProducer) {
		super();
		this.kafkaProducer = kafkaProducer;
	}
	
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("msg") String msg){
		kafkaProducer.sendMessage(msg);
		return ResponseEntity.ok("Message Sent to kafka Topic");
	}
}
