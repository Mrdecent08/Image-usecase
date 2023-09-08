package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic kafkaTopic() {
		return TopicBuilder.name("kafkaTopic")
				.partitions(5)
				.build();
	}
	
	@Bean
	public NewTopic kafkaJsonTopic() {
		return TopicBuilder.name("kafkaJsonTopic")
				.partitions(5)
				.config(TopicConfig.RETENTION_MS_CONFIG, "10")
				.build();
	}
}
