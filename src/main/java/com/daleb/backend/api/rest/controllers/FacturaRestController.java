package com.daleb.backend.api.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daleb.backend.api.rest.models.Factura;
import com.daleb.backend.api.rest.services.FacturaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {

	@Autowired
	private FacturaService facturaService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Factura show(@PathVariable String id) {
		return facturaService.findById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		facturaService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		
		try {
			log.info(new ObjectMapper().writeValueAsString(factura));
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		
		
		return facturaService.save(factura);
	}

}
