package br.com.fiap.stormeye.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.stormeye.dto.AlertaDTO;
import br.com.fiap.stormeye.model.Administrador;
import br.com.fiap.stormeye.model.Alerta;
import br.com.fiap.stormeye.model.Catastrofe;
import br.com.fiap.stormeye.model.Cidade;
import br.com.fiap.stormeye.service.AdministradorService;
import br.com.fiap.stormeye.service.AlertaService;
import br.com.fiap.stormeye.service.CatastrofeService;
import br.com.fiap.stormeye.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;
    private final CidadeService cidadeService;
    private final CatastrofeService catastrofeService;
    private final AdministradorService administradorService;

    @PostMapping
    public AlertaDTO criar(@RequestBody @Valid Alerta alerta) {
        
        Cidade cidade = cidadeService.buscarPorId(alerta.getCidade().getId());
        Catastrofe catastrofe = catastrofeService.buscarPorId(alerta.getCatastrofe().getId());
        Administrador admin = administradorService.buscarPorId(alerta.getAdministrador().getId());

        alerta.setCidade(cidade);
        alerta.setCatastrofe(catastrofe);
        alerta.setAdministrador(admin);

        return toDTO(alertaService.salvar(alerta));
    }

    @GetMapping
    public Page<AlertaDTO> listar(
        @RequestParam(required = false) String cidade,
        @RequestParam(required = false) String catastrofe,
        @RequestParam(required = false) Integer nivelGravidade,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
        Pageable pageable) {

        return alertaService.listar(cidade, catastrofe, nivelGravidade, inicio, fim, pageable)
                .map(this::toDTO);
    }

    @GetMapping("/{id}")
    public AlertaDTO buscarPorId(@PathVariable Long id) {
        return toDTO(alertaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public AlertaDTO atualizar(@PathVariable Long id, @RequestBody @Valid Alerta alerta) {
        Cidade cidade = cidadeService.buscarPorId(alerta.getCidade().getId());
        Catastrofe catastrofe = catastrofeService.buscarPorId(alerta.getCatastrofe().getId());
        Administrador admin = administradorService.buscarPorId(alerta.getAdministrador().getId());

        alerta.setCidade(cidade);
        alerta.setCatastrofe(catastrofe);
        alerta.setAdministrador(admin);

        return toDTO(alertaService.atualizar(id, alerta));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alertaService.deletar(id);
    }

    private AlertaDTO toDTO(Alerta alerta) {
        AlertaDTO dto = new AlertaDTO();
        dto.setId(alerta.getId());
        dto.setHeadline(alerta.getHeadline());
        dto.setDescricao(alerta.getDescricao());
        dto.setNivelGravidade(alerta.getNivelGravidade());
        dto.setDataAlerta(alerta.getDataAlerta());
        dto.setFimAlerta(alerta.getFimAlerta());
        dto.setCidade(alerta.getCidade() != null ? alerta.getCidade().getNome() : null);
        dto.setCatastrofe(alerta.getCatastrofe() != null ? alerta.getCatastrofe().getNome() : null);
        dto.setAdministrador(alerta.getAdministrador() != null ? alerta.getAdministrador().getNome() : null);
        return dto;
    }
}
