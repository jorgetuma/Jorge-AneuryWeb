package com.grupo5.microserviciocatalogo.servicios;

import com.grupo5.microserviciocatalogo.colecciones.Libro;
import com.grupo5.microserviciocatalogo.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarCatalogo() {
        return libroRepository.findAll();
    }

    public List<Libro> listarCatalogoByAutor(String autor) {
        return  libroRepository.findAllByAutor(autor);
    }

    public List<Libro> listarCatalogoporGenero(String genero) {
        return  libroRepository.findAllByGenero(genero);
    }

    public List<Libro> listarCatalogoByTitulo(String titulo) {
        return libroRepository.findLibrosByTituloLike(titulo);
    }

    public void insertar(Libro libro) {
        libroRepository.insert(libro);
    }

    public void generarCatalogo() {
        //TODO
    }
}
