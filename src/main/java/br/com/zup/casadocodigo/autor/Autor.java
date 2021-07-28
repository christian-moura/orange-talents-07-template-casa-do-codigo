package br.com.zup.casadocodigo.autor;

import br.com.zup.casadocodigo.livro.Livro;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    public Autor() {
    }

    public Autor(String nome, String email, String descricao, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
