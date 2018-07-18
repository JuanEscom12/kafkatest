package com.state.machine.test.demo;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceCustomerInfo {

	
	public void validateRequest(final StateContext<StatesCustomerInfo, EventsCustomerInfo> context) {
    	final Request request = (Request) context.getMessage().getHeaders().get("request");
    	System.out.println(":: State Validation" + request);
    	// Execute validator
    	final boolean isValid = Boolean.TRUE;
    	if(isValid) {
    		context.getStateMachine().sendEvent(EventsCustomerInfo.CONSULT);
    	} else {
    		// Error message
    		final Error error = new Error();
    		error.setMessage("The request is wrong");
    		final Message<EventsCustomerInfo> message = MessageBuilder
    				.withPayload(EventsCustomerInfo.VALIDATION_ERROR)
    				.setHeader("error", error)
    				.build();
    		context.getStateMachine().sendEvent(message);
    	}
	}
	
}
