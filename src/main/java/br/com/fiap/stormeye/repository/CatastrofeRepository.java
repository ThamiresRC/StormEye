package br.com.fiap.stormeye.repository;

import br.com.fiap.stormeye.model.Catastrofe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CatastrofeRepository extends JpaRepository<Catastrofe, Long>, JpaSpecificationExecutor<Catastrofe> {
}
