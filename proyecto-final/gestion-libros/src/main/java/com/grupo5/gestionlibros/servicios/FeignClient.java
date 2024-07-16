package com.grupo5.gestionlibros.servicios;

import com.grupo5.gestionlibros.dto.AuthUserDto;
import com.grupo5.gestionlibros.dto.LoginDto;
import com.grupo5.gestionlibros.dto.RequestDto;
import com.grupo5.gestionlibros.dto.TokenDto;
import org.springframework.web.bind.annotation.*;

@org.springframework.cloud.openfeign.FeignClient(name = "api-gateway", url = "http://localhost:8080")
public interface FeignClient {

    @PostMapping( "/auth/login")
    public TokenDto login(@RequestBody LoginDto dto);

    @PostMapping("/auth/validate")
    public TokenDto validate(@RequestParam String token, @RequestBody RequestDto requestDto);

    @PostMapping("/auth/register")
    public TokenDto register(@RequestBody AuthUserDto dto);

    @GetMapping("/notificacion/notificar-registro/{correo}&{username}")
    public void notificarRegistro(@PathVariable("correo") String correo, @PathVariable("username") String userName);
}
