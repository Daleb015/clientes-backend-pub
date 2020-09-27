package com.daleb.backend.api.rest.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daleb.backend.api.rest.models.Cliente;
import com.daleb.backend.api.rest.services.ClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping()
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente id ".concat(id.toString().concat(" No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult bindingResult) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			ZoneId zoneIdCol = ZoneId.of("America/Bogota");
			ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneIdCol);
			cliente.setCreateAt(zonedDateTime);
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException dae) {
			log.info("Error de creacion de datos " + dae.getMessage());
			response.put("mensaje", "Error al crear cliente en base de datos");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult bindingResult,
			@PathVariable("id") String id) {

		Cliente findedClient = clienteService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		findedClient.setApellido(cliente.getApellido());
		findedClient.setEmail(cliente.getEmail());
		findedClient.setNombre(cliente.getNombre());

		Cliente updatedClient = null;

		try {
			updatedClient = clienteService.updateId(findedClient);
		} catch (DataAccessException dae) {
			log.info("Error de actualizacion de datos " + dae.getMessage());
			response.put("mensaje", "Error al actualizar cliente en base de datos");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("cliente", updatedClient);
		response.put("mensaje", "el cliente ha sido actualizado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);
		} catch (DataAccessException dae) {
			log.info("Error de eliminacion de datos " + dae.getMessage());
			response.put("mensaje", "Error al eliminar cliente en base de datos");
			response.put("error", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}

}
