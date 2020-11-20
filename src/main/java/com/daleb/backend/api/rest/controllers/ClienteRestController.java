package com.daleb.backend.api.rest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daleb.backend.api.rest.models.Cliente;
import com.daleb.backend.api.rest.models.Region;
import com.daleb.backend.api.rest.repositorys.FacturaRepository;
import com.daleb.backend.api.rest.repositorys.ItemFacturaRepository;
import com.daleb.backend.api.rest.repositorys.ProductoRepository;
import com.daleb.backend.api.rest.services.ClienteService;
import com.daleb.backend.api.rest.services.RegionService;
import com.daleb.backend.api.rest.services.UploadFileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UploadFileService iUploadFileService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private FacturaRepository FacturaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private ItemFacturaRepository itemFacturaRepository;

	@GetMapping("/prueba")
	public Cliente probar() {

		return clienteService.findById("5f73611bb4591f2b1ca2a2a6");

		/*
		 * Factura factura = new Factura(); factura.setCliente(cliente);
		 * factura.setCreateAt(new Date());
		 * factura.setDescripcion("factura de prueba");
		 * factura.setObservacion("observacion de prueba");
		 * 
		 * Producto producto = new Producto(); producto.setCreateAt(new Date());
		 * producto.setNombre("Televisor"); producto.setPrecio(590000.0);
		 * productoRepository.save(producto);
		 * 
		 * ItemFactura itemFactura = new ItemFactura();
		 * itemFactura.setCantidad(5); itemFactura.setProducto(producto);
		 * itemFacturaRepository.save(itemFactura);
		 * 
		 * List<ItemFactura> lista = Arrays.asList(itemFactura);
		 * factura.setItems(lista); FacturaRepository.save(factura);
		 * 
		 * List<Factura> facturas = Arrays.asList(factura);
		 * cliente.setFacturas(facturas); clienteService.updateId(cliente);
		 */
	}

	@GetMapping()
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje",
					"Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente id ".concat(
					id.toString().concat(" No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente,
			BindingResult bindingResult) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(err -> "El campo " + err.getField() + " "
							+ err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.BAD_REQUEST);
		}

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException dae) {
			log.info("Error de creacion de datos " + dae.getMessage());
			response.put("mensaje", "Error al crear cliente en base de datos");
			response.put("error", dae.getMessage().concat(": ")
					.concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response,
				HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,
			BindingResult bindingResult, @PathVariable("id") String id) {

		Cliente findedClient = clienteService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(err -> "El campo " + err.getField() + " "
							+ err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.BAD_REQUEST);
		}

		findedClient.setApellido(cliente.getApellido());
		findedClient.setEmail(cliente.getEmail());
		findedClient.setNombre(cliente.getNombre());
		findedClient.setRegion(cliente.getRegion());

		Cliente updatedClient = null;

		try {
			updatedClient = clienteService.updateId(findedClient);
		} catch (DataAccessException dae) {
			log.info("Error de actualizacion de datos " + dae.getMessage());
			response.put("mensaje",
					"Error al actualizar cliente en base de datos");
			response.put("error", dae.getMessage().concat(": ")
					.concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("cliente", updatedClient);
		response.put("mensaje", "el cliente ha sido actualizado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();

			iUploadFileService.eliminar(nombreFotoAnterior);

			clienteService.delete(id);
		} catch (DataAccessException dae) {
			log.info("Error de eliminacion de datos " + dae.getMessage());
			response.put("mensaje",
					"Error al eliminar cliente en base de datos");
			response.put("error", dae.getMessage().concat(": ")
					.concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response,
				HttpStatus.NO_CONTENT);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/uploads")
	public ResponseEntity<?> upload(
			@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") String id) {
		Map<String, Object> response = new HashMap<String, Object>();

		Cliente cliente = clienteService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;

			try {
				nombreArchivo = iUploadFileService.copiar(archivo);
			} catch (IOException ioe) {

				response.put("mensaje",
						"Error al subir la imagen del cliente ");
				response.put("error", ioe.getMessage().concat(": ")
						.concat(ioe.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response,
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

			String nombreFotoAnterior = cliente.getFoto();

			iUploadFileService.eliminar(nombreFotoAnterior);

			cliente.setFoto(nombreArchivo);
			clienteService.updateId(cliente);

			response.put("cliente", cliente);
			response.put("mensaje",
					"Has subido correctamente la imagen :" + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/uploads/img/{nombrefoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombrefoto) {

		Resource recurso = null;

		try {
			recurso = iUploadFileService.cargar(nombrefoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, headers, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/regiones")
	public ResponseEntity<List<Region>> listarRegiones() {

		List<Region> regiones = regionService.getAllRegions();

		return new ResponseEntity<List<Region>>(regiones, HttpStatus.OK);

	}

}
