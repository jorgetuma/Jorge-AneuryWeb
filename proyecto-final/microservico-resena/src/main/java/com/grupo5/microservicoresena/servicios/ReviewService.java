package com.grupo5.microservicoresena.servicios;

import com.grupo5.microservicoresena.entidades.Review;
import com.grupo5.microservicoresena.repositorios.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> listarByLibro(String idLibro) {
        return reviewRepository.findAllByIdLibro(idLibro);
    }

    public void crearReview(String idusuario, String idlibro, String review, int calificacion) {
        Review r = new Review(idusuario,review,idlibro,calificacion);
        reviewRepository.save(r);
    }

    @Transactional
    public void editarReview(String id,String review,int calificacion) {
        Review r = reviewRepository.findByIdReview(id);
        r.setReview(review);
        r.setCalificacion(calificacion);
    }

    public void eliminarReview(String id) {
        Review r = reviewRepository.findByIdReview(id);
        reviewRepository.delete(r);
    }

    public Review buscar(String id) {
        return reviewRepository.findByIdReview(id);
    }
}
