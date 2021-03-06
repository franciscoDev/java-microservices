package com.franc.microservicios.usuarios.services;
 
import java.util.List;

import com.franc.microservicios.commons.alumnos.models.entity.Alumno;
import com.franc.microservicios.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno>{

	public List<Alumno> findByNombreOrApellido(String termino);
}
