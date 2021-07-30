package br.com.zup.casadocodigo.pais;

import br.com.zup.casadocodigo.cliente.Cliente;
import br.com.zup.casadocodigo.estado.Estado;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String nome;
    @OneToMany(mappedBy = "pais")
    private List<Estado> estados =new ArrayList<>();

    @OneToMany(mappedBy = "pais")
    private List<Cliente> clientes =new ArrayList<>();

    public Pais() {}

    public Pais(String nome) {
        this.nome = nome;
    }
}
