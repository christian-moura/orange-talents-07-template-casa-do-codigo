package br.com.zup.casadocodigo.categoria;


import br.com.zup.casadocodigo.autor.AutorRequest;
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
@RequestMapping(value = "/categoria")
public class CategoriaController {

    private final EntityManager entityManager;

    @Autowired
    public  CategoriaController (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid CategoriaRequest categoriaRequest){
        entityManager.persist(categoriaRequest.toCategoria());
        return ResponseEntity.ok().build();
    }

}
