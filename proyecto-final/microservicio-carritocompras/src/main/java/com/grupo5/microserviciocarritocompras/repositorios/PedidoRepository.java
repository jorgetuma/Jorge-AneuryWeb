package com.grupo5.microserviciocarritocompras.repositorios;

import com.grupo5.microserviciocarritocompras.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,String> {

    List<Pedido> findAllByPendiente(boolean estado);
    Pedido findPedidoByIdPedido(String id);

    List<Pedido> findAllByIdUser(String id);
}
