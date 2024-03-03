package com.Grupo5.practica5.controladores;

import com.Grupo5.practica5.encapsulaciones.Sensor;
import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import com.Grupo5.practica5.servicios.DispositivoService;
import com.Grupo5.practica5.servicios.TramaJSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class DispositivoController {

    private final TramaJSONService tramaJSONService;
    private final DispositivoService dispositivoService;

    @Autowired
    public DispositivoController(TramaJSONService tramaJSONService, DispositivoService dispositivoService) {
        this.tramaJSONService = tramaJSONService;
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
        Sensor sensor = dispositivoService.findDispositivoById(id);
        model.addAttribute("dispositivo",sensor);
        return "/dashboard";
    }

    @RequestMapping("/generar/{id}")
    @ResponseBody
    public TramaJSON generar(@PathVariable("id") int id) {
        return tramaJSONService.generarTramaJson(id);
    }

    @RequestMapping("/generar-lista/{id}")
    @ResponseBody
    public List<TramaJSON> generarLista(@PathVariable("id") int id) {
        return tramaJSONService.generarTramasJSON(id,20);
    }
}
