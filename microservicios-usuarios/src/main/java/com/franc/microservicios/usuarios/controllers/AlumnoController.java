package com.franc.microservicios.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.franc.microservicios.commons.alumnos.models.entity.Alumno;
import com.franc.microservicios.commons.controllers.CommonController;
import com.franc.microservicios.usuarios.services.AlumnoService;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
 
	@GetMapping("/upload/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		
		Optional<Alumno> optional = service.findById(id);
		
		if ( ! optional.isPresent() || optional.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(optional.get().getFoto());
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno 
									,BindingResult result
									,@PathVariable Long id
									){
		
		if( result.hasErrors())return this.validar(result);
		
		Optional<Alumno> optional = service.findById(id);
		
		if( ! optional.isPresent()) { return ResponseEntity.notFound().build();}
		
		Alumno alumnoDB = optional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	@PutMapping("/editar-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno 
									,BindingResult result
									,@PathVariable Long id
									,@RequestParam MultipartFile archivo) throws IOException{
		
		if( result.hasErrors())return this.validar(result);
		
		Optional<Alumno> optional = service.findById(id);
		
		if( ! optional.isPresent()) { return ResponseEntity.notFound().build();}
		
		Alumno alumnoDB = optional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		if( ! archivo.isEmpty() )alumnoDB.setFoto(archivo.getBytes());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}

	@PostMapping("/crear-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno
										, BindingResult result
										,@RequestParam MultipartFile archivo) throws IOException {
		
		if( ! archivo.isEmpty() )alumno.setFoto(archivo.getBytes());
		return super.crear(alumno, result);
	}
	
	
	 
}
