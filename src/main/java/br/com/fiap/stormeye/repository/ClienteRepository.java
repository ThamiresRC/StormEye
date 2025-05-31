package br.com.fiap.stormeye.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.stormeye.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
