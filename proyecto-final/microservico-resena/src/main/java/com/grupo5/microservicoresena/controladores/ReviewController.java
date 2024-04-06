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
    public void crearReview(@PathVariable("idusuario") String idusuario, @PathVariable("idlibro") String idlibro, @RequestParam("review") String review, @RequestParam("calificacion") int calificacion) {
        reviewService.crearReview(idusuario,idlibro,review,calificacion);
    }

    @PostMapping("/editar/{id}")
    public void editarReview(@PathVariable("id") String id,@RequestParam("review") String review, @RequestParam("calificacion") int calificacion) {
        reviewService.editarReview(id,review,calificacion);
    }

    @RequestMapping("/eliminar/{id}")
    public void eliminarReview(@PathVariable("id") String id) {
        reviewService.eliminarReview(id);
    }

}
