package com.Jorge.Aneury.practica4.repositorios;

import com.Jorge.Aneury.practica4.entidades.Usuario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    Usuario findByUserName(String userName);

   List<Usuario> findAllByActivo(boolean status);

   List<Usuario> findAllByActivo(PageRequest pageable, boolean status);

   long countAllByActivo(boolean status);

   boolean existsUsuarioByUserNameAndActivo(String userName,boolean activo);
}
