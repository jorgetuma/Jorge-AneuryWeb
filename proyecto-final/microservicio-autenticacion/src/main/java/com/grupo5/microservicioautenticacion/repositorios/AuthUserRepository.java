package com.grupo5.microservicioautenticacion.repositorios;

import com.grupo5.microservicioautenticacion.entidades.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByEmail(String email);
    Optional<AuthUser> findById(int id);

}
