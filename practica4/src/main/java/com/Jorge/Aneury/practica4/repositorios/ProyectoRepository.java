package com.Jorge.Aneury.practica4.repositorios;

import com.Jorge.Aneury.practica4.entidades.Proyecto;
import com.Jorge.Aneury.practica4.entidades.Usuario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository  extends JpaRepository<Proyecto,Integer> {

    List<Proyecto> findAllByActivo(boolean activo);

    List<Proyecto> findAllByActivo(PageRequest pageRequest, boolean activo);

    Proyecto findById(int id);

    long countAllByActivo(boolean activo);

    List<Proyecto> findProyectosByUserAndActivo(PageRequest pageRequest, Usuario user, boolean activo);

}
