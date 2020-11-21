package com.daleb.backend.api.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daleb.backend.api.rest.models.Factura;
import com.daleb.backend.api.rest.services.FacturaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {

	@Autowired
	private FacturaService facturaService;

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Factura show(@PathVariable String id) {
		return facturaService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		facturaService.delete(id);
	}

}
