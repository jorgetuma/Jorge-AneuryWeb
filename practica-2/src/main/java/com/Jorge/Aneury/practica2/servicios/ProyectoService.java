package com.Jorge.Aneury.practica2.servicios;

import com.Jorge.Aneury.practica2.entidades.Proyecto;
import com.Jorge.Aneury.practica2.entidades.Usuario;
import com.Jorge.Aneury.practica2.repositorios.ProyectoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> getProyectos() {
        return  proyectoRepository.findAllByActivo(true);
    }

    public List<Proyecto> getUsuariosPaginados(int pag, int numporPagina, Usuario usuario){
        PageRequest paginaRequest = PageRequest.of(pag, numporPagina);
        return proyectoRepository.findProyectosByUserAndActivo(paginaRequest,usuario,true);
    }

    public Proyecto getProyectoById(int id) {
        return  proyectoRepository.findById(id);
    }

    @Transactional
    public void isertar(String nombre, String descripcion, Usuario user) {
        Proyecto p = new Proyecto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
//        p.setUserName(username);
        p.setUser(user);
        p.setActivo(true);
        proyectoRepository.save(p);
    }

    @Transactional
    public void modificar(int id,String nombre,String descripcion) {
        Proyecto p = proyectoRepository.findById(id);
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
    }

    @Transactional
    public void eliminar(int id) {
        Proyecto p = proyectoRepository.findById(id);
        p.setActivo(false);
    }

    public long obtenerCantidadProyectosActivos(int cantidadporPagina) {
        return proyectoRepository.countAllByActivo(true)/cantidadporPagina;
    }
}
