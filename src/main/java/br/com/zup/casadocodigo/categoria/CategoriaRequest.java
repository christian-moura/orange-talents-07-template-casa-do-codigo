package br.com.zup.casadocodigo.categoria;

import br.com.zup.casadocodigo.validator.NomeUnico;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaRequest {

        @JsonProperty
        @NotBlank
        @NomeUnico
        private String nome;

        public Categoria toCategoria(){
            return new Categoria(this.nome);
        }
}
