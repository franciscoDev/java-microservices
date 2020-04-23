package com.franc.microservicios.examenes.services;

import com.franc.microservicios.commons.services.CommonService;

import java.util.List;

import com.franc.microservicios.commons.examenes.models.entity.Examen;

public interface ExamenService extends CommonService<Examen>{
	
	public List<Examen> findByNombre(String term);

}
