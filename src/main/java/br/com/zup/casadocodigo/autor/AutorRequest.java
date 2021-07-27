package br.com.zup.casadocodigo.autor;

import br.com.zup.casadocodigo.validator.EmailUnico;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AutorRequest {

    @JsonProperty
    @NotBlank
    private String nome;
    @JsonProperty
    @NotBlank
    @Email
    @EmailUnico
    private String email;
    @JsonProperty
    @NotBlank
    @Length(max = 400)
    private String descricao;

    public Autor toAutor(){
        return new Autor(this.nome,this.email,this.descricao, LocalDateTime.now());
    }
}
