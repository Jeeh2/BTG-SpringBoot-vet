package com.vet.api.repository;

import com.vet.api.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findBylogin(String login);
}
