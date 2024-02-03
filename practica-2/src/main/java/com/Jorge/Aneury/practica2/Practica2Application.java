package com.Jorge.Aneury.practica2;


import com.Jorge.Aneury.practica2.servicios.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica2Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext =	SpringApplication.run(Practica2Application.class, args);
		UsuarioService usuarioService = (UsuarioService) applicationContext.getBean("usuarioService");
		usuarioService.crearUsuarios();
	}

}
