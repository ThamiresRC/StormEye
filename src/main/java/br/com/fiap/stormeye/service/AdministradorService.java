package br.com.fiap.stormeye.service;

import br.com.fiap.stormeye.model.Administrador;
import br.com.fiap.stormeye.repository.AdministradorRepository;
import br.com.fiap.stormeye.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepository repository;

    @Transactional
    public Administrador salvar(Administrador admin) {
        return repository.save(admin);
    }

    public Page<Administrador> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Administrador buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Administrador com ID " + id + " não encontrado"));
    }

    @Transactional
    public Administrador atualizar(Long id, Administrador adminAtualizado) {
        Administrador admin = buscarPorId(id);
        admin.setNome(adminAtualizado.getNome());
        admin.setLogin(adminAtualizado.getLogin());
        return repository.save(admin);
    }

    @Transactional
    public void deletar(Long id) {
        Administrador admin = buscarPorId(id);
        repository.delete(admin);
    }
}
