package br.com.fiap.stormeye.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "catastrofe")
public class Catastrofe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catastrofe")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_catastrofe")
    private String nome;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @Min(1)
    @Max(5)
    @Column(name = "nivel_gravidade")
    private int nivelGravidade;

    @NotBlank
    @Size(max = 100)
    private String localizacao;
}
