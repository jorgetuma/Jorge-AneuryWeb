package com.Jorge.Aneury.practica2.servicios;

import com.Jorge.Aneury.practica2.entidades.Rol;
import com.Jorge.Aneury.practica2.entidades.Usuario;
import com.Jorge.Aneury.practica2.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void insertar(String userName,String passWord ,String nombre, Set<Rol> roles) {
        Usuario u = new Usuario(userName,passWord,nombre,true,roles);
        usuarioRepository.save(u);
    }

    public void modificar(String userName,String passWord,String nombre,Set<Rol> roles) {
        Usuario u = usuarioRepository.findByUserName(userName);
        u.setUserName(userName);
        u.setPassWord(passWord);
        u.setNombre(nombre);
        u.setRoles(roles);
    }

    public void  eliminar(String userName) {
        Usuario u = usuarioRepository.findByUserName(userName);
        u.setActivo(false);
    }
}
