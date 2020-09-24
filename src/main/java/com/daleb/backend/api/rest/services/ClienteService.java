package com.daleb.backend.api.rest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daleb.backend.api.rest.models.Cliente;

public interface ClienteService {
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable pageable);
	public Cliente save(Cliente cliente);
	public void delete(String id);
	public Cliente findById(String id);
	public Cliente update(Cliente cliente);
	public Cliente updateId(Cliente cliente);
}
