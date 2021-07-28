package br.com.zup.casadocodigo.livro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

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

}
