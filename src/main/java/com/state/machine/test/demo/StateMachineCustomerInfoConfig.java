package com.state.machine.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
@Scope(scopeName="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class StateMachineCustomerInfoConfig extends EnumStateMachineConfigurerAdapter<StatesCustomerInfo, EventsCustomerInfo> {

	@Autowired
	private ServiceCustomerInfo serviceCustomerInfo;

	@Autowired
	private ConfigurationServer configurationServer;
	
	@Override
	public void configure(final StateMachineConfigurationConfigurer<StatesCustomerInfo, EventsCustomerInfo> config)
			throws Exception {
		config
		.withConfiguration()
		.autoStartup(Boolean.TRUE);
	}

	@Override
	public void configure(
			final StateMachineStateConfigurer<StatesCustomerInfo, EventsCustomerInfo> states)
					throws Exception {
		states
		.withStates()
		.initial(StatesCustomerInfo.RECEIVED_REQUEST)
			.state(StatesCustomerInfo.VALIDATION, actionValidation())
				.state(StatesCustomerInfo.CONSULT, actionConsult())
					.state(StatesCustomerInfo.MASTERHUB_CONSULT, actionMasterHubConsult())
						.state(StatesCustomerInfo.OPENLEGACY_CONSULT, actionOpenLegacyConsult())
								.state(StatesCustomerInfo.KAFKA_MASTERHUB, actionOpenLegacyConsult())
									.state(StatesCustomerInfo.RESULT, actionResult())
											.end(StatesCustomerInfo.END);
	}

	@Override
	public void configure(final StateMachineTransitionConfigurer<StatesCustomerInfo, EventsCustomerInfo> transitions)
			throws Exception {
		transitions
		.withExternal()
		.source(StatesCustomerInfo.RECEIVED_REQUEST).target(StatesCustomerInfo.VALIDATION).event(EventsCustomerInfo.VALIDATE)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.VALIDATION).target(StatesCustomerInfo.CONSULT).event(EventsCustomerInfo.CONSULT)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.VALIDATION).target(StatesCustomerInfo.RESULT).event(EventsCustomerInfo.VALIDATION_ERROR)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.CONSULT).target(StatesCustomerInfo.MASTERHUB_CONSULT).event(EventsCustomerInfo.CONSULT_MASTERHUB)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.CONSULT).target(StatesCustomerInfo.OPENLEGACY_CONSULT).event(EventsCustomerInfo.CONSULT_LEGACY)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.MASTERHUB_CONSULT).target(StatesCustomerInfo.RESULT).event(EventsCustomerInfo.RESPONSE)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.OPENLEGACY_CONSULT).target(StatesCustomerInfo.KAFKA_MASTERHUB).event(EventsCustomerInfo.KAFKA)
		.and()
		.withExternal()
		.source(StatesCustomerInfo.KAFKA_MASTERHUB).target(StatesCustomerInfo.RESULT).event(EventsCustomerInfo.RESPONSE);
	}


	public Action<StatesCustomerInfo, EventsCustomerInfo> actionValidation() {
		return new Action<StatesCustomerInfo, EventsCustomerInfo>() {
			@Override
			public void execute(final StateContext<StatesCustomerInfo, EventsCustomerInfo> context) {

				// Validate Request
				serviceCustomerInfo.validateRequest(context);

			}
		};
	}


	public Action<StatesCustomerInfo, EventsCustomerInfo> actionConsult() {
		return new Action<StatesCustomerInfo, EventsCustomerInfo>() {
			@Override
			public void execute(final StateContext<StatesCustomerInfo, EventsCustomerInfo> context) {
				
				if(configurationServer.isEnabledMasterHub()) {
					context.getStateMachine().sendEvent(EventsCustomerInfo.CONSULT_MASTERHUB);
				} else {
					context.getStateMachine().sendEvent(EventsCustomerInfo.CONSULT_LEGACY);
				}

			}
		};
	}


	public Action<StatesCustomerInfo, EventsCustomerInfo> actionMasterHubConsult() {
		return new Action<StatesCustomerInfo, EventsCustomerInfo>() {
			@Override
			public void execute(final StateContext<StatesCustomerInfo, EventsCustomerInfo> context) {

					
			}
		};
	}


	public Action<StatesCustomerInfo, EventsCustomerInfo> actionOpenLegacyConsult() {
		return new Action<StatesCustomerInfo, EventsCustomerInfo>() {
			@Override
			public void execute(final StateContext<StatesCustomerInfo, EventsCustomerInfo> context) {


			}
		};
	}


	public Action<StatesCustomerInfo, EventsCustomerInfo> actionResult() {
		return new Action<StatesCustomerInfo, EventsCustomerInfo>() {
			@Override
			public void execute(final StateContext<StatesCustomerInfo, EventsCustomerInfo> context) {
					

			}
		};
	}
}
