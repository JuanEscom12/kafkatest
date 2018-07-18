package com.state.machine.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

	@Autowired
	private StateMachine<StatesCustomerInfo, EventsCustomerInfo> stateMachine;

	public void test() {
		
		final Request request = new Request();
		final Response response  = new Response();
		final Message<EventsCustomerInfo> message = MessageBuilder
				.withPayload(EventsCustomerInfo.VALIDATE)
				.setHeader("request", request)
				.setHeader("response", response)
				.build();

		stateMachine.sendEvent(message);

		while(!stateMachine.isComplete()) {

		}

	}

}

