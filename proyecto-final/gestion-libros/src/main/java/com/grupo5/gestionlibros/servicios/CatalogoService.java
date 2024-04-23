package com.grupo5.gestionlibros.servicios;

import com.google.gson.Gson;
import com.grupo5.gestionlibros.dto.Libro;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CatalogoService {

    private final Gson gson = new Gson();
    private final String apiUrl = "http://localhost:8081/catalogo";

    public List<Libro> listar() {
        List<Libro> libros;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String librosJson = client.get()
                .uri( apiUrl +"/listar")
                .retrieve()
                .body(String.class);

        libros = gson.fromJson(librosJson,List.class);

        return libros;
    }

    public List<Libro> listarByAutor(String autor) {
        List<Libro> libros;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String librosJson = client.get()
                .uri( apiUrl + "/listar-autor/" + autor)
                .retrieve()
                .body(String.class);

        libros = gson.fromJson(librosJson,List.class);

        return libros;
    }

    public List<Libro> listarByGenero(String genero) {
        List<Libro> libros;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String librosJson = client.get()
                .uri(apiUrl + "/listar-genero/" + genero)
                .retrieve()
                .body(String.class);

        libros = gson.fromJson(librosJson,List.class);

        return libros;
    }

    public List<Libro> listarByTitulo(String titulo) {
        List<Libro> libros;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String librosJson = client.get()
                .uri(apiUrl + "/listar-titulo/" + titulo)
                .retrieve()
                .body(String.class);

        libros = gson.fromJson(librosJson,List.class);

        return libros;
    }

    public Libro buscar(String id) {
        Libro libro;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String libroJson = client.get()
                .uri(apiUrl + "/buscar/" + id)
                .retrieve()
                .body(String.class);

        libro = gson.fromJson(libroJson,Libro.class);

        return libro;
    }
}
