package br.com.zup.casadocodigo.pais;

import br.com.zup.casadocodigo.validator.ValorUnico;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @JsonProperty
    @NotBlank
    @ValorUnico(entidade = Pais.class, atributo = "nome")
    private String nome;

    public Pais toPais(){
        return new Pais(this.nome);
    }
}