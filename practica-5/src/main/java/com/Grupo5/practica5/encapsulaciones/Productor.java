package com.Grupo5.practica5.encapsulaciones;

import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Productor {


    public Productor() {

    }

    public void enviarMensaje(TramaJSON tramaJSON) throws JMSException {
        //Creando el connection factory indicando el host y puerto, en la trama el failover indica que reconecta de manera
        // automatica
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //Crea un nuevo hilo cuando hacemos a conexión, que no se detiene cuando
        // aplicamos el metodo stop(), para eso tenemos que cerrar la JVM o
        // realizar un close().
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();

        // Creando una sesión no transaccional y automatica.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.
        MessageProducer producer =  null;

        Queue queue = session.createQueue("queue");
        producer = session.createProducer(queue);

        Message message = session.createObjectMessage(tramaJSON);
        producer.send(message);

        //Desconectando la referencia.
        producer.close();
        session.close();
        connection.stop();

    }
}
