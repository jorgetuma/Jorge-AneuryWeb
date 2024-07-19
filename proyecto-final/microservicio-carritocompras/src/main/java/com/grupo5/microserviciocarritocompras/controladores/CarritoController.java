package com.grupo5.microserviciocarritocompras.controladores;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import com.grupo5.microserviciocarritocompras.servicios.CarritoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoCompraService carritoCompraService;

    @Autowired
    public CarritoController(CarritoCompraService carritoCompraService) {
        this.carritoCompraService = carritoCompraService;
    }

    @RequestMapping("/crear/{idusuario}")
    public String crearCarrito(@PathVariable("idusuario") int idUsuario) {
        return carritoCompraService.crearCarrito(idUsuario);
    }

    @RequestMapping("/buscar/{id}")
    public CarritoCompra buscarCarrito(@PathVariable("id") String id) {
    return carritoCompraService.buscarCarrito(id);
    }

    @RequestMapping("/agregar/{idlibro}&{idcarrito}")
    public void agregar(@PathVariable("idlibro") String idlibro,@PathVariable("idcarrito") String idcarrito) {
        System.out.println(idcarrito);
        System.out.println(idlibro);
        carritoCompraService.agregar(idlibro,idcarrito);
    }

    @RequestMapping("/eliminar/{idlibro}&{idcarrito}")
    public void eliminar(@PathVariable("idlibro") String idlibro,@PathVariable("idcarrito") String idcarrito) {
        carritoCompraService.eliminar(idlibro,idcarrito);
    }

    @RequestMapping("/buscar-usuario/{idusuario}")
    public ResponseEntity<CarritoCompra> buscarByusuario(@PathVariable("idusuario") int idusuario) {
        CarritoCompra carritoCompra = carritoCompraService.buscarCarritoByUsuario(idusuario);
        if (carritoCompra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carritoCompraService.buscarCarritoByUsuario(idusuario));
    }

    @RequestMapping("/limpiar/{idcarrito}")
    public void limpiarCarrito(@PathVariable("idcarrito") String idcarrito) {
        carritoCompraService.limpiar(idcarrito);
    }
}
