package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.Pedido;
import com.grupo5.gestionlibros.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PedidoService pedidoService;

    @Autowired
    public AdminController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
        List<Pedido> compras,realizadas, pendientes;
        compras = pedidoService.listarFecha();
        realizadas = pedidoService.listaRealizdas(compras);
        pendientes = pedidoService.listaPedientes(compras);

        model.addAttribute("compras",compras.size());
        model.addAttribute("realizadas",realizadas.size());
        model.addAttribute("pendientes",pendientes.size());
        model.addAttribute("fecha",fecha);
        return "/dashboard";
    }
}
