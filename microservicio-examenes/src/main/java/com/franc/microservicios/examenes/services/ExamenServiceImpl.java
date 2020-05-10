package com.franc.microservicios.examenes.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franc.microservicios.commons.services.CommonServiceImpl;
import com.franc.microservicios.commons.examenes.models.entity.Asignatura;
import com.franc.microservicios.commons.examenes.models.entity.Examen;
import com.franc.microservicios.examenes.models.repository.AsignaturaRepository;
import com.franc.microservicios.examenes.models.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

	private AsignaturaRepository asignaturaRepository;
	@Override
	@Transactional(readOnly=true)
	public List<Examen> findByNombre(String term) {
		 
		return repository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Asignatura> findAllAsignaturas() {
		 
		return asignaturaRepository.findAll();
	}

}
