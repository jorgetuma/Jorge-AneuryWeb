package com.grupo5.microservicionotificacion.reporte.repositorios;

import com.grupo5.microservicionotificacion.reporte.colecciones.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends MongoRepository<Notificacion,String> {
}
