package com.grupo5.gestionlibros.servicios;

import com.grupo5.gestionlibros.dto.LoginDto;
import com.grupo5.gestionlibros.dto.RequestDto;
import com.grupo5.gestionlibros.dto.TokenDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "api-gateway", url = "http://localhost:8080")
public interface FeignClient {

    @PostMapping( "/auth/login")
    public TokenDto login(@RequestBody LoginDto dto);

    @PostMapping("/auth/validate")
    public TokenDto validate(@RequestParam String token, @RequestBody RequestDto requestDto);
}