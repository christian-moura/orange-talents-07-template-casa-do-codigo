package br.com.zup.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/autor")
public class AutorController {

    private AutorRepository repository;

    @Autowired
    public AutorController(AutorRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?>cadastro(@RequestBody @Valid AutorRequest autorRequest){
        repository.save(autorRequest.toAutor());
        return ResponseEntity.ok().build();
    }
}
