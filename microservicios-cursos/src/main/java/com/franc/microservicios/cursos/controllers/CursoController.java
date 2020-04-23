package com.franc.microservicios.cursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.franc.microservicios.commons.alumnos.models.entity.Alumno;
import com.franc.microservicios.commons.controllers.CommonController;
import com.franc.microservicios.commons.examenes.models.entity.Examen;
import com.franc.microservicios.cursos.models.entity.Curso;
import com.franc.microservicios.cursos.services.CursoService;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso,@PathVariable Long id){
		
		Optional<Curso> optional = this.service.findById(id);
		if( ! optional.isPresent())return ResponseEntity.notFound().build();
		Curso cursoDB = optional.get();
		cursoDB.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@PutMapping("/{id}/alumnos/asignar")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos ,@PathVariable Long id){
		
		Optional<Curso> optional = this.service.findById(id);
		if( ! optional.isPresent())return ResponseEntity.notFound().build();
		Curso cursoDB = optional.get();
		 
		alumnos.forEach(a->{
			cursoDB.addAlumno(a);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@PutMapping("/{id}/alumnos/eliminar")
	public ResponseEntity<?> eliminarAlumnos(@RequestBody List<Alumno> alumnos ,@PathVariable Long id){
		
		Optional<Curso> optional = this.service.findById(id);
		if( ! optional.isPresent())return ResponseEntity.notFound().build();
		Curso cursoDB = optional.get();
		 
		alumnos.forEach(a->{
			cursoDB.removeAlumno(a);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@DeleteMapping("/{id}/alumnos/eliminar")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno ,@PathVariable Long id){
		
		Optional<Curso> optional = this.service.findById(id);
		if( ! optional.isPresent())return ResponseEntity.notFound().build();
		Curso cursoDB = optional.get();
		cursoDB.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> findByAlumno(@PathVariable Long id){
		return ResponseEntity.ok(service.findCursoByAlumnoId(id));
	}
	
	@PutMapping("/{id}/examen/asignar")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes ,@PathVariable Long id){
		
		Optional<Curso> optional = this.service.findById(id);
		if( ! optional.isPresent())return ResponseEntity.notFound().build();
		Curso cursoDB = optional.get();
		 
		examenes.forEach(e->{
			cursoDB.addExamen(e);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@DeleteMapping("/{id}/examen/eliminar")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen ,@PathVariable Long id){
		
		Optional<Curso> optional = this.service.findById(id);
		if( ! optional.isPresent())return ResponseEntity.notFound().build();
		Curso cursoDB = optional.get();
		cursoDB.removeExamen(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
}
