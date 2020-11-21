package com.daleb.backend.api.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daleb.backend.api.rest.models.Producto;
import com.daleb.backend.api.rest.repositorys.ProductoRepository;
import com.daleb.backend.api.rest.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {

		return productoRepository.findByNombreContainingIgnoreCase(term);
	}

}
