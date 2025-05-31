package br.com.fiap.stormeye.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.stormeye.model.Alerta;

public class AlertaSpecification {

    public static Specification<Alerta> cidadeNomeContains(String nomeCidade) {
        return (root, query, builder) -> 
            nomeCidade == null ? null : 
            builder.like(builder.lower(root.get("cidade").get("nome")), "%" + nomeCidade.toLowerCase() + "%");
    }

    public static Specification<Alerta> catastrofeNomeContains(String nomeCatastrofe) {
        return (root, query, builder) -> 
            nomeCatastrofe == null ? null : 
            builder.like(builder.lower(root.get("catastrofe").get("nome")), "%" + nomeCatastrofe.toLowerCase() + "%");
    }

    public static Specification<Alerta> nivelGravidadeEquals(Integer nivel) {
        return (root, query, builder) -> 
            nivel == null ? null : 
            builder.equal(root.get("nivelGravidade"), nivel);
    }

    public static Specification<Alerta> dataAlertaAfter(LocalDateTime data) {
        return (root, query, builder) -> 
            data == null ? null : 
            builder.greaterThanOrEqualTo(root.get("dataAlerta"), data);
    }

    public static Specification<Alerta> dataAlertaBefore(LocalDateTime data) {
        return (root, query, builder) -> 
            data == null ? null : 
            builder.lessThanOrEqualTo(root.get("dataAlerta"), data);
    }
}
