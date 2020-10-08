package com.daleb.backend.api.rest.repositorys;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daleb.backend.api.rest.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	public Optional<Usuario> findByUsername(String username);

}
