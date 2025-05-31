package br.com.fiap.stormeye.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdministradorDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;
}

