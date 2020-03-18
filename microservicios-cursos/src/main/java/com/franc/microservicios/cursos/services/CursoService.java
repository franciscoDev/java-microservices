package com.franc.microservicios.cursos.services;

import com.franc.microservicios.commons.services.CommonService;
import com.franc.microservicios.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {
	public Curso findCursoByAlumnoId(Long id);
}
