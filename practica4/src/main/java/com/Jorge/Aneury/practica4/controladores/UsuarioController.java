package com.Jorge.Aneury.practica4.controladores;

import com.Jorge.Aneury.practica4.entidades.Usuario;
import com.Jorge.Aneury.practica4.servicios.UsuarioService;
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

    @GetMapping("/listar-usuario/{pag}")
    public String listarUsuarios(Model model,@PathVariable("pag") int pag) {
        if(pag <=0) {pag = 1;}
        List<Usuario> usuarios = usuarioService.getUsuariosPaginados(pag - 1,10);
        long cantPag = usuarioService.obtenerCantidadUsuariosActivos(10);
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("cantpag",cantPag);
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
       boolean bool = usuarioService.insertar(username,password,nombre,List.of(usuarioService.getRol(roles)));
        if(!bool) {return "redirect:/usuario/error";}
        return "redirect:/usuario/listar-usuario/1";
    }

    @PostMapping("/mod-usuario")
    public String modUsuario(@RequestParam("nombre") String nombre, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rol")String roles) {
        usuarioService.modificar(username,password,nombre,List.of(usuarioService.getRol(roles)));
        return "redirect:/usuario/listar-usuario/1";
    }

    @RequestMapping("/eliminar-usuario/{userName}")
    public String eliminarUsuario(@PathVariable("userName") String userName) {
        usuarioService.eliminar(userName);
        return "redirect:/usuario/listar-usuario/1";
    }

    @GetMapping("/error")
    public String errorUsuario(Model model) {
        model.addAttribute("mensaje","Nombre de usuario no esta disponible");
        model.addAttribute("url","/usuario/listar-usuario/1");
        model.addAttribute("titulo","Username no disponible");
        model.addAttribute("boton","Volver a la lista de usarios");
        return "/mensaje";
    }
}
