package com.ccruza.microservices.monedamicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccruza.microservices.monedamicroservice.entities.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Long>{

	Moneda findByDescripcion(String descripcion);
}
