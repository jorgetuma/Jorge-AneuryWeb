package com.Grupo5.practica5.repositorios;

import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<TramaJSON,String> {

    List<TramaJSON> findAllByIdDispositivo(int idDispositivo);
}
