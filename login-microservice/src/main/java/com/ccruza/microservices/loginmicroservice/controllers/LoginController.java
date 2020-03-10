package com.ccruza.microservices.loginmicroservice.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccruza.microservices.loginmicroservice.configurations.Configuration;
import com.ccruza.microservices.loginmicroservice.entities.Usuario;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@Validated
@RequestMapping(value = "/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	private Environment environment;

	@Autowired
	private Configuration configuration;

	@PostMapping
	public Usuario login(@RequestBody Usuario usuario) {

		String token = getJWTToken(usuario.getUsername());
		usuario.setToken(token);
		usuario.setPort(environment.getProperty("local.server.port"));
		usuario.setConfigValue(configuration.getConfigValue());
		return usuario;

	}

	@PostMapping("/failure")
	@HystrixCommand(fallbackMethod = "fallbackFailedLogin")
	public Usuario loginFailure() {
		throw new RuntimeException("Login not available");
	}

	public Usuario fallbackFailedLogin() {
		return new Usuario("Carlos", "Failed Token", "0000", "NULL");
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ADMIN,SUPER-ADMIN");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
