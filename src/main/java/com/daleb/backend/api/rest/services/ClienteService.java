package com.daleb.backend.api.rest.services;

import java.util.List;

import com.daleb.backend.api.rest.models.Cliente;

public interface ClienteService {
	public List<Cliente> findAll();
	public Cliente save(Cliente cliente);
	public void delete(String id);
	public Cliente findById(String id);
	public Cliente update(Cliente cliente);
	public Cliente updateId(Cliente cliente);
}
