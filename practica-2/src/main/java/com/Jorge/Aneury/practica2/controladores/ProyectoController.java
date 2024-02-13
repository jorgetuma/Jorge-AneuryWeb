package com.Jorge.Aneury.practica2.controladores;

import com.Jorge.Aneury.practica2.entidades.Proyecto;
import com.Jorge.Aneury.practica2.entidades.Usuario;
import com.Jorge.Aneury.practica2.servicios.ProyectoService;
import com.Jorge.Aneury.practica2.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("proyecto")
public class ProyectoController {

    private final ProyectoService proyectoService;
    private UsuarioService usuarioService;

    @Autowired
    public ProyectoController(ProyectoService proyectoService, UsuarioService usuarioService) {
        this.proyectoService = proyectoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-proyecto/{pag}")
    public String listarProyecto(Model model,@PathVariable("pag") int pag) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = usuarioService.getUsuarioByUsername(authentication.getName());

        if(pag <= 0) {pag = 1;}
        List<Proyecto> proyectos = proyectoService.getUsuariosPaginados(pag - 1, 10, currentUser);
        long cantPag = proyectoService.obtenerCantidadProyectosActivos(10);
        model.addAttribute("proyectos",proyectos);
        model.addAttribute("size",proyectos.size());
        model.addAttribute("cantpag",cantPag);
        return  "listar-proyecto";
    }

    @GetMapping("/modificar-proyecto/{id}")
    public String modificarProyecto(@PathVariable("id") int id, Model model) {
        Proyecto p = proyectoService.getProyectoById(id);
        model.addAttribute("proyecto",p);
        return "mod-proyecto";
    }

    @PostMapping("/crear-proyecto")
    public String crearProyecto(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = usuarioService.getUsuarioByUsername(authentication.getName());
        proyectoService.isertar(nombre,descripcion,currentUser);
        return "redirect:/proyecto/listar-proyecto/1";
    }

    @PostMapping("/mod-proyecto/{id}")
    public String modProyecto(@PathVariable("id") int id,@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
        proyectoService.modificar(id,nombre,descripcion);
        return "redirect:/proyecto/listar-proyecto/1";
    }

    @RequestMapping("/eliminar-proyecto/{id}")
    public  String eliminarProyecto(@PathVariable("id") int id) {
        proyectoService.eliminar(id);
        return "redirect:/proyecto/listar-proyecto/1";
    }
}
