package br.com.zup.casadocodigo.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator  implements ConstraintValidator<ValorUnico, String> {


    private EntityManager manager;
    private String entidade = null;
    private String atributo = null;


    @Autowired
    public ValorUnicoValidator(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        entidade = constraintAnnotation.entidade();
        atributo = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select e from "+entidade+ " e where e."+atributo+" = :valor");
        query.setParameter("valor", valor);
        return query.getResultList().isEmpty();
    }


}