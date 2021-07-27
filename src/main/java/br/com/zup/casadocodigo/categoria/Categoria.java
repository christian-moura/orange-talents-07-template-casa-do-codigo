package br.com.zup.casadocodigo.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;

    public Categoria() { }

    public Categoria(String nome) {
        this.nome = nome;
    }
}
