package com.grupo5.microservicionotificacion.reporte.repositorios;

import com.grupo5.microservicionotificacion.reporte.colecciones.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FacturaRepository extends MongoRepository<Factura,String> {

    List<Factura> findAllByIdUsuario(String idUsuario);
    Factura findFacturaById(String id);
}
