package br.com.zup.casadocodigo.categoria;

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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void deveriaDevolver400CasoDadosInseridosSejamInvalidos() throws Exception {
        URI uri = new URI("/categoria");
        String payload = "{\"nome\":\"\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @Transactional
    public void deveriaDevolver400ComNomeCategoriaJaCadastrado() throws Exception {

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

    @Test
    public void deveriaCadastrarEDevolver200ComDadosValidos() throws Exception {

        URI uri = new URI("/categoria");
        String payload = "{\"nome\":\"Programação\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

}