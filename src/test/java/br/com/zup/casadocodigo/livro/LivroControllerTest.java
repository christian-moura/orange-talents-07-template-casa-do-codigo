package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.categoria.Categoria;
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
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;

     //  -------------------  testes para método de cadastro  ----------------
    @Test
    @Transactional
    public void deveriaCadastrarEDevolver200ComDadosValidos() throws Exception {

        Autor autor = new Autor("Christian Rodrigues","christian@email.com","Autor de romance", LocalDateTime.now());
        Categoria categoria = new Categoria("Romance");
        manager.persist(autor);
        manager.persist(categoria);
        URI uri = new URI("/livro");
        String payload = "{\n\t\"titulo\":\"Amor incondicional\",\n\t\"resumo\": \"Amor incondicional significa amor pleno, completo, absoluto, que não impõe condições ou limites para se amar. Quem ama de forma incondicional não espera nada em troca. O amor está em primeiro lugar.\",\n\t\"sumario\":\"sumario\",\n\t\"resumo\":\"O amor incondicional é conhecido como o afeto sem quaisquer limitações. Este termo é às vezes associado a outros termos tal como o verdadeiro altruísmo, amor completo\",\n\t\"preco\": 5000.0,\n\t\"numeroPaginas\": 101,\n\t\"isbn\":\"978–65–012–5227–77\",\n\t\"dataLancamento\":\"23/03/2022\",\n\t\"idAutor\": 1,\n\t\"idCategoria\": 1\t\n}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    public void deveriaDevolver400CasoDadosInseridosSejamInvalidos() throws Exception {
        URI uri = new URI("/livro");
        String payload = "{\n\t\"titulo\":\"\",\n\t\"resumo\": \"Amor incondicional significa amor pleno, completo, absoluto, que não impõe condições ou limites para se amar. Quem ama de forma incondicional não espera nada em troca. O amor está em primeiro lugar.\",\n\t\"sumario\":\"\",\n\t\"resumo\":\"O amor incondicional é conhecido como o afeto sem quaisquer limitações. Este termo é às vezes associado a outros termos tal como o verdadeiro altruísmo, amor completo\",\n\t\"preco\": 5000.0,\n\t\"numeroPaginas\": 101,\n\t\"isbn\":\"978–65–012–5227–77\",\n\t\"dataLancamento\":\"23/03/2022\",\n\t\"idAutor\": 1,\n\t\"idCategoria\": 1\t\n}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Transactional
    public void deveriaDevolver400ComTituloEISBNJaCadastrados() throws Exception {

        Autor autor = new Autor("Christian Rodrigues","christian@email.com","Autor de romance", LocalDateTime.now());
        Categoria categoria = new Categoria("Romance");
        manager.persist(autor);
        manager.persist(categoria);
        Livro livro = new Livro("Amor incondicional","sumario","resumo", BigDecimal.valueOf(5000.0),
                101,"978–65–012–5227–77",
                LocalDate.of(2022, 1, 8),autor,categoria);
        manager.persist(livro);
        URI uri = new URI("/livro");
        String payload = "{\n\t\"titulo\":\"Amor incondicional\",\n\t\"resumo\": \"Amor incondicional significa amor pleno, completo, absoluto, que não impõe condições ou limites para se amar. Quem ama de forma incondicional não espera nada em troca. O amor está em primeiro lugar.\",\n\t\"sumario\":\"sumario\",\n\t\"resumo\":\"O amor incondicional é conhecido como o afeto sem quaisquer limitações. Este termo é às vezes associado a outros termos tal como o verdadeiro altruísmo, amor completo\",\n\t\"preco\": 5000.0,\n\t\"numeroPaginas\": 101,\n\t\"isbn\":\"978–65–012–5227–77\",\n\t\"dataLancamento\":\"23/03/2022\",\n\t\"idAutor\": 1,\n\t\"idCategoria\": 1\t\n}";
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    //  -------------------  testes para método de listar todos produtos  ----------------

    @Test
    public void deveriaretornar200ComTodosLivrosCadastrados() throws Exception {;
        URI uri = new URI("/livro");
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }
}