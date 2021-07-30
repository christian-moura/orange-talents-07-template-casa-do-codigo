package br.com.zup.casadocodigo.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistroValidoValidator implements ConstraintValidator<RegistroValido, Integer> {

    private EntityManager manager;
    Class<?> entidade;

    @Autowired
    public RegistroValidoValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(RegistroValido constraintAnnotation) {
        entidade = constraintAnnotation.entidade() ;
    }

    @Override
    public boolean isValid(Integer valor, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select e from "+entidade.getName()+" e where e.id = :valor");
        query.setParameter("valor", Long.valueOf(valor));
        return !query.getResultList().isEmpty();
    }
}