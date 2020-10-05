package com.daleb.backend.api.rest.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daleb.backend.api.rest.services.IUploadFileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IUploadFileServiceImpl implements IUploadFileService {

	private final static String DIRECTORIO_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString());
		Resource recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();

			recurso = new UrlResource(rutaArchivo.toUri());

			log.error("Error al ubicar el recurso");

		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");

		Path path = getPath(nombreArchivo);
		log.info(path.toString());

		Files.copy(archivo.getInputStream(), path);

		return nombreArchivo;
	}

	@Override
	public Boolean eliminar(String nombreFoto) {
		if (nombreFoto != null && !nombreFoto.isEmpty()) {
			Path pathAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoAnterior = pathAnterior.toFile();

			if (archivoAnterior.exists() && archivoAnterior.canRead()) {
				archivoAnterior.delete();
				return true;
			}
		}

		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {

		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
