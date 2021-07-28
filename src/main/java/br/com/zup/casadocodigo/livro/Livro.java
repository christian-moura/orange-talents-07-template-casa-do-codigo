package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.categoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String titulo;
    @Lob
    @Column(nullable = false)
    private String sumario;
    @Column(nullable = false,length = 500)
    private String resumo;
    @Column(nullable = false)
    @Min(value = 20)
    private BigDecimal preco;
    @Column(nullable = false)
    @Min(value = 100)
    private Integer numeroPaginas;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private LocalDate dataLancamento;

    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;


    public Livro(){}

    public Livro(String titulo, String sumario, String resumo, BigDecimal preco, Integer numeroPaginas, String isbn, LocalDate dataLancamento, Autor autor, Categoria categoria) {
        this.titulo = titulo;
        this.sumario = sumario;
        this.resumo = resumo;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.autor = autor;
        this.categoria = categoria;
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
