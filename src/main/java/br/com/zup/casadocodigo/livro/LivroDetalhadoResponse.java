package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.AutorResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDetalhadoResponse {

    @JsonProperty
    private String titulo;
    @JsonProperty
    private String sumario;
    @JsonProperty
    private String resumo;
    @JsonProperty
    private BigDecimal preco;
    @JsonProperty
    private Integer numeroPaginas;
    @JsonProperty
    private String isbn;
    @JsonProperty
    private String categoria;
    @JsonProperty
    private AutorResponse autor;
    @JsonProperty
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    public LivroDetalhadoResponse(Livro livro) {
        this.titulo= livro.getTitulo();
        this.sumario= livro.getSumario();
        this.resumo= livro.getResumo();
        this.preco=livro.getPreco();
        this.numeroPaginas= livro.getNumeroPaginas();
        this.isbn= livro.getIsbn();
        this.dataLancamento=livro.getDataLancamento();
        this.categoria= livro.getCategoria().getNome();
        this.autor = new AutorResponse(livro.getAutor());

    }
}
