package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.handler.exception.PersonalizadaException;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.pais.PaisRepository;
import br.com.zup.casadocodigo.validator.RegistroValido;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class EstadoRequest {

    @JsonProperty
    @NotBlank
    private String nome;
    @JsonProperty
    @RegistroValido(entidade = Pais.class)
    private Integer pais;


    public Estado toEstado(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        Optional<Estado> estado = estadoRepository.findEstadoByNomeAndPais(this.nome, Long.valueOf(this.pais));
        if(estado.isPresent()) throw new PersonalizadaException("Já existe um estado com este nome cadastrado neste país","nome");
        Optional<Pais> pais = paisRepository.findById(Long.valueOf(this.pais));
        return new Estado(this.nome, pais.get());
    }
}
