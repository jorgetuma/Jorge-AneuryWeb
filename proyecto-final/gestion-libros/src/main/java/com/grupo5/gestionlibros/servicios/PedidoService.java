package com.grupo5.gestionlibros.servicios;

import com.google.gson.Gson;
import com.grupo5.gestionlibros.dto.Libro;
import com.grupo5.gestionlibros.dto.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PedidoService {
    private final Gson gson = new Gson();
    private final String apiUrl = "http://localhost:8082/pedido";

    public List<Pedido> listar() {
        List<Pedido> pedidos;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String pedidoJson = client.get()
                .uri( apiUrl +"/listar")
                .retrieve()
                .body(String.class);

        pedidos = gson.fromJson(pedidoJson,List.class);

        return pedidos;
    }

    public List<Pedido> listarByUsuario(String id) {
        List<Pedido> pedidos;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String pedidoJson = client.get()
                .uri( apiUrl +"/listar-usuario/" + id)
                .retrieve()
                .body(String.class);

        pedidos = gson.fromJson(pedidoJson,List.class);

        return pedidos;
    }
}
