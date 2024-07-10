package com.grupo5.microservicioautenticacion.servicios;

import com.grupo5.microservicioautenticacion.dto.AuthUserDto;
import com.grupo5.microservicioautenticacion.dto.LoginDto;
import com.grupo5.microservicioautenticacion.dto.RequestDto;
import com.grupo5.microservicioautenticacion.dto.TokenDto;
import com.grupo5.microservicioautenticacion.entidades.AuthUser;
import com.grupo5.microservicioautenticacion.repositorios.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;;
    private final JwtService jwtService;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByEmail(authUserDto.getEmail());
        if (user.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .name(authUserDto.getName())
                .email(authUserDto.getEmail())
                .password(password)
//                .role("user")
                .role(authUserDto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(LoginDto loginDto) {
        Optional<AuthUser> user = authUserRepository.findByEmail(loginDto.getEmail());
        if (user.isEmpty()) {
            return null;
        }
        if (passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
            return new TokenDto(jwtService.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token, RequestDto requestDto) {
        if (!jwtService.validate(token, requestDto)) {
            return null;
        }
        String email = jwtService.getEmail(token);
        if (authUserRepository.findByEmail(email).isEmpty()) {
            return null;
        }
        return new TokenDto(token);
    }
}
