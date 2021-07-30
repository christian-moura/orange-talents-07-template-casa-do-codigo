package br.com.zup.casadocodigo.validator;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pais.PaisEstadoRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EstadoPertenceAoPaisValidator implements ConstraintValidator<EstadoPertenceAoPais, PaisEstadoRequest> {

    private EstadoRepository estadoRepository;
   // Class<?> entidade;

    @Autowired
    public EstadoPertenceAoPaisValidator(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

//    @Override
//    public void initialize(EstadoPertenceAoPais constraintAnnotation) {
//        entidade = constraintAnnotation.entidade() ;
//    }

    @Override
    public boolean isValid(PaisEstadoRequest endereco, ConstraintValidatorContext context) {
        Optional<Estado> estado = estadoRepository.findEstadoByNomeAndPais(endereco.getEstado(), endereco.getId());
        return estado.isPresent();
    }
}