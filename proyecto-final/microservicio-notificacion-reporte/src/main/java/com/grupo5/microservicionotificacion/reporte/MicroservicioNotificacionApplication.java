package com.grupo5.microservicionotificacion.reporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioNotificacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioNotificacionApplication.class, args);
	}

}
