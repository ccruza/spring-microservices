package com.ccruza.microservices.monedamicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ccruza.microservices.monedamicroservice.entities.Moneda;
import com.ccruza.microservices.monedamicroservice.repositories.MonedaRepository;

@Service
public class MonedaService {

	@Autowired
	private MonedaRepository monedaRepository;

	// getAll
	public List<Moneda> getAllMonedas() {
		return monedaRepository.findAll();
	}

	// create
	public Moneda createMoneda(Moneda moneda) {

		Moneda existingMoneda = monedaRepository.findByDescripcion(moneda.getDescripcion());

		if (existingMoneda != null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Moneda ya existe.");

		return monedaRepository.save(moneda);

	}

	// getByDescripcion
	public Moneda getMonedaByDescripcion(String descripcion) {
		return monedaRepository.findByDescripcion(descripcion);
	}
}
