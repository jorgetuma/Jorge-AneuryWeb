package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.Libro;
import com.grupo5.gestionlibros.servicios.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    private final CatalogoService catalogoService;

    @Autowired
    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/listar")
    public String listarCatalago(Model model) {
        List<Libro> libros = catalogoService.listar();
        model.addAttribute("libros",libros);
        return "/libros";
    }

    @GetMapping("/listar-titulo/{titulo}")
    public String listarCatalagoBytitulo(Model model, @PathVariable("titulo") String titulo) {
        List<Libro> libros = catalogoService.listarByTitulo(titulo);
        model.addAttribute("libros",libros);
        return "/libros";
    }

    @GetMapping("/listar-autor/{autor}")
    public String listarCatalagoByAutor(Model model, @PathVariable("autor") String autor) {
        List<Libro> libros = catalogoService.listarByAutor(autor);
        model.addAttribute("libros",libros);
        return "/libros";
    }

    @GetMapping("/listar-genero/{genero}")
    public String listarCatalagoByGenero(Model model, @PathVariable("genero") String genero) {
        List<Libro> libros = catalogoService.listarByGenero(genero);
        model.addAttribute("libros",libros);
        return "/libros";
    }
}
