package br.com.zup.casadocodigo.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NomeUnicoValidator implements ConstraintValidator<NomeUnico, String> {

    private EntityManager manager;

    @Autowired
    public NomeUnicoValidator(EntityManager manager) {
        this.manager = manager;
    }

    public boolean isValid(String nome, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select c from Categoria c where c.nome = :nome");
        query.setParameter("nome", nome);
        return query.getResultList().isEmpty();
    }
}