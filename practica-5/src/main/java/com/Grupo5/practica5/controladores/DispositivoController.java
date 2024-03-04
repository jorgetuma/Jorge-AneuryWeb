package com.Grupo5.practica5.controladores;

import com.Grupo5.practica5.encapsulaciones.Sensor;
import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import com.Grupo5.practica5.servicios.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    @Autowired
    public DispositivoController( DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @RequestMapping("/")
    public String listar(Model model) {
        List<Sensor> dispositivos = dispositivoService.getDispositivosActivos();
        model.addAttribute("dispositivos",dispositivos);
        model.addAttribute("size",dispositivos.size());
        return "/listar";
    }

    @RequestMapping("/dashboard/{id}")
    public String dashboard(@PathVariable("id") int id, Model model) {
        List<TramaJSON> tramaJSONList = dispositivoService.obtenerTramasByIdDispositivo(id);
        model.addAttribute("tramasJSON",tramaJSONList);
        model.addAttribute("id",id);
        return "/dashboard";
    }
}
