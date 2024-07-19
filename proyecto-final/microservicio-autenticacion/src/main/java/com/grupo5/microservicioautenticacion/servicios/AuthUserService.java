package com.grupo5.microservicioautenticacion.servicios;

import com.grupo5.microservicioautenticacion.dto.*;
import com.grupo5.microservicioautenticacion.entidades.AuthUser;
import com.grupo5.microservicioautenticacion.repositorios.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<AuthUser> getById(int id) {
        return authUserRepository.findById(id);
    }

    public List<UserDto> getAllUsers() {
        List<AuthUser> users = authUserRepository.findAll();
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    public AuthUser update(int id, AuthUserDto dto) {
        Optional<AuthUser> optionalAuthUser = authUserRepository.findById(id);
        if (optionalAuthUser.isEmpty()) {
            return null;
        }
        AuthUser authUser = optionalAuthUser.get();
        authUser.setName(dto.getName());
        authUser.setEmail(dto.getEmail());
        authUser.setRole(dto.getRole());
//        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return authUserRepository.save(authUser);
    }

    public boolean delete(int id) {
        if (authUserRepository.existsById(id)) {
            authUserRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
