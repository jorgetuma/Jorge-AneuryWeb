package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.Libro;
import com.grupo5.gestionlibros.servicios.CatalogoService;
import com.grupo5.gestionlibros.servicios.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CatalogoController {

    private final CatalogoService catalogoService;
    private final JwtService jwtService;

    @Autowired
    public CatalogoController(CatalogoService catalogoService, JwtService jwtService) {
        this.catalogoService = catalogoService;
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public String listarCatalago(HttpServletRequest request, Model model) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }
        int id = jwtService.getId(token);
        List<Libro> libros = catalogoService.listar();
        model.addAttribute("libros",libros);
        model.addAttribute("userId",id);
        return "libros";
    }

    @GetMapping("catalogo/listar-titulo/{titulo}")
    public String listarCatalagoBytitulo(HttpServletRequest request, Model model, @PathVariable("titulo") String titulo) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }

        List<Libro> libros = catalogoService.listarByTitulo(titulo);
        int id = jwtService.getId(token);

        model.addAttribute("userId", id);
        model.addAttribute("libros",libros);
        return "/libros";
    }

    @GetMapping("catalogo/listar-autor/{autor}")
    public String listarCatalagoByAutor(HttpServletRequest request, Model model, @PathVariable("autor") String autor) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }

        if (autor == null || autor.isEmpty()) {
            return "/libros";
        }
        List<Libro> libros = catalogoService.listarByAutor(autor);
        int id = jwtService.getId(token);

        model.addAttribute("userId", id);
        model.addAttribute("libros",libros);
        return "/libros";
    }

    @GetMapping("catalogo/listar-genero/{genero}")
    public String listarCatalagoByGenero(HttpServletRequest request, Model model, @PathVariable("genero") String genero) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return "403";
        }

        if (genero == null || genero.isEmpty()) {
            return "/libros";
        }
        List<Libro> libros = catalogoService.listarByGenero(genero);
        int id = jwtService.getId(token);

        model.addAttribute("userId", id);
        model.addAttribute("libros",libros);
        return "/libros";
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
