package br.com.zup.casadocodigo.cliente;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.pais.PaisEstadoRequest;
import br.com.zup.casadocodigo.pais.PaisRepository;
import br.com.zup.casadocodigo.validator.CPFouCNPJ;
import br.com.zup.casadocodigo.validator.EstadoPertenceAoPais;
import br.com.zup.casadocodigo.validator.ValorUnico;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class ClienteRequest {

    @JsonProperty
    @Email
    @NotBlank
    @ValorUnico(entidade = Cliente.class, atributo = "email")
    private String email;
    @JsonProperty
    @NotBlank
    private String nome;
    @JsonProperty
    @NotBlank
    private String sobrenome;
    @JsonProperty
    @NotBlank
    @ValorUnico(entidade = Cliente.class, atributo = "documento")
    @CPFouCNPJ
    private String documento;
    @JsonProperty
    @NotBlank
    private String endereco;
    @JsonProperty
    @NotBlank
    private String complemento;
    @JsonProperty
    @NotBlank
    private String cidade;
    @JsonProperty
    @EstadoPertenceAoPais
    private  PaisEstadoRequest pais;
    @JsonProperty
    @NotBlank
    private String telefone;
    @JsonProperty
    private String cep;


    public Cliente toCliente(PaisRepository paisRepository, EstadoRepository estadoRepository){
        Estado estado = estadoRepository.findEstadoByNome(pais.getEstado());
        Optional<Pais> pais = paisRepository.findById(this.pais.getId());
        return new Cliente(this.email,this.nome,this.sobrenome,this.documento,this.endereco,this.complemento,this.cidade,
                pais.get(), estado,this.telefone,this.cep);
    }
}
