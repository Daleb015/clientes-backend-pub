package com.daleb.backend.api.rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daleb.backend.api.rest.models.Usuario;
import com.daleb.backend.api.rest.repositorys.UsuarioRepository;
import com.daleb.backend.api.rest.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"User Not Found with username: " + username));

		List<GrantedAuthority> authorithies = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(),
				usuario.getEnable(), true, true, true, authorithies);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario findByUsername(String username) {

		return usuarioRepository.findByUsername(username).orElse(null);
	}

}
