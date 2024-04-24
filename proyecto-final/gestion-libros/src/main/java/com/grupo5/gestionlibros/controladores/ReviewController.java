package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.Libro;
import com.grupo5.gestionlibros.dto.Review;
import com.grupo5.gestionlibros.servicios.CatalogoService;
import com.grupo5.gestionlibros.servicios.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final CatalogoService catalogoService;

    @Autowired
    public ReviewController(ReviewService reviewService,CatalogoService catalogoService) {
        this.reviewService = reviewService;
        this.catalogoService = catalogoService;
    }

    @GetMapping("/ver/{id}")
    public String listar(Model model, @PathVariable("id") String id) {
        List<Review> reviews = reviewService.listar(id);
        Libro libro = catalogoService.buscar(id);
        model.addAttribute("libro",libro);
        model.addAttribute("reviews",reviews);
        return  "/review";
    }

    @PostMapping("/crear/{idusuario}&{idlibro}")
    public String crearReview(@PathVariable("idusuario") String idusuario, @PathVariable("idlibro") String idlibro, @RequestParam("review") String review, @RequestParam("calificacion") int calificacion) {
        reviewService.crearReview(idusuario,idlibro,review,calificacion);
        return "redirect:/review/ver/" + idlibro;
    }

    @GetMapping("/editar/{id}&{idlibro}")
    public String editar(Model model,@PathVariable("id") String id,@PathVariable("idlibro") String idlibro) {
        Review review = reviewService.buscar(id);
        model.addAttribute("review",review);
        model.addAttribute("idlibro",idlibro);
        return "/modreview";
    }

    @PostMapping("/edit/{id}&{idlibro}")
    public String editarReview(@PathVariable("id") String id,@PathVariable("idlibro") String idlibro,@RequestParam("review") String review, @RequestParam("calificacion") int calificacion) {
        reviewService.editarReview(id,review,calificacion);
        return "redirect:/review/ver/" + idlibro;
    }

    @RequestMapping("/eliminar/{idreview}&{idlibro}")
    public String eliminar(@PathVariable("idreview") String idreview,@PathVariable("idlibro") String idlibro) {
        reviewService.eliminar(idreview);
        return "redirect:/review/ver/" + idlibro;
    }

}
