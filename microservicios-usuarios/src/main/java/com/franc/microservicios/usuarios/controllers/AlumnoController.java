package com.franc.microservicios.usuarios.controllers;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.franc.microservicios.commons.alumnos.models.entity.Alumno;
import com.franc.microservicios.commons.controllers.CommonController;
import com.franc.microservicios.usuarios.services.AlumnoService;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
 
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno ,@PathVariable Long id ){
		Optional<Alumno> optional = service.findById(id);
		
		if( ! optional.isPresent()) { return ResponseEntity.notFound().build();}
		
		Alumno alumnoDB = optional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
	 
}
