package br.com.zup.casadocodigo.categoria;

import br.com.zup.casadocodigo.validator.ValorUnico;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

        @JsonProperty
        @NotBlank
        //@NomeUnico
        @ValorUnico(entidade = "Categoria", atributo = "nome")
        private String nome;

        public Categoria toCategoria(){
            return new Categoria(this.nome);
        }
}
