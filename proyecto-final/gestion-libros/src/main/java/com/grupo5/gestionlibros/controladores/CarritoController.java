package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.CarritoCompra;
import com.grupo5.gestionlibros.dto.Item;
import com.grupo5.gestionlibros.dto.Libro;
import com.grupo5.gestionlibros.servicios.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    private final CarritoCompraService carritoCompraService;
    private final CatalogoService catalogoService;
    private final JwtService jwtService;
    private final FeignClient feignClient;
    private final PedidoService pedidoService;

    @Autowired
    public CarritoController(CarritoCompraService carritoCompraService, CatalogoService catalogoService, JwtService jwtService, FeignClient feignClient, PedidoService pedidoService) {
        this.carritoCompraService = carritoCompraService;
        this.catalogoService = catalogoService;
        this.jwtService = jwtService;
        this.feignClient = feignClient;
        this.pedidoService = pedidoService;
    }


    @GetMapping("/{user}")
    public String verCarrito(HttpServletRequest request, @PathVariable("user") String user, Model model) {
        try {
            CarritoCompra carritoCompra = carritoCompraService.buscarByUsuario(user);
            List<Item> items = obtenerItems(carritoCompra);
            float totalCarrito = carritoCompraService.obtenerTotalCarrito(items);

            String token = getTokenFromCookies(request);

            model.addAttribute("user", user);
            model.addAttribute("totalCarrito", totalCarrito);
            model.addAttribute("items", items);
            model.addAttribute("cantItems", items.size());
            model.addAttribute("carrito", carritoCompra);
            model.addAttribute("token", token);

            return "carrito";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

    @GetMapping("/agregar/{idLibro}&{idCarrito}&{user}")
    @ResponseBody
    public Map<String, String> agregar(@PathVariable String idLibro,@PathVariable String idCarrito,@PathVariable String user) {
//        carritoCompraService.agregar(idLibro,idCarrito);
//        return "redirect:/carrito/" + user;
        Map<String, String> response = new HashMap<>();
        try {
            carritoCompraService.agregar(idLibro, idCarrito);
            response.put("message", "Libro agregado al carrito exitosamente.");
        } catch (Exception e) {
            response.put("message", "Error al agregar el libro al carrito.");
        }
        return response;
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

    @PostMapping(path = "/procesarCompraPaypal")
    public String procesarCompraPayPal(HttpServletRequest request, Model model, @RequestParam Map<String,String> params){
        String token = params.get("custom");
        if (token == null || token.isEmpty()) {
            System.out.println("no token");
            return "403";
        }
        int userId = jwtService.getId(token);
        try {
            CarritoCompra carritoCompra = feignClient.buscarByusuario("Bearer " + token, userId);
            carritoCompraService.limpiarCarrito(carritoCompra.getIdCarrito());
//            feignClient.procesarPedido("Bearer " + token, userId, params);
            return "redirect:/";
        } catch (Exception e) {
            System.out.println(e);
            return "403";
        }

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

    private String getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
