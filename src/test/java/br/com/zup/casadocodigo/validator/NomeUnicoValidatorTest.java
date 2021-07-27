package br.com.zup.casadocodigo.validator;

import br.com.zup.casadocodigo.categoria.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidatorContext;


@SpringBootTest
@ActiveProfiles("test")
class NomeUnicoValidatorTest {

    @PersistenceContext
    private EntityManager manager;
    private NomeUnicoValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp(){
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new NomeUnicoValidator(manager);
    }

    @Test
    public void ONomeEUnicoEOTesteDevePassar(){
        boolean valid = validator.isValid("Programação", context);
        Assertions.assertTrue(valid);
    }

    @Test
    @Transactional
    public void ONomeNaoEUnicoEOTesteNaoDevePassar(){
        Categoria categoria = new Categoria("Programação");
        manager.persist(categoria);
        boolean valid = validator.isValid("Programação", context);
        Assertions.assertFalse(valid);
    }

}