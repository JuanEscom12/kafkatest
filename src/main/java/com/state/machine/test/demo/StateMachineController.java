package com.state.machine.test.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateMachineController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StateMachine<StatesCustomerInfo, EventsCustomerInfo> stateMachine;
	
	@Autowired
	private KafkaProducer producer;
	
	@RequestMapping("/")
	public String greeting() {
		return "redirect:/states";
	}

	@GetMapping("/states")
	public String getStates() {
		
		logger.info(":: STATES ");
		
		producer.send("Message Test");
		
		final Request request = new Request();
		final Response response  = new Response();
		final Message<EventsCustomerInfo> message = MessageBuilder
				.withPayload(EventsCustomerInfo.VALIDATE)
				.setHeader("request", request)
				//.setHeader("response", response)
				.build();

		stateMachine.sendEvent(message);

		while(!stateMachine.isComplete()) {
			
		}
		
		return "states";
	}



}