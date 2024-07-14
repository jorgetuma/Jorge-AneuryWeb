package com.grupo5.gestionlibros.config;

import com.grupo5.gestionlibros.dto.RequestDto;
import com.grupo5.gestionlibros.dto.TokenDto;
import com.grupo5.gestionlibros.servicios.FeignClient;
import feign.FeignException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final FeignClient feignClient;

    public AuthInterceptor(FeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.sendRedirect("/login");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }

        boolean valid = validateToken(token, request);

        if (!valid) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.sendRedirect("/login");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            return false;
        }

        return true;
    }

    private boolean validateToken(String token, HttpServletRequest request) {

        RequestDto requestDto = new RequestDto(request.getRequestURI(), request.getMethod());
        try {
            TokenDto tokenDto = feignClient.validate(token, requestDto);
            if (tokenDto != null) {
                return true;
            }
        } catch (FeignException.FeignClientException e) {
            return false;
        }
        return false;
    }
}