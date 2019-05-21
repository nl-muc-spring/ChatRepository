package com.netlight.munich.spring.server.app.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "storage-backend")
public class StorageConfig {
	private String hostName = "http://chat-server-backend";
    private int port = 8001;
    
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getFullUrl() {
		return hostName + ":" + port;
	}
    
    
}
