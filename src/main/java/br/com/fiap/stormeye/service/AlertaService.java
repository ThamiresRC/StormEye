package br.com.fiap.stormeye.service;

import br.com.fiap.stormeye.model.Alerta;
import br.com.fiap.stormeye.repository.AlertaRepository;
import br.com.fiap.stormeye.exception.ResourceNotFoundException;
import br.com.fiap.stormeye.specification.AlertaSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository repository;

    @Transactional
    public Alerta salvar(Alerta alerta) {
        return repository.save(alerta);
    }

    public Page<Alerta> listar(String cidade, String catastrofe, Integer nivelGravidade, LocalDateTime inicio, LocalDateTime fim, Pageable pageable) {
        Specification<Alerta> spec = Specification
                .where(AlertaSpecification.cidadeNomeContains(cidade))
                .and(AlertaSpecification.catastrofeNomeContains(catastrofe))
                .and(AlertaSpecification.nivelGravidadeEquals(nivelGravidade))
                .and(AlertaSpecification.dataAlertaAfter(inicio))
                .and(AlertaSpecification.dataAlertaBefore(fim));

        return repository.findAll(spec, pageable);
    }

    public Alerta buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alerta com ID " + id + " não encontrado"));
    }

    @Transactional
    public Alerta atualizar(Long id, Alerta alertaAtualizado) {
        Alerta alerta = buscarPorId(id);

        alerta.setHeadline(alertaAtualizado.getHeadline());
        alerta.setDescricao(alertaAtualizado.getDescricao());
        alerta.setDataAlerta(alertaAtualizado.getDataAlerta());
        alerta.setFimAlerta(alertaAtualizado.getFimAlerta());
        alerta.setNivelGravidade(alertaAtualizado.getNivelGravidade());
        alerta.setCidade(alertaAtualizado.getCidade());
        alerta.setCatastrofe(alertaAtualizado.getCatastrofe());
        alerta.setAdministrador(alertaAtualizado.getAdministrador());

        return repository.save(alerta);
    }

    @Transactional
    public void deletar(Long id) {
        Alerta alerta = buscarPorId(id);
        repository.delete(alerta);
    }
}
