package com.state.machine.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String kafkaTopic;
	
	public void send(final String data) {
	    kafkaTemplate.send(kafkaTopic, data);
	}
	
}
