package br.com.fiap.stormeye.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) 
    @NotBlank
    @Size(max = 50)
    private String usuario;

    @NotBlank
    private String senha;

    @NotBlank
    @Pattern(regexp = "^(admin|cliente)$", message = "O tipo de usuário deve ser 'admin' ou 'cliente'")
    @Size(max = 10)
    private String tipoUsuario;
}
  