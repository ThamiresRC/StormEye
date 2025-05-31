package br.com.fiap.stormeye.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlertaDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String headline;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @Min(1)
    @Max(5)
    private int nivelGravidade;

    @NotNull
    private LocalDateTime dataAlerta;

    @NotNull
    private LocalDateTime fimAlerta;

    private String cidade;
    private String catastrofe;
    private String administrador;
}
