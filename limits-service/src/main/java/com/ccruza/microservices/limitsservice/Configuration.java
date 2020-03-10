package com.ccruza.microservices.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component // Para versiones anteriores de spring, se debe colocar esta anotación.
// En nuevas versiones de spring, con esta anotaciòn basta para inicializar como "component"
@ConfigurationProperties("limits-service") // limits-service es el prefijo de application.properties
public class Configuration {

	private int minimum;
	private int maximum;

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public int getMaximum() {
		return maximum;
	}

}
