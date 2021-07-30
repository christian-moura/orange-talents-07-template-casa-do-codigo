package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.cliente.Cliente;
import br.com.zup.casadocodigo.pais.Pais;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pais pais;

    @OneToMany(mappedBy = "estado")
    private List<Cliente> clientes =new ArrayList<>();

    public Estado() { }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }
}
