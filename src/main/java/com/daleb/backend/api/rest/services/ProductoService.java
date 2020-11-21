package com.daleb.backend.api.rest.services;

import java.util.List;

import com.daleb.backend.api.rest.models.Producto;

public interface ProductoService {
	public List<Producto> findByNombre(String term);
}
