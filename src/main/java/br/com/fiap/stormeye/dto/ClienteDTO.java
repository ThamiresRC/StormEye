package br.com.fiap.stormeye.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;
}
