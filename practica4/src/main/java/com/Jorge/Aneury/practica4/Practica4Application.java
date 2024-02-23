package com.Jorge.Aneury.practica4;


import com.Jorge.Aneury.practica4.servicios.SecurityServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica4Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext =	SpringApplication.run(Practica4Application.class, args);
//		UsuarioService usuarioService = (UsuarioService) applicationContext.getBean("usuarioService");
//		usuarioService.crearUsuarios();

		SecurityServices securityServices = (SecurityServices) applicationContext.getBean("securityServices");
		securityServices.crearUsuarios();
	}

}
