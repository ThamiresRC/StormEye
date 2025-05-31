package br.com.fiap.stormeye.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatastrofeDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @Min(1)
    @Max(5)
    private int nivelGravidade;

    @NotBlank
    @Size(max = 100)
    private String localizacao;
}
