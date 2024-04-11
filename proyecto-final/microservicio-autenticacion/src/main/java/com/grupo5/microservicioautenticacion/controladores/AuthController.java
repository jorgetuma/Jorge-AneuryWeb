package com.grupo5.microservicioautenticacion.controladores;

import com.grupo5.microservicioautenticacion.dto.LoginRequest;
import com.grupo5.microservicioautenticacion.entidades.Usuario;
import com.grupo5.microservicioautenticacion.servicios.JwtService;
import com.grupo5.microservicioautenticacion.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UsuarioService usuarioService;
    private final JwtService jwtService;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        System.out.println(email + " " + password);
//        String token = userService.login(email, password);
//        if (token == null) {
//            return ResponseEntity.badRequest().body("Usuario o contraseña incorrectos");
//        }
//        return ResponseEntity.ok(token);
//    }
//
//    @GetMapping("/validate")
//    public ResponseEntity<String> validate(@RequestParam String token) {
//        String validToken = userService.validate(token);
//        if (validToken == null) {
//            return ResponseEntity.badRequest().body("Invalid token");
//        }
//        return ResponseEntity.ok(validToken);
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok("Token:" + token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = usuarioService.login(request.getEmail(), request.getPassword());
        if (token == null) {
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrectos");
        }
        return ResponseEntity.ok("Token:" + token);
    }
}
