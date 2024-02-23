package com.Jorge.Aneury.practica4.servicios;

import com.Jorge.Aneury.practica4.entidades.Rol;
import com.Jorge.Aneury.practica4.entidades.Usuario;
import com.Jorge.Aneury.practica4.repositorios.RolRepository;
import com.Jorge.Aneury.practica4.repositorios.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecurityServices implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    private PasswordEncoder passwordEncoder;

    public SecurityServices(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        passwordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        return passwordEncoder;
    }

    public void crearUsuarios(){
        System.out.println("Creación del usuario y rol en la base de datos");
        Rol rolAdmin = new Rol("ROLE_ADMIN");
        Rol rolUsuario = new Rol("ROLE_USER");
        rolRepository.save(rolAdmin);
        rolRepository.save(rolUsuario);

        Usuario admin = new Usuario();
        admin.setUserName("admin");
        admin.setNombre("Administrador");
        admin.setPassWord(passwordEncoder.encode("admin"));
        admin.setActivo(true);
        admin.setRoles(List.of(rolAdmin));
        usuarioRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
        }

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), user.isActivo(), true, true, true, grantedAuthorities);
    }
}
