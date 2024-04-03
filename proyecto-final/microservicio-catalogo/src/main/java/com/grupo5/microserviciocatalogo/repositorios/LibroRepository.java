package com.grupo5.microserviciocatalogo.repositorios;

import com.grupo5.microserviciocatalogo.colecciones.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LibroRepository extends MongoRepository<Libro,String> {
    List<Libro> findAllByAutor(String autor);
    List<Libro> findAllByGenero(String genero);
    List<Libro> findAllByTituloLike(String titulo);
    Libro findLibroById(String id);
}
