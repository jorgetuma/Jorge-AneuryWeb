package com.grupo5.microservicionotificacion.reporte.servicios;

import com.grupo5.microservicionotificacion.reporte.colecciones.Notificacion;
import com.grupo5.microservicionotificacion.reporte.repositorios.NotificacionRepository;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static j2html.TagCreator.*;

@Service
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public void enviar(String correo,String mensaje) {
        Notificacion notificacion = new Notificacion(correo,mensaje);
        //Configurando el servidor.
        Mailer mailer = MailerBuilder
                .withSMTPServer("host.correo", 587, "", "")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();


        //Creando mensaje HTML.
        String html = body(
                h1("Gestion de libros grupo 5"),
                h2(mensaje),
                h2("Link a la app: " + a("http://localhost:8080/"))
        ).render();

        //Configurando el Correo para ser enviado.
        //https://generator.email/qkamal@alpicley.gq
        Email email = EmailBuilder.startingBlank()
                .from("noreply@grupo5.com")
                .to("correo", correo)
                .withReplyTo("Soporte", "soporte@grupo5.com")
                .withSubject("Confirmacion registro del proyectio final grupo 5 - "+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()))
                .withHTMLText(html)
                .withReturnReceiptTo()
                .withBounceTo("bounce@grupo5")
                .buildEmail();

        //Enviando el mensaje:
        mailer.sendMail(email);
        notificacionRepository.save(notificacion);
    }
}
