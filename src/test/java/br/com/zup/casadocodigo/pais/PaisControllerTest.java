package br.com.zup.casadocodigo.pais;

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
import javax.transaction.Transactional;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PaisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;

    //  -------------------  testes para método de cadastro de país  ----------------
    @Test
    @Transactional
    public void deveriaCadastrarEDevolver200ComDadosValidos() throws Exception {
        URI uri = new URI("/pais");
        String payload = "{\n\t\"nome\":\"Brasil\"\n}";
                mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @org.springframework.transaction.annotation.Transactional
    public void deveriaDevolver400ComNomePaisJaCadastrado() throws Exception {

        Pais pais = new Pais("Brasil");
        manager.persist(pais);
        URI uri = new URI("/pais");
        String payload = "{\"nome\":\"Brasil\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void deveriaDevolver400ComNomePaisInvalido() throws Exception {

        URI uri = new URI("/pais");
        String payload = "{\"nome\":\"\"}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

}