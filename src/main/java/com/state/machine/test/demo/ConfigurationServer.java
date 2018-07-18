package com.state.machine.test.demo;

import org.springframework.stereotype.Component;

@Component
public class ConfigurationServer {

	private boolean isEnabledMasterHub;
	
	private boolean isEnabledKafkaMasterHub;

	public boolean isEnabledMasterHub() {
		return isEnabledMasterHub;
	}

	public void setEnabledMasterHub(boolean isEnabledMasterHub) {
		this.isEnabledMasterHub = isEnabledMasterHub;
	}

	public boolean isEnabledKafkaMasterHub() {
		return isEnabledKafkaMasterHub;
	}

	public void setEnabledKafkaMasterHub(boolean isEnabledKafkaMasterHub) {
		this.isEnabledKafkaMasterHub = isEnabledKafkaMasterHub;
	}
	
	
}
