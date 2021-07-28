package br.com.zup.casadocodigo.categoria;

import br.com.zup.casadocodigo.validator.ValorUnico;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

        @JsonProperty
        @NotBlank
        @ValorUnico(entidade = Categoria.class, atributo = "nome")
        private String nome;

        public Categoria toCategoria(){
            return new Categoria(this.nome);
        }
}
