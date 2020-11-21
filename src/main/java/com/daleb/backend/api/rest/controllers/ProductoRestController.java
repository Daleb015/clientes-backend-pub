package com.daleb.backend.api.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daleb.backend.api.rest.models.Producto;
import com.daleb.backend.api.rest.services.ProductoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/filtrar/nombre/{palabra}")
	public List<Producto> filtrarProductos(@PathVariable String palabra){
		return productoService.findByNombre(palabra);
	}
	
}
