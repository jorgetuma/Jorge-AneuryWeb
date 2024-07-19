package com.grupo5.gestionlibros.controladores;

import com.grupo5.gestionlibros.dto.AuthUserDto;
import com.grupo5.gestionlibros.dto.LoginDto;
import com.grupo5.gestionlibros.dto.TokenDto;
import com.grupo5.gestionlibros.servicios.FeignClient;
import feign.FeignException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AuthController {

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
                return "redirect:/";
            }
        } catch (FeignException.FeignClientException e) {
            System.out.println(e.getMessage());
            return "login";
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute AuthUserDto dto, HttpServletResponse response) {
        try {
            TokenDto tokenDto = feignClient.register(dto);
            if (tokenDto != null) {
                Cookie cookie = new Cookie("Authorization", tokenDto.getToken());
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
                System.out.println(dto.toString());
                feignClient.notificarRegistro(dto.getEmail(), dto.getName());
                return "redirect:/";
            }
        } catch (FeignException.FeignClientException e) {
            System.out.println(e.getMessage());
            return "register";
        }
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "redirect:/login";
    }

}
