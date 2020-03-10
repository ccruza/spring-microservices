package com.ccruza.microservices.monedamicroservice.controllers;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccruza.microservices.monedamicroservice.entities.Usuario;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "login-microservice")
public interface LoginMicroserviceProxy {

	@PostMapping("/login-microservice/login")
	public Usuario Login(@RequestBody Usuario usuario);

}
