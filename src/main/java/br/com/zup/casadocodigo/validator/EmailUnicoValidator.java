package br.com.zup.casadocodigo.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    private  EntityManager manager;

    @Autowired
    public EmailUnicoValidator(EntityManager manager) {
        this.manager = manager;
    }


    public boolean isValid(String email, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select a from Autor a where a.email = :email");
        query.setParameter("email", email);
        return query.getResultList().isEmpty();
    }
}