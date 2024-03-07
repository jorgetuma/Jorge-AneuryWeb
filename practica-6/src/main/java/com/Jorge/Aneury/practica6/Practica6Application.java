package com.Jorge.Aneury.practica6;


import com.Jorge.Aneury.practica6.servicios.SecurityServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica6Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext =	SpringApplication.run(Practica6Application.class, args);
//		UsuarioService usuarioService = (UsuarioService) applicationContext.getBean("usuarioService");
//		usuarioService.crearUsuarios();

		SecurityServices securityServices = (SecurityServices) applicationContext.getBean("securityServices");
		securityServices.crearUsuarios();
	}

}
