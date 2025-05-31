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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.stormeye.dto.CatastrofeDTO;
import br.com.fiap.stormeye.model.Catastrofe;
import br.com.fiap.stormeye.service.CatastrofeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/catastrofes")
@RequiredArgsConstructor
public class CatastrofeController {

    private final CatastrofeService service;

    @PostMapping
    public CatastrofeDTO criar(@RequestBody @Valid CatastrofeDTO dto) {
        var catastrofe = toModel(dto);
        return toDTO(service.salvar(catastrofe));
    }

    @GetMapping
    public Page<CatastrofeDTO> listar(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) Integer nivelGravidade,
        Pageable pageable) {

    return service.listar(nome, nivelGravidade, pageable).map(this::toDTO);
}


    @GetMapping("/{id}")
    public CatastrofeDTO buscarPorId(@PathVariable Long id) {
        return toDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public CatastrofeDTO atualizar(@PathVariable Long id, @RequestBody @Valid CatastrofeDTO dto) {
        var catastrofe = service.buscarPorId(id);
        catastrofe.setNome(dto.getNome());
        catastrofe.setDescricao(dto.getDescricao());
        catastrofe.setNivelGravidade(dto.getNivelGravidade());
        return toDTO(service.salvar(catastrofe));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    private CatastrofeDTO toDTO(Catastrofe catastrofe) {
        CatastrofeDTO dto = new CatastrofeDTO();
        dto.setId(catastrofe.getId());
        dto.setNome(catastrofe.getNome());
        dto.setDescricao(catastrofe.getDescricao());
        dto.setNivelGravidade(catastrofe.getNivelGravidade());
        return dto;
    }

    private Catastrofe toModel(CatastrofeDTO dto) {
        var catastrofe = new Catastrofe();
        catastrofe.setNome(dto.getNome());
        catastrofe.setDescricao(dto.getDescricao());
        catastrofe.setNivelGravidade(dto.getNivelGravidade());
        return catastrofe;
    }
}
