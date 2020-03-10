package com.ccruza.microservices.monedamicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ccruza.microservices.monedamicroservice.entities.Moneda;
import com.ccruza.microservices.monedamicroservice.entities.Usuario;
import com.ccruza.microservices.monedamicroservice.services.MonedaService;

@RestController
@Validated
@RequestMapping(value = "/moneda")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MonedaController {

	@Autowired
	private MonedaService monedaService;

	@Autowired
	private LoginMicroserviceProxy proxy;

	@GetMapping
	public List<Moneda> getAllMonedas() {
		return monedaService.getAllMonedas();
	}

	@PostMapping
	public Moneda createMoneda(@RequestBody Moneda moneda) {
		try {

			Usuario usrNoAuth = new Usuario(moneda.getUsuario(), "", "", "");

			Usuario usrAuth = proxy.Login(usrNoAuth);

			moneda.setToken(usrAuth.getToken());
			moneda.setPort(usrAuth.getPort() + " - " + usrAuth.getConfigValue());

			return monedaService.createMoneda(moneda);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en createMoneda :: " + ex.getMessage());
		}
	}

	@GetMapping("/{descripcion}")
	public Moneda getMonedaByDescripcion(@PathVariable("descripcion") String descripcion) {
		Moneda moneda = monedaService.getMonedaByDescripcion(descripcion);

		if (moneda == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Moneda not found");

		return moneda;
	}

//	@PostMapping("/login")
//	public Usuario login(@RequestBody Usuario usuario) {
//
//		String token = getJWTToken(usuario.getUsername());
//		usuario.setToken(token);
//		return usuario;
//
//	}
//
//	private String getJWTToken(String username) {
//		String secretKey = "mySecretKey";
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ADMIN,SUPER-ADMIN");
//
//		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
//
//		return "Bearer " + token;
//	}

}
