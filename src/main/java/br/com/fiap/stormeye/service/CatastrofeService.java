package br.com.fiap.stormeye.service;

import br.com.fiap.stormeye.model.Catastrofe;
import br.com.fiap.stormeye.repository.CatastrofeRepository;
import br.com.fiap.stormeye.specification.CatastrofeSpecification;
import br.com.fiap.stormeye.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatastrofeService {

    private final CatastrofeRepository repository;

    @Transactional
    public Catastrofe salvar(Catastrofe catastrofe) {
        return repository.save(catastrofe);
    }

    public Page<Catastrofe> listar(String nome, Integer nivelGravidade, Pageable pageable) {
    var spec = Specification
        .where(CatastrofeSpecification.nomeContains(nome))
        .and(CatastrofeSpecification.nivelGravidadeEquals(nivelGravidade));

    return repository.findAll(spec, pageable);
}


    public Catastrofe buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Catástrofe com ID " + id + " não encontrada"));
    }

    @Transactional
    public Catastrofe atualizar(Long id, Catastrofe catastrofeAtualizada) {
        Catastrofe catastrofe = buscarPorId(id);
        catastrofe.setNome(catastrofeAtualizada.getNome());
        catastrofe.setDescricao(catastrofeAtualizada.getDescricao());
        catastrofe.setNivelGravidade(catastrofeAtualizada.getNivelGravidade());
        return repository.save(catastrofe);
    }

    @Transactional
    public void deletar(Long id) {
        Catastrofe catastrofe = buscarPorId(id);
        repository.delete(catastrofe);
    }
}
