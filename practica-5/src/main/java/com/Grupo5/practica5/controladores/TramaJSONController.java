package com.Grupo5.practica5.controladores;

import com.Grupo5.practica5.servicios.TramaJSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TramaJSONController {

    private final TramaJSONService tramaJSONService;

    @Autowired
    public TramaJSONController(TramaJSONService tramaJSONService) {
        this.tramaJSONService = tramaJSONService;
    }
}
