package com.grupo5.microservicoresena.controladores;

import com.grupo5.microservicoresena.entidades.Review;
import com.grupo5.microservicoresena.servicios.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping("/listar/{id}")
    public List<Review> listarByLibro(@PathVariable("id") String id) {
        return reviewService.listarByLibro(id);
    }

    @PostMapping("/crear/{idusuario}&{idlibro}")
    public void crearReview(@PathVariable("idusuario") String idusuario, @PathVariable("idlibro") String idlibro, @RequestBody Review r) {
        String review = r.getReview();
        int calificacion = r.getCalificacion();
        reviewService.crearReview(idusuario,idlibro,review,calificacion);
    }

    @PostMapping("/editar/{id}")
    public void editarReview(@PathVariable("id") String id, @RequestBody Review r) {
        String review = r.getReview();
        int calificacion = r.getCalificacion();
        reviewService.editarReview(id,review,calificacion);
    }

    @GetMapping("/buscar/{id}")
    public Review buscar(@PathVariable("id") String id) {
        return reviewService.buscar(id);
    }

    @RequestMapping("/eliminar/{id}")
    public void eliminarReview(@PathVariable("id") String id) {
        reviewService.eliminarReview(id);
    }

}
