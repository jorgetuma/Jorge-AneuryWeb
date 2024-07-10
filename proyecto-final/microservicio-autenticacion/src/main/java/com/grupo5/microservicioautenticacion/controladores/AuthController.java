package com.grupo5.microservicioautenticacion.controladores;

import com.grupo5.microservicioautenticacion.dto.AuthUserDto;
import com.grupo5.microservicioautenticacion.dto.LoginDto;
import com.grupo5.microservicioautenticacion.dto.RequestDto;
import com.grupo5.microservicioautenticacion.dto.TokenDto;
import com.grupo5.microservicioautenticacion.entidades.AuthUser;
import com.grupo5.microservicioautenticacion.servicios.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserService authUserService;

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
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto dto) {
        dto.setRole("USER");
        AuthUser authUser = authUserService.save(dto);
        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }

}
