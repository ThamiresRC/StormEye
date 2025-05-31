package br.com.fiap.stormeye.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastroLoginDTO {

    @NotBlank
    @Size(max = 50)
    private String usuario;

    @NotBlank
    private String senha;

    @NotBlank
    @Pattern(regexp = "^(admin|cliente)$", message = "O tipo de usuário deve ser 'admin' ou 'cliente'")
    private String tipoUsuario;
}
