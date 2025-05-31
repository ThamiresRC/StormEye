package br.com.fiap.stormeye.service;

import br.com.fiap.stormeye.model.Cliente;
import br.com.fiap.stormeye.repository.ClienteRepository;
import br.com.fiap.stormeye.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Page<Cliente> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado"));
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setLogin(clienteAtualizado.getLogin());
        return repository.save(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }
}
