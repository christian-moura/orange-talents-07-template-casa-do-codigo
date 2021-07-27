package br.com.zup.casadocodigo.validator;
import br.com.zup.casadocodigo.autor.Autor;
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
import java.time.LocalDateTime;


@SpringBootTest
@ActiveProfiles("test")
public class EmailUnicoValidatorTest {

    @PersistenceContext
    private EntityManager manager;
    private EmailUnicoValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp(){
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new EmailUnicoValidator(manager);
    }

    @Test
    @Transactional
    public void OEmailEUnicoEOTesteDevePassar(){
        boolean valid = validator.isValid("christian@email.com", context);
        Assertions.assertTrue(valid);
    }

    @Test
    @Transactional
    public void OEmailNaoEUnicoEOTesteNaoDevePassar(){
        Autor autor = new Autor("Christian","christian@email.com","Descrição Teste", LocalDateTime.now());
        manager.persist(autor);
        boolean valid = validator.isValid("christian@email.com", context);
        Assertions.assertFalse(valid);
    }


}