package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.pais.Pais;
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
public class EstadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void deveriaDevolver400ComPaisInvalido() throws Exception {

        URI uri = new URI("/estado");
        String payload = "{\n \"nome\":\"Minas Gerais\", \"pais\":10 \n}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @Transactional
    public void deveriaDevolver400ComEstadoJaCadastradoNoPais() throws Exception {

        Pais pais = new Pais("Brasil");
        Estado estado = new Estado("Minas Gerais",pais);
        manager.persist(pais);
        manager.persist(estado);
        URI uri = new URI("/estado");
        String payload = "{\n \"nome\":\"Minas Gerais\", \"pais\":1 \n}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @Transactional
    public void deveriaCadastrarEDevolver200() throws Exception {

        Pais pais = new Pais("Brasil");
        manager.persist(pais);
        URI uri = new URI("/estado");
        String payload = "{\n \"nome\":\"Minas Gerais\", \"pais\":1 \n}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

}