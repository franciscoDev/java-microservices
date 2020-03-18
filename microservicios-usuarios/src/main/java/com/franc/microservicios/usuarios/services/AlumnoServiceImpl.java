package com.franc.microservicios.usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franc.microservicios.commons.alumnos.models.entity.Alumno;
import com.franc.microservicios.commons.services.CommonServiceImpl;
import com.franc.microservicios.usuarios.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findByNombreOrApellido(String termino) {
		return repository.findByNombreOrApellido(termino);
	}

	
}
