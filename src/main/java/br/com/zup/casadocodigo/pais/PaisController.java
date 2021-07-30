package br.com.zup.casadocodigo.pais;

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
@RequestMapping(value = "/pais")
public class PaisController {

    private EntityManager entityManager;

    @Autowired
    public PaisController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid PaisRequest paisRequest){
        entityManager.persist(paisRequest.toPais());
        return ResponseEntity.ok().build();
    }
}


