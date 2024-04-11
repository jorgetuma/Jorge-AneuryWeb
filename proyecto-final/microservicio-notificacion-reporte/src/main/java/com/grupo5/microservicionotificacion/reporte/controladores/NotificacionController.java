package com.grupo5.microservicionotificacion.reporte.controladores;

import com.grupo5.microservicionotificacion.reporte.servicios.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacion")
public class NotificacionController {
    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @RequestMapping("/notificar-registro/{correo}&{username}")
    public void notificarRegistro(@PathVariable("correo") String correo,@PathVariable("username") String userName) {
        String mensaje = "Este correo confirma la creación de su perfil de usuario de nombre:" + userName;
        notificacionService.enviar(correo,mensaje);
    }
}
