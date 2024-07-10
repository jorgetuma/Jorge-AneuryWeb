package com.grupo5.microservicioautenticacion;

import com.grupo5.microservicioautenticacion.dto.AuthUserDto;
import com.grupo5.microservicioautenticacion.servicios.AuthUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioAutenticacionApplication {
	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(MicroservicioAutenticacionApplication.class, args);
		AuthUserService authUserService = (AuthUserService) applicationContext.getBean("authUserService");

		authUserService.save(new AuthUserDto("admin", "admin@email.com", "admin", "ADMIN"));

	}

}
