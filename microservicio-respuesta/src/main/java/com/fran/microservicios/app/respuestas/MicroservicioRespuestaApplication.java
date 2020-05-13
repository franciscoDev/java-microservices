package com.fran.microservicios.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.fran.microservicios.app.respuestas.models.entity"
	,"com.franc.microservicios.commons.alumnos.models.entity"
	,"com.franc.microservicios.commons.examenes.models.entity"
	
})
public class MicroservicioRespuestaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRespuestaApplication.class, args);
	}

}
