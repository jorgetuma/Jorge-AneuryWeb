package com.Grupo5.practica5.encapsulaciones;

import com.Grupo5.practica5.servicios.DispositivoService;
import com.google.gson.Gson;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class Suscriptor {
    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    MessageConsumer consumer;

    Gson gson = new Gson();

    DispositivoService dispositivoService;

    public Suscriptor(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    public void conectar() throws JMSException {
        //Creando el connection factory indicando el host y puerto, en la trama el failover indica que reconecta de manera
        // automatica
        factory = new ActiveMQConnectionFactory("admin", "admin", "failover:tcp://localhost:61616");


        //Crea un nuevo hilo cuando hacemos a conexión, que no se detiene cuando
        // aplicamos el metodo stop(), para eso tenemos que cerrar la JVM o
        // realizar un close().
        connection = factory.createConnection();


        // Arrancamos la conexión
        //Puede verlo en direccion por defecto de tu activemq local:
        //http://127.0.0.1:8161/admin/connections.admin
        connection.start();

        // Creando una sesión no transaccional y automatica.
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.

            Topic topic = session.createTopic("sensores_notificacion");
            consumer = session.createConsumer(topic);

        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                TramaJSON tramaJSON = gson.fromJson(textMessage.getText(),TramaJSON.class);
                dispositivoService.insertarTrama(tramaJSON);
                System.out.println("Trama JSON recibida: " + textMessage.getText());
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }


    public void cerrarConexion() throws JMSException {
        connection.stop();
        connection.close();
    }
}
