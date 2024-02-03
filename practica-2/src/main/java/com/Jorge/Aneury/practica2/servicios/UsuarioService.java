package com.Jorge.Aneury.practica2.servicios;

import com.Jorge.Aneury.practica2.entidades.Rol;
import com.Jorge.Aneury.practica2.entidades.Usuario;
import com.Jorge.Aneury.practica2.repositorios.RolRepository;
import com.Jorge.Aneury.practica2.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private  final RolRepository rolRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAllByActivo(true);
    }

    public List<Usuario> getUsuariosPaginados(int pag,int numporPagina){
        PageRequest paginaRequest = PageRequest.of(pag, numporPagina);
        return usuarioRepository.findAllByActivo(paginaRequest,true);
    }

    public long obtenerCantidadUsuariosActivos(int cantidadporPagina) {
        return usuarioRepository.countAllByActivo(true)/cantidadporPagina;
    }

    public Usuario getUsuarioByUsername(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    @Transactional
    public void insertar(String userName,String passWord ,String nombre, List<Rol> roles) {
        Usuario u = new Usuario();
        u.setUserName(userName);
        u.setNombre(nombre);
        u.setPassWord(passWord);
        u.setRoles(roles);
        u.setActivo(true);
        usuarioRepository.save(u);
    }

    @Transactional
    public void modificar(String userName,String passWord,String nombre,List<Rol> roles) {
        Usuario u = usuarioRepository.findByUserName(userName);
        u.setUserName(userName);
        u.setPassWord(passWord);
        u.setNombre(nombre);
        u.setRoles(roles);
    }

    @Transactional
    public void  eliminar(String userName) {
        Usuario u = usuarioRepository.findByUserName(userName);
        u.setActivo(false);
    }

    public Rol getRol(String nombre) {
        return rolRepository.findByRole(nombre);
    }

    public void crearUsuarios(){
        System.out.println("Creación del usuario y rol en la base de datos");
        Rol rolAdmin = new Rol("ROLE_ADMIN");
        Rol rolUsuario = new Rol("ROLE_USER");
        rolRepository.save(rolAdmin);
        rolRepository.save(rolUsuario);

        Usuario admin = new Usuario();
        admin.setUserName("admin");
        admin.setNombre("Administrador");
        admin.setPassWord("admin");
        admin.setActivo(true);
        admin.setRoles(List.of(rolAdmin));
        usuarioRepository.save(admin);
    }
}