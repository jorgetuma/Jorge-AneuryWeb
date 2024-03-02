package com.Grupo5.practica5;

import com.Grupo5.practica5.encapsulaciones.Consumidor;
import com.Grupo5.practica5.encapsulaciones.Sensor;
import com.Grupo5.practica5.servicios.DispositivoService;
import jakarta.jms.JMSException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica5Application {

	public static void main(String[] args) throws JMSException {
		ApplicationContext applicationContext =  SpringApplication.run(Practica5Application.class, args);

		// Simulando consumidor
		Consumidor consumidor = new Consumidor();
		consumidor.conectar();

		// Simulando los 2 clientes(Dispsitivos/Sensores)
		DispositivoService dispositivoService = (DispositivoService) applicationContext.getBean("dispositivoService");
		Sensor d1 = new Sensor(1);
		Sensor d2 = new Sensor(2);

		d1.generarDatos(20);
		d2.generarDatos(20);
		dispositivoService.getDispositivosActivos().add(d1);
		dispositivoService.getDispositivosActivos().add(d2);
	}

}
