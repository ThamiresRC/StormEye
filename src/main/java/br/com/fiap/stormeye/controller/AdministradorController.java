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

import br.com.fiap.stormeye.dto.AdministradorDTO;
import br.com.fiap.stormeye.model.Administrador;
import br.com.fiap.stormeye.model.Login;
import br.com.fiap.stormeye.service.AdministradorService;
import br.com.fiap.stormeye.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService service;
    private final LoginService loginService;

    @PostMapping
    public AdministradorDTO criar(@RequestBody @Valid AdministradorDTO dto) {
        var admin = toModel(dto);
        return toDTO(service.salvar(admin));
    }

    @GetMapping
    public Page<AdministradorDTO> listar(Pageable pageable) {
        return service.listar(pageable).map(this::toDTO);
    }

    @GetMapping("/{id}")
    public AdministradorDTO buscarPorId(@PathVariable Long id) {
        return toDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public AdministradorDTO atualizar(@PathVariable Long id, @RequestBody @Valid AdministradorDTO dto) {
        var admin = service.buscarPorId(id);
        admin.setNome(dto.getNome());
        return toDTO(service.salvar(admin));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    // Conversão para DTO
    private AdministradorDTO toDTO(Administrador admin) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(admin.getId());
        dto.setNome(admin.getNome());
        dto.setLoginId(admin.getLogin().getId());
        return dto;
    }

    // Conversão para Model com associação ao Login
    private Administrador toModel(AdministradorDTO dto) {
        var admin = new Administrador();
        admin.setNome(dto.getNome());

        // Busca o login com base no ID enviado
        Login login = loginService.buscarPorId(dto.getLoginId());
        admin.setLogin(login);

        return admin;
    }
}
