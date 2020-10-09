package com.daleb.backend.api.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daleb.backend.api.rest.models.Cliente;
import com.daleb.backend.api.rest.repositorys.ClienteRepository;
import com.daleb.backend.api.rest.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	ClienteRepository clienteRepository;


	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente save(Cliente cliente) {

		return clienteRepository.insert(cliente);
	}

	@Override
	public void delete(String id) {
		clienteRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(String id) {

		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public Cliente update(Cliente cliente) {
		Query query = new Query(Criteria.where("_id").is(cliente.getId()));
		Update update = new Update();
		update.set("nombre", cliente.getNombre());
		update.set("apellido", cliente.getApellido());
		return clienteRepository.findById(mongoTemplate.findAndModify(query, update, Cliente.class).getId())
				.orElse(null);
	}

	@Override
	public Cliente updateId(Cliente cliente) {

		return clienteRepository.save(cliente);
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

}
