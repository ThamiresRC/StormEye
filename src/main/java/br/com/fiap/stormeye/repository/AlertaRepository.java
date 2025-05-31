package br.com.fiap.stormeye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.stormeye.model.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Long>, JpaSpecificationExecutor<Alerta> {
}
