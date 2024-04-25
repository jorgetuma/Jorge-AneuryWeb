package com.grupo5.gestionlibros.servicios;

import com.google.gson.Gson;
import com.grupo5.gestionlibros.dto.CarritoCompra;
import com.grupo5.gestionlibros.dto.Libro;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CarritoCompraService {
    private final Gson gson = new Gson();
    private final String apiUrl = "http://localhost:8082/carrito";


    public String crear(String idUsuario) {
        String id;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String carritoJson = client.get()
                .uri(apiUrl + "/crear/" + idUsuario)
                .retrieve()
                .body(String.class);

        id = gson.fromJson(carritoJson,String.class);

        return id;
    }

    public void agregar(String idLibro,String idCarrito) {

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        client.get()
                .uri(apiUrl + "/agregar/" + idLibro + "&" + idCarrito)
                .retrieve();
    }

    public void quitar(String idLibro,String idCarrito) {

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        client.get()
                .uri(apiUrl + "/eliminar/" + idLibro + "&" + idCarrito)
                .retrieve();
    }

    public CarritoCompra buscar(String id) {
        CarritoCompra carrito;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String carritoJson = client.get()
                .uri(apiUrl + "/buscar/" + id)
                .retrieve()
                .body(String.class);

        carrito = gson.fromJson(carritoJson,CarritoCompra.class);

        return carrito;
    }

    public CarritoCompra buscarByUsuario(String idusuario) {
        CarritoCompra carrito;

        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String carritoJson = client.get()
                .uri(apiUrl + "/buscar-usuario/" + idusuario)
                .retrieve()
                .body(String.class);

        carrito = gson.fromJson(carritoJson,CarritoCompra.class);

        return carrito;
    }
}
