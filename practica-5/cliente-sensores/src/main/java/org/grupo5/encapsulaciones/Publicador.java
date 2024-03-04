package org.grupo5.encapsulaciones;

import com.google.gson.Gson;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publicador {
    public Publicador() {

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

        Topic topic = session.createTopic("sensores_notificacion");
        producer = session.createProducer(topic);

        // Conversión a JSON y enviar
        Gson gson = new Gson();
        Message message = session.createTextMessage(gson.toJson(tramaJSON));

        producer.send(message);

        //Desconectando la referencia.
        producer.close();
        session.close();
        connection.stop();

    }
}
