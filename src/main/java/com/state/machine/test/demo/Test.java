package com.state.machine.test.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class Test {

	@Bean
	@Profile("dev")
	public Execution testFunctional() {
		return (map -> {
			final List<String> list = new ArrayList<>();
			list.add("Hola Dev " + map);
			return list;
		});
	}

	@Bean
	@Profile("prod")
	public Execution testFunctionalProd() {
		return (map -> {
			final List<String> list = new ArrayList<>();
			list.add("Hola Prod " + map);
			return list;
		});
	}

	@Bean
	public Foo foo() {
		return new Foo();
	}
	
}
