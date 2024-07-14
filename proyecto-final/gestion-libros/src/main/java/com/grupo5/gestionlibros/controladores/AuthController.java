package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.LoginDto;
import com.grupo5.gestionlibros.dto.TokenDto;
import com.grupo5.gestionlibros.servicios.FeignClient;
import feign.FeignException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private FeignClient feignClient;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping( "/login")
    public String login(@ModelAttribute LoginDto dto, HttpServletResponse response) {
        try {
            TokenDto tokenDto = feignClient.login(dto);
            if (tokenDto != null) {
                Cookie cookie = new Cookie("Authorization", tokenDto.getToken());
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "redirect:/admin/dashboard";
            }
        } catch (FeignException.FeignClientException e) {
            System.out.println(e.getMessage());
            return "login";
        }
        return "login";
    }
}