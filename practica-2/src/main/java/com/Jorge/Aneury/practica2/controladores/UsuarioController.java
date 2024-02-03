package com.Jorge.Aneury.practica2.controladores;

import com.Jorge.Aneury.practica2.entidades.Rol;
import com.Jorge.Aneury.practica2.entidades.Usuario;
import com.Jorge.Aneury.practica2.servicios.UsuarioService;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-usuario")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("size",usuarios.size());
        return "/listar-usuario";
    }

    @GetMapping("/modificar-usuario/{userName}")
    public String modificarUsuario(@PathVariable("userName") String userName,Model model) {
        Usuario u = usuarioService.getUsuarioByUsername(userName);
        model.addAttribute("usuario",u);
        return "/mod-usuario";
    }

    @PostMapping("/agregar-usuario")
    public String agregarUsuario(@RequestParam("nombre") String nombre, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rol")String roles) {
        usuarioService.insertar(username,password,nombre,List.of(usuarioService.getRol(roles)));
        return "redirect:/usuario/listar-usuario";
    }

    @PostMapping("/mod-usuario")
    public String modUsuario(@RequestParam("nombre") String nombre, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rol")String roles) {
        usuarioService.modificar(username,password,nombre,List.of(usuarioService.getRol(roles)));
        return "redirect:/usuario/listar-usuario";
    }

    @RequestMapping("/eliminar-usuario/{userName}")
    public String eliminarUsuario(@PathVariable("userName") String userName) {
        usuarioService.eliminar(userName);
        return "redirect:/usuario/listar-usuario";
    }
}
