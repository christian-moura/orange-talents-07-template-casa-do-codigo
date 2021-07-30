package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.pais.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/estado")
public class EstadoController {

    private EstadoRepository estadoRepository;
    private PaisRepository paisRepository;

    @Autowired
    public EstadoController(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid EstadoRequest estadoRequest){
       estadoRepository.save(estadoRequest.toEstado(paisRepository,estadoRepository));
       return ResponseEntity.ok().build();
    }

}
