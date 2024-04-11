package com.grupo5.microservicionotificacion.reporte.colecciones;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notificacion {
    @Id
    private String id;
    private String correo;
    private String mensaje;

    public Notificacion() {

    }

    public Notificacion(String correo, String mensaje) {
        this.correo = correo;
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
