package br.com.zup.casadocodigo.livro;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LivrosResponse {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String titulo;

    public LivrosResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }
}
