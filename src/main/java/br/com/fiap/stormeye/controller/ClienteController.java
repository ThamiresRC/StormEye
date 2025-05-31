package br.com.fiap.stormeye.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.stormeye.dto.ClienteDTO;
import br.com.fiap.stormeye.model.Cliente;
import br.com.fiap.stormeye.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteDTO criar(@RequestBody @Valid ClienteDTO dto) {
        var cliente = toModel(dto);
        return toDTO(service.salvar(cliente));
    }

    @GetMapping
    public Page<ClienteDTO> listar(Pageable pageable) {
        return service.listar(pageable).map(this::toDTO);
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        return toDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDTO dto) {
        var cliente = service.buscarPorId(id);
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        return toDTO(service.salvar(cliente));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        return dto;
    }

    private Cliente toModel(ClienteDTO dto) {
        var cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        return cliente;
    }
}
