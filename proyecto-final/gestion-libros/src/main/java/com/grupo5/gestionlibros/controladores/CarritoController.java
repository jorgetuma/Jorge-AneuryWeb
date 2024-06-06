package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.CarritoCompra;
import com.grupo5.gestionlibros.dto.Item;
import com.grupo5.gestionlibros.dto.Libro;
import com.grupo5.gestionlibros.servicios.CarritoCompraService;
import com.grupo5.gestionlibros.servicios.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/{user}")
    public String verCarrito(@PathVariable("user") String user, Model model) {
        CarritoCompra carritoCompra = carritoCompraService.buscarByUsuario(user);
        List<Item> items = obtenerItems(carritoCompra);
        float totalCarrito = carritoCompraService.obtenerTotalCarrito(items);

        model.addAttribute("user", user);
        model.addAttribute("totalCarrito", totalCarrito);
        model.addAttribute("items", items);
        model.addAttribute("cantItems", items.size());
        model.addAttribute("carrito", carritoCompra);

        return "/carrito";
    }

    @GetMapping("/agregar/{idLibro}&{idCarrito}&{user}")
    public String agregar(@PathVariable String idLibro,@PathVariable String idCarrito,@PathVariable String user) {
        carritoCompraService.agregar(idLibro,idCarrito);
        return "redirect:/carrito/" + user;
    }

    @GetMapping("/quitar/{idLibro}&{idCarrito}&{user}")
    public String quitar(@PathVariable String idLibro,@PathVariable String idCarrito,@PathVariable String user) {
        carritoCompraService.quitar(idLibro,idCarrito);
        return "redirect:/carrito/" + user;
    }

    @GetMapping("/limpiar/{idCarrito}&{user}")
    public String limpiarCarrito(@PathVariable String idCarrito,@PathVariable String user) {
        carritoCompraService.limpiarCarrito(idCarrito);
        return "redirect:/carrito/" + user;
    }

    private List<Item> obtenerItems(CarritoCompra carritoCompra) {
        List<Item> items = new ArrayList<>();

        for(String idlib:carritoCompra.getLibros()) {
            Libro libro = catalogoService.buscar(idlib);
            int cantidad = 0;

            for(String id:carritoCompra.getLibros()) {
                if(id.equals(libro.getId())) {
                    cantidad++;
                }
            }
            Item item = new Item(libro,cantidad);
            items.add(item);
        }
        return items;
    }
}
