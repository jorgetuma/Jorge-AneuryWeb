package com.grupo5.gestionlibros.servicios;

import com.grupo5.gestionlibros.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@org.springframework.cloud.openfeign.FeignClient(name = "api-gateway", url = "http://localhost:8080")
public interface FeignClient {

    @PostMapping( "/auth/login")
    public TokenDto login(@RequestBody LoginDto dto);

    @PostMapping("/auth/validate")
    public TokenDto validate(@RequestParam String token, @RequestBody RequestDto requestDto);

    @PostMapping("/auth/register")
    public TokenDto register(@RequestBody AuthUserDto dto);

    @GetMapping("/users/")
    public List<UserDto> getUsers(@RequestHeader("Authorization") String bearerToken);

    @PostMapping("/users/")
    public UserDto createUser(@RequestHeader("Authorization") String bearerToken, @RequestBody AuthUserDto dto);

    @PutMapping("/users/{id}")
    public UserDto updateUser(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody AuthUserDto dto);

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String bearerToken, @PathVariable int id);

    @GetMapping("/notificacion/notificar-registro/{correo}&{username}")
    public void notificarRegistro(@PathVariable("correo") String correo, @PathVariable("username") String userName);

    @RequestMapping("/carrito/crear/{idusuario}")
    public String crearCarrito(@RequestHeader("Authorization") String bearerToken, @PathVariable("idusuario") String idUsuario);

    @RequestMapping("/carrito/buscar/{id}")
    public CarritoCompra buscarCarrito(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") String id);

    @RequestMapping("/carrito/agregar/{idlibro}&{idcarrito}")
    public void agregar(@RequestHeader("Authorization") String bearerToken, @PathVariable("idlibro") String idlibro,@PathVariable("idcarrito") String idcarrito);

    @RequestMapping("/carrito/buscar-usuario/{idusuario}")
    public CarritoCompra buscarByusuario(@RequestHeader("Authorization") String bearerToken, @PathVariable("idusuario") int idusuario);

    @PostMapping("/pedido/procesar/{iduser}")
    public void procesarPedido(@RequestHeader("Authorization") String bearerToken, @PathVariable("iduser") int iduser, @RequestBody Map<String,String> params);

    @RequestMapping("/pedido/listar-usuario/{id}")
    public List<Pedido> listarByUsuario(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") int id);

    @GetMapping("/pedido/listar-fechactual")
    public List<Pedido> listarByFecha(@RequestHeader("Authorization") String bearerToken);
}
