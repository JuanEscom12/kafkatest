package com.state.machine.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private KafkaProducer producer;

	@Autowired
	private Execution execution;
	
	@Autowired
	private Foo foo;
	
	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	
	@Bean
    public CommandLineRunner init() {

		System.out.println("********************* RESULT: " + execution.process("Everybody"));
		//System.out.println("********************* RESULT: " + foo.message());
		
		return null;
		
//		System.out.println("\n\n:: SENDING MESSAGE TO KAFKA TOPIC ");
//		
//        return args -> {
//        	
//        	producer.send("MASTERHUBCONNECTION");
//        	
//        	System.out.println("\n\n:: MESSAGE DELIVERED TO KAFKA TOPIC ");
//        };
        
    }
		
}
