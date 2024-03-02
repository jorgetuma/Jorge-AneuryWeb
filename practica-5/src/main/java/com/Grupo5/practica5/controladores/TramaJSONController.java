package com.Grupo5.practica5.controladores;

import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import com.Grupo5.practica5.servicios.TramaJSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class TramaJSONController {

    private final TramaJSONService tramaJSONService;

    @Autowired
    public TramaJSONController(TramaJSONService tramaJSONService) {
        this.tramaJSONService = tramaJSONService;
    }

    @RequestMapping("/dashboard/{id}")
    public String dashboard(@PathVariable("id") int id) {
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
        return tramaJSONService.generarTramasJSON(id);
    }
}
