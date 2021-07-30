package br.com.zup.casadocodigo.cliente;

import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pais.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private EstadoRepository estadoRepository;
    private PaisRepository paisRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(EstadoRepository estadoRepository, PaisRepository paisRepository, ClienteRepository clienteRepository) {
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid ClienteRequest clienteRequest ){
        clienteRepository.save(clienteRequest.toCliente(paisRepository,estadoRepository));
        return ResponseEntity.ok().build();
    }
}
