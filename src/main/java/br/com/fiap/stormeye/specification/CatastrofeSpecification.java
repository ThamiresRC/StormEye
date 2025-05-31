package br.com.fiap.stormeye.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.stormeye.model.Catastrofe;

public class CatastrofeSpecification {

    public static Specification<Catastrofe> nomeContains(String nome) {
        return (root, query, builder) ->
            nome == null ? null :
            builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Catastrofe> nivelGravidadeEquals(Integer nivel) {
        return (root, query, builder) ->
            nivel == null ? null :
            builder.equal(root.get("nivelGravidade"), nivel);
    }
}
