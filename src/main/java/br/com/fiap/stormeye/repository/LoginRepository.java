package br.com.fiap.stormeye.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.stormeye.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByUsuario(String usuario);
}
