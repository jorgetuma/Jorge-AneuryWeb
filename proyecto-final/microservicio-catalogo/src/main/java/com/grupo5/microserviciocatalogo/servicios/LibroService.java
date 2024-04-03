package com.grupo5.microserviciocatalogo.servicios;

import com.grupo5.microserviciocatalogo.colecciones.Libro;
import com.grupo5.microserviciocatalogo.repositorios.LibroRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

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
        return libroRepository.findAllByTituloLike(titulo);
    }

    public Libro buscarLibroById(String id) {
        return libroRepository.findLibroById(id);
    }

    public void generarCatalogo(int cantLibros) {
        Faker faker = new Faker();
        Random rand = new Random();
        for(int i = 0; i < cantLibros; i++) {
            String titulo = faker.book().title();
            String autor = faker.book().author();
            String genero = faker.book().genre();
            String editorial = faker.book().publisher();
            float precio = rand.nextFloat(500.00f);
            DecimalFormat df = new DecimalFormat("#.##"); // para que el precio tenga solo dos cifras después del punto
            String numeroFormateado = df.format(precio);
            precio = Float.parseFloat(numeroFormateado);
            Libro libro = new Libro(titulo,autor,genero,editorial,precio);
            libroRepository.insert(libro);
        }
    }
}
