package com.grupo5.microserviciocatalogo;

import com.grupo5.microserviciocatalogo.servicios.LibroService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MicroservicioCatalogoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(MicroservicioCatalogoApplication.class, args);
		LibroService libroService = (LibroService) applicationContext.getBean("libroService");

		// generando el catalogo de libros con el datafaker
		libroService.generarCatalogo(30);
	}

}
