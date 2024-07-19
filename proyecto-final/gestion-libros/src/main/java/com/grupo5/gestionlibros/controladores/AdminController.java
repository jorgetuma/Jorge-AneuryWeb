package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.AuthUserDto;
import com.grupo5.gestionlibros.dto.Pedido;
import com.grupo5.gestionlibros.dto.UserDto;
import com.grupo5.gestionlibros.servicios.FeignClient;
import com.grupo5.gestionlibros.servicios.PedidoService;
import feign.FeignException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PedidoService pedidoService;
    private final FeignClient feignClient;

    @Autowired
    public AdminController(PedidoService pedidoService, FeignClient feignClient) {
        this.pedidoService = pedidoService;
        this.feignClient = feignClient;
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

    @GetMapping("/users")
    public String users(HttpServletRequest request, Model model) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }
        try {
            List<UserDto> users = feignClient.getUsers("Bearer " + token);
            model.addAttribute("users", users);
            return "users"; // Return users view if token is valid
        } catch (FeignException.Unauthorized e) {
            return "403";
        }
    }

    @PostMapping("/users")
    public String addUser(HttpServletRequest request, @ModelAttribute("user") AuthUserDto user, Model model) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }
        try {
            feignClient.createUser("Bearer " + token, user);
            return "redirect:/admin/users";
        } catch (FeignException.Unauthorized e) {
            return "403";
        }
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(HttpServletRequest request, @PathVariable int id, @ModelAttribute AuthUserDto authUserDto) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }
        try {
            feignClient.updateUser("Bearer " + token, id, authUserDto);
            return "redirect:/admin/users";
        } catch (FeignException.Unauthorized e) {
            return "403";
        }

    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(HttpServletRequest request, @PathVariable int id) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }
        try {
            feignClient.deleteUser("Bearer " + token, id);
            return "redirect:/admin/users";
        } catch (FeignException.Unauthorized e) {
            return "403";
        }
    }

    private String getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
