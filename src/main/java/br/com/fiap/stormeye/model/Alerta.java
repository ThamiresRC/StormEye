package br.com.fiap.stormeye.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do alerta é obrigatório")
    @Size(max = 100)
    private String headline;

    @NotBlank(message = "A descrição do alerta é obrigatória")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "A data do alerta é obrigatória")
    private LocalDateTime dataAlerta;

    @NotNull(message = "A data de fim do alerta é obrigatória")
    private LocalDateTime fimAlerta;

    @Min(1)
    @Max(5)
    private int nivelGravidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "catastrofe_id")
    private Catastrofe catastrofe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "admin_id")
    private Administrador administrador;
}
