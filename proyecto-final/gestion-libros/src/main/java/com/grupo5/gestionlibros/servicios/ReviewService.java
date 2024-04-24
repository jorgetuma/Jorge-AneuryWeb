package com.grupo5.gestionlibros.servicios;

import com.google.gson.Gson;
import com.grupo5.gestionlibros.dto.Review;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ReviewService {
    private final Gson gson = new Gson();
    private final String apiUrl = "http://localhost:8083/review";

    public List<Review> listar(String id) {
        List<Review> reviews;
        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String reviewsJson = client.get()
                .uri( apiUrl +"/listar/" + id)
                .retrieve()
                .body(String.class);

        reviews = gson.fromJson(reviewsJson,List.class);

        return reviews;
    }

    public void eliminar(String id) {
        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        client.get()
                .uri( apiUrl +"/eliminar/" + id)
                .retrieve();
    }

    public void crearReview(String idusuario, String idlibro, String review, int calificacion) {
        Review r = new Review(idusuario,review,idlibro,calificacion);
        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        client.post()
                .uri(apiUrl + "/crear/" + idusuario + "&" + idlibro)
                .contentType(MediaType.APPLICATION_JSON)
                .body(r) // representa los datos a enviar como parametros del body
                .retrieve();
    }

    public void editarReview(String id, String review, int calificacion) {
        Review r = new Review();

        r.setReview(review);
        r.setCalificacion(calificacion);

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        client.post()
                .uri(apiUrl + "/editar/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(r) // representa los datos a enviar como parametros del body
                .retrieve();
    }

    public Review buscar(String id) {
        Review review;
        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String reviewJson = client.get()
                .uri( apiUrl +"/buscar/" + id)
                .retrieve()
                .body(String.class);

        review = gson.fromJson(reviewJson,Review.class);

        return review;
    }
}
