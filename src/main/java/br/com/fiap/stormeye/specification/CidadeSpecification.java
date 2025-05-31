package br.com.fiap.stormeye.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.stormeye.model.Cidade;

public class CidadeSpecification {

    public static Specification<Cidade> nomeContains(String nome) {
        return (root, query, builder) ->
            nome == null ? null :
            builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Cidade> paisContains(String pais) {
        return (root, query, builder) ->
            pais == null ? null :
            builder.like(builder.lower(root.get("pais")), "%" + pais.toLowerCase() + "%");
    }
}
