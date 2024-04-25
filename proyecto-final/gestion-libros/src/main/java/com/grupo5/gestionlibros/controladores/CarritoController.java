package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.servicios.CarritoCompraService;
import com.grupo5.gestionlibros.servicios.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    private final CarritoCompraService carritoCompraService;
    private final CatalogoService catalogoService;

    @Autowired
    public CarritoController(CarritoCompraService carritoCompraService,CatalogoService catalogoService) {
        this.carritoCompraService = carritoCompraService;
        this.catalogoService = catalogoService;
    }
}
