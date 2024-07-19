package com.grupo5.microserviciocarritocompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioCarritocomprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCarritocomprasApplication.class, args);
	}

}
