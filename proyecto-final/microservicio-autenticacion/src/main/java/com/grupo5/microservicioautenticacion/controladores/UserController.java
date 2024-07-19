package com.grupo5.microservicioautenticacion.controladores;

import com.grupo5.microservicioautenticacion.dto.AuthUserDto;
import com.grupo5.microservicioautenticacion.dto.UserDto;
import com.grupo5.microservicioautenticacion.entidades.AuthUser;
import com.grupo5.microservicioautenticacion.repositorios.AuthUserRepository;
import com.grupo5.microservicioautenticacion.servicios.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthUserService authUserService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUser() {
        return ResponseEntity.ok(authUserService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody AuthUserDto dto) {
        AuthUser authUser = authUserService.save(dto);
        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }
        UserDto userDto = new UserDto(authUser.getId(), authUser.getName(), authUser.getEmail(), authUser.getRole());
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody AuthUserDto dto) {

        AuthUser authUser = authUserService.update(id, dto);
        if (authUser == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto(authUser.getId(), authUser.getName(), authUser.getEmail(), authUser.getRole());
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean isDeleted = authUserService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
