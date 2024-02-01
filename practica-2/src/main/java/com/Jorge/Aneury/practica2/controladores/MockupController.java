package com.Jorge.Aneury.practica2.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MockupController {

    @GetMapping("/")
    public String index() {
        return "redirect:listar-mockup";
    }

    @GetMapping("listar-mockup")
    public String listarMackups() {
        return "/listar-mockup";
    }

    @GetMapping("crear-mockup")
    public String crearMockups() {
        return "/crear-mockup";
    }
}
