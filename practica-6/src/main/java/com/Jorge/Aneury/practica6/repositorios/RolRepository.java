package com.Jorge.Aneury.practica6.repositorios;

import com.Jorge.Aneury.practica6.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,String> {
    Rol findByRole(String role);
}
