package br.com.zup.casadocodigo.autor;

import br.com.zup.casadocodigo.validator.EmailUnicoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidatorContext;
import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaDevolver400CasoDadosInseridosSejamInvalidos() throws Exception {
        URI uri = new URI("/autor");
        String payload = "{\"nome\":\"\",\"email\":\"christian5\",\"descricao\":\"\"}";
                mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deveriaCadastrarEDevolver200ComDadosValidos() throws Exception {

        URI uri = new URI("/autor");
        String payload = "{\"nome\":\"Christian Rodrigues\",\"email\":\"christian@email.com\",\"descricao\":\"Ele é zupper\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }
}