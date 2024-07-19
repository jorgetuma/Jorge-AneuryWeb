package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.Pedido;
import com.grupo5.gestionlibros.servicios.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/compras")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/listar/{id}")
    public String listar(Model model, @PathVariable("id") String id) {
        List<Pedido> compras = pedidoService.listarByUsuario(id);
        model.addAttribute("compras",compras);
        return "/compras";
    }

    @GetMapping("/ver/{id}")
    public String visualizar(Model model, @PathVariable("id") String id) {
        Pedido pedido = pedidoService.buscar(id);
        model.addAttribute("pedido",pedido);
        return "/facturaview";
    }
}
