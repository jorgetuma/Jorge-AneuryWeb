package com.grupo5.microserviciocatalogo.controladores;

import com.grupo5.microserviciocatalogo.colecciones.Libro;
import com.grupo5.microserviciocatalogo.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
    private final LibroService libroService;

    @Autowired
    public CatalogoController(LibroService libroService) {
        this.libroService = libroService;
    }

    @RequestMapping("/listar")
    public List<Libro> listarCatalogo() {
        return libroService.listarCatalogo();
    }

    @RequestMapping("/listar-autor/{autor}")
    public List<Libro> listarAutor(@PathVariable("autor") String autor) {
        return libroService.listarCatalogoByAutor(autor);
    }

    @RequestMapping("/listar-genero/{genero}")
    public List<Libro> listarGenero(@PathVariable("genero") String genero) {
        return libroService.listarCatalogoporGenero(genero);
    }

    @RequestMapping("/listar-titulo/{titulo}")
    public List<Libro> listarTitulo(@PathVariable("titulo") String titulo) {
        return libroService.listarCatalogoByTitulo(titulo);
    }
}
