package com.grupo5.microservicionotificacion.reporte.repositorios;

import com.grupo5.microservicionotificacion.reporte.colecciones.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FacturaRepository extends MongoRepository<Factura,String> {

    List<Factura> findAllByIdUsuario(String idUsuario);
    Factura findFacturaById(String id);
}
