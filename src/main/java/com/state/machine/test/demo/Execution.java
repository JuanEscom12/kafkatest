package com.state.machine.test.demo;

import java.util.List;

@FunctionalInterface
public interface Execution {

	List<String> process(String map);
	
}
