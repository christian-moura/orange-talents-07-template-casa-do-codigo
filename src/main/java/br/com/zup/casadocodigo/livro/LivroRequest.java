package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.categoria.Categoria;
import br.com.zup.casadocodigo.validator.ValorUnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


public class LivroRequest {

    @JsonProperty
    @NotBlank
    @ValorUnico(entidade = Livro.class, atributo = "titulo")
    private String titulo;
    @JsonProperty
    private String sumario;
    @JsonProperty
    @Length(max = 500)
    private String resumo;
    @JsonProperty
    @Min(value = 20)
    private BigDecimal preco;
    @JsonProperty
    @Min(value = 100)
    private Integer numeroPaginas;
    @JsonProperty
    @ValorUnico(entidade = Livro.class, atributo = "isbn")
    private String isbn;
    @JsonProperty
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;
    @JsonProperty
    @NotNull
    private Long idAutor;
    @JsonProperty
    @NotNull
    private Long idCategoria;


    public Livro toLivro(EntityManager entityManager) {

       Autor autor = entityManager.find(Autor.class, idAutor);
       Categoria categoria = entityManager.find(Categoria.class, idCategoria);
       Assert.state(autor!=null,"ID de Autor inexistente: "+idAutor);
       Assert.state(categoria!=null,"ID de Categoria inexistente "+idCategoria);

       return new Livro(this.titulo,
                this.sumario,this.resumo,this.preco,this.numeroPaginas,
                this.isbn, this.dataLancamento, autor,categoria);
    }
}
