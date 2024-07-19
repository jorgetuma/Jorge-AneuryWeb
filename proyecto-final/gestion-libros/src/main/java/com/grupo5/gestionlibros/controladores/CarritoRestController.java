package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.CarritoCompra;
import com.grupo5.gestionlibros.servicios.FeignClient;
import com.grupo5.gestionlibros.servicios.JwtService;
import feign.FeignException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/carrito")
public class CarritoRestController {

    private final FeignClient feignClient;
    private final JwtService jwtService;

    @PostMapping("/crear/{idusuario}")
    public ResponseEntity<String> crearCarrito(HttpServletRequest request, @PathVariable("idusuario") String idUsuario) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(feignClient.crearCarrito("Bearer " + token, idUsuario));
        } catch (FeignException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/buscar-usuario/{idusuario}")
    public ResponseEntity<CarritoCompra> buscarByusuario(HttpServletRequest request, @PathVariable("idusuario") int idusuario){
        String token = getTokenFromCookies(request);
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(feignClient.buscarByusuario("Bearer " + token, idusuario));
        } catch (FeignException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/buscar/{id}")
    public ResponseEntity<CarritoCompra> buscarCarrito(HttpServletRequest request, @PathVariable("id") String id) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(feignClient.buscarCarrito("Bearer " + token, id));
        } catch (FeignException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping("/agregar/{idlibro}&{idcarrito}")
    public ResponseEntity<?> agregar(HttpServletRequest request, @PathVariable("idlibro") String idlibro, @PathVariable("idcarrito") String idcarrito) {
        String token = getTokenFromCookies(request);
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            feignClient.agregar("Bearer " + token, idlibro,idcarrito);
            return ResponseEntity.ok().build();
        } catch (FeignException e) {
            return ResponseEntity.badRequest().build();
        }
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
