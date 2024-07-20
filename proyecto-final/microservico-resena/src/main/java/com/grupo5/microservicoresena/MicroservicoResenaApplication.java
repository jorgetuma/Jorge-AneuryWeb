package com.grupo5.microservicoresena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicoResenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoResenaApplication.class, args);
	}

}
