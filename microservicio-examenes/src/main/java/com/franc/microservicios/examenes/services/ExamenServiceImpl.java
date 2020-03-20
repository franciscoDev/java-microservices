package com.franc.microservicios.examenes.services;

import org.springframework.stereotype.Service;

import com.franc.microservicios.commons.services.CommonServiceImpl;
import com.franc.microservicios.commons.examenes.models.entity.Examen;
import com.franc.microservicios.examenes.models.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

}
