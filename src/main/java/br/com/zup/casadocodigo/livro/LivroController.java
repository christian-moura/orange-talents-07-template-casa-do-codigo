package br.com.zup.casadocodigo.livro;
import br.com.zup.casadocodigo.handler.Error;

import br.com.zup.casadocodigo.autor.AutorRepository;
import br.com.zup.casadocodigo.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livro")
public class LivroController {

    private  EntityManager entityManager;
    private AutorRepository autorRepository;
    private CategoriaRepository categoriaRepository;

    @Autowired
    public  LivroController (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid LivroRequest livroRequest){
        entityManager.persist(livroRequest.toLivro(entityManager));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarLivros (){
        List <Livro> lista = entityManager.createQuery("select t from Livro t").getResultList();
        return ResponseEntity.ok(lista.stream().map(LivroResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhesLivro (@PathVariable Long id){
       Livro livro = entityManager.find(Livro.class,id);
       if(livro == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Livro não encontado com o id: "+id,"id"));
       return ResponseEntity.ok(new LivroDetalhadoResponse(livro));
    }

}
