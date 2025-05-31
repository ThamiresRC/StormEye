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

import br.com.fiap.stormeye.dto.CidadeDTO;
import br.com.fiap.stormeye.model.Cidade;
import br.com.fiap.stormeye.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService service;

    @PostMapping
    public CidadeDTO criar(@RequestBody @Valid CidadeDTO cidadeDTO) {
        Cidade cidade = toModel(cidadeDTO);
        var saved = service.salvar(cidade);
        return toDTO(saved);
    }

    @GetMapping
    public Page<CidadeDTO> listar(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String pais,
        Pageable pageable) {

    return service.listar(nome, pais, pageable).map(this::toDTO);
}


    @GetMapping("/{id}")
    public CidadeDTO buscarPorId(@PathVariable Long id) {
        return toDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public CidadeDTO atualizar(@PathVariable Long id, @RequestBody @Valid CidadeDTO cidadeDTO) {
        var cidade = service.buscarPorId(id);
        cidade.setNome(cidadeDTO.getNome());
        cidade.setPais(cidadeDTO.getPais());
        return toDTO(service.salvar(cidade));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    private CidadeDTO toDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        dto.setPais(cidade.getPais());
        return dto;
    }

    private Cidade toModel(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        cidade.setPais(dto.getPais());
        return cidade;
    }
}
