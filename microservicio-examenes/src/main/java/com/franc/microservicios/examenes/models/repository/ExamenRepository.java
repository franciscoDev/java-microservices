package com.franc.microservicios.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.franc.microservicios.commons.examenes.models.entity.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Long> {

}
