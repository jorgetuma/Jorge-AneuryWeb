package com.Grupo5.practica5;

import com.Grupo5.practica5.encapsulaciones.Sensor;
import com.Grupo5.practica5.encapsulaciones.Suscriptor;
import com.Grupo5.practica5.servicios.DispositivoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica5Application {

	public static void main(String[] args) throws JMSException {
		ApplicationContext applicationContext =  SpringApplication.run(Practica5Application.class, args);

		// Simulando lod dispositivos activos
		DispositivoService dispositivoService = (DispositivoService) applicationContext.getBean("dispositivoService");
		Sensor s1 = new Sensor(1);
		Sensor s2 = new Sensor(2);
		dispositivoService.insertarDispositivo(s1);
		dispositivoService.insertarDispositivo(s2);

		// Inicializando el servidor suscriptor
		Suscriptor suscriptor = new Suscriptor(dispositivoService);
		suscriptor.conectar();
	}

}
