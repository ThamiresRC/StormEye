package br.com.fiap.stormeye.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.stormeye.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
