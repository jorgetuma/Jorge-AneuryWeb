package com.Jorge.Aneury.practica6.repositorios;

import com.Jorge.Aneury.practica6.entidades.Mockup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MockupRepository extends JpaRepository<Mockup, UUID> {

}
