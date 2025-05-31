package br.com.fiap.stormeye.service;

import br.com.fiap.stormeye.model.Cidade;
import br.com.fiap.stormeye.repository.CidadeRepository;
import br.com.fiap.stormeye.specification.CidadeSpecification;
import br.com.fiap.stormeye.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository repository;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        return repository.save(cidade);
    }

    public Page<Cidade> listar(String nome, String pais, Pageable pageable) {
    var spec = Specification
        .where(CidadeSpecification.nomeContains(nome))
        .and(CidadeSpecification.paisContains(pais));

    return repository.findAll(spec, pageable);
}


    public Cidade buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cidade com ID " + id + " não encontrada"));
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidadeAtualizada) {
        Cidade cidade = buscarPorId(id);
        cidade.setNome(cidadeAtualizada.getNome());
        cidade.setPais(cidadeAtualizada.getPais());
        return repository.save(cidade);
    }

    @Transactional
    public void deletar(Long id) {
        Cidade cidade = buscarPorId(id);
        repository.delete(cidade);
    }
}
