package com.daleb.backend.api.rest.services;

import com.daleb.backend.api.rest.models.Usuario;

public interface UsuarioService {
	public Usuario findByUsername(String username);
}
