package br.com.zup.casadocodigo.autor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false, length = 400)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataCadastro ;

    public Autor(String nome, String email, String descricao, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }
}
