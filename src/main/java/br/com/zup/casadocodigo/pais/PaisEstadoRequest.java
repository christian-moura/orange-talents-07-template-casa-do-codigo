package br.com.zup.casadocodigo.pais;

import br.com.zup.casadocodigo.validator.RegistroValido;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class PaisEstadoRequest {
    @JsonProperty
    @NotNull
    @RegistroValido(entidade = Pais.class)
    private Long id;
    @JsonProperty
    @NotBlank
    private String estado;

    public PaisEstadoRequest(Long id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }
}
