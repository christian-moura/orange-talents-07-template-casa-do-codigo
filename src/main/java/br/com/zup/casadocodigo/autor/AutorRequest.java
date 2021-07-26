package br.com.zup.casadocodigo.autor;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AutorRequest {

    @JsonProperty
    @NotNull
    private String nome;
    @JsonProperty
    @NotNull
    @Email
    private String email;
    @JsonProperty
    @NotNull
    @Length(max = 400)
    private String descricao;

    public Autor toAutor(){
        return new Autor(this.nome,this.email,this.descricao, LocalDateTime.now());
    }
}
