package com.grupo5.microservicioautenticacion.controladores;

import com.grupo5.microservicioautenticacion.dto.*;
import com.grupo5.microservicioautenticacion.entidades.AuthUser;
import com.grupo5.microservicioautenticacion.servicios.AuthUserService;
import com.grupo5.microservicioautenticacion.servicios.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserService authUserService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto dto) {
        TokenDto tokenDto = authUserService.login(dto);
        if (tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto) {
        TokenDto tokenDto = authUserService.validate(token, requestDto);
        if (tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> create(@RequestBody AuthUserDto dto) {
        dto.setRole("USER");
        AuthUser authUser = authUserService.save(dto);
        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }
        TokenDto tokenDto = new TokenDto(jwtService.createToken(authUser));
        return ResponseEntity.ok(tokenDto);
    }

}
