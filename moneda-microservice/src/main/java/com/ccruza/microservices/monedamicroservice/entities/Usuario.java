package com.ccruza.microservices.monedamicroservice.entities;

public class Usuario {

	private String username;
	private String token;
	private String port;
	private String configValue;

	public Usuario() {

	}

	public Usuario(String username, String token, String port, String configValue) {
		this.username = username;
		this.token = token;
		this.port = port;
		this.configValue = configValue;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

}
