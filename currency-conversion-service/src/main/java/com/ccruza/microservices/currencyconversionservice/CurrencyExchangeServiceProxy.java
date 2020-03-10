package com.ccruza.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
// name -> spring.application.name del application.properties que se va a consumir
// @FeignClient(name = "currency-exchange-service") // se obtendrà desde app.properties (sin pasar por Zuul)
@FeignClient(name = "netflix-zuul-api-gateway-server") // interceptado por Zuul
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	// @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	// @GetMapping("/currency-exchange/from/{from}/to/{to}") // sin pasar por Zuul
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}") // interceptado por Zuul
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
	// En el mètodo, se debe especificar la variable para PathVariable. e.g.
	// @PathVariable("from") String from

}
