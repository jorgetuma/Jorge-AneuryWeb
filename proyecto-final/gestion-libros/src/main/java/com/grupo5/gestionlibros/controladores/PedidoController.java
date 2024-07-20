package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.Pedido;
import com.grupo5.gestionlibros.servicios.FeignClient;
import com.grupo5.gestionlibros.servicios.JwtService;
import com.grupo5.gestionlibros.servicios.PedidoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
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
    private final FeignClient feignClient;
    private final JwtService jwtService;

    public PedidoController(PedidoService pedidoService, FeignClient feignClient, JwtService jwtService) {
        this.pedidoService = pedidoService;
        this.feignClient = feignClient;
        this.jwtService = jwtService;
    }

    @GetMapping("/listar/{id}")
    public String listar(HttpServletRequest request, Model model, @PathVariable("id") int id) {
        String token = jwtService.getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }
        List<Pedido> compras = feignClient.listarByUsuario("Bearer " + token, id);

        model.addAttribute("userId", id);
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
