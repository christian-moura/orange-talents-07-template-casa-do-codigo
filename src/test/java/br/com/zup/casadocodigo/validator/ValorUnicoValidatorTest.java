package br.com.zup.casadocodigo.validator;

import br.com.zup.casadocodigo.categoria.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidatorContext;

import java.net.URI;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ValorUnicoValidatorTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;
    private ValorUnicoValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp(){
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new ValorUnicoValidator(manager);
    }

    @Test
    public void OValorEUnicoParaOAtributoNomeNaEntidadeCategoriaEOTesteDevePassar() throws Exception {
        URI uri = new URI("/categoria");
        String payload = "{\"nome\":\"Programação\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Transactional
    public void OValorNaoEUnicoParaOAtributoNomeNaEntidadeCategoriaEOTesteNaoDevePassar() throws Exception {
        Categoria categoria = new Categoria("Programação");
        manager.persist(categoria);
        URI uri = new URI("/categoria");
        String payload = "{\"nome\":\"Programação\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

}