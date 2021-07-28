package br.com.zup.casadocodigo.livro;


import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<LivrosResponse>> listarLivros (){
        List <Livro> lista = entityManager.createQuery("select t from Livro t").getResultList();
        return ResponseEntity.ok(lista.stream().map(LivrosResponse::new).collect(Collectors.toList()));
    }

}
