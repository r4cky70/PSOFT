package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Produtos")
public class ProdutoV1ControllerTests {
    @Autowired
    MockMvc driver;

    @Autowired
    ProdutoRepository produtoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Produto produto;

    @BeforeEach
    void setup() {
        produto = produtoRepository.save(Produto.builder()
                .codigoDeBarras("1234567890123")
                .fabricante("Fabricante Dez")
                .nome("Produto Dez")
                .preco(100.00)
                .build());
    }

    @AfterEach
    void tearDown() {
        produtoRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de campos obrigatórios")
    class ProdutoValidacaoCamposObrigatorios {

        @Test
        @DisplayName("Quando alteramos o nome do produto com dados válidos")
        void quandoAlteramosNomeDoProdutoValido() throws Exception {
            // Arrange
            produto.setNome("Produto Dez Alterado");

            // Act
            String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

            // Assert
            assertEquals(resultado.getNome(), "Produto Dez Alterado");
        }

    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da regra sobre o preço")
    class ProdutoValidacaoRegrasDoPreco {
        // Implementar os testes aqui
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da validação do código de barras")
    class ProdutoValidacaoCodigoDeBarras {
        // Implementar os testes aqui
    }

    @Nested
    @DisplayName("Conjunto de casos de teste REST API (caminos básicos)")
    class ProdutoValidacaoRestApiBasico {

        final String URI_PRODUTOS = "/v1/produtos";
        Produto produto1;
        //getALL()
        @BeforeEach
        void setup(){
            produto1 = produtoRepository.save(Produto.builder()
                    .codigoDeBarras("9876543210123")
                    .fabricante("Fabricante Vinte")
                    .nome("Produto Vinte")
                    .preco(100.00)
                    .build());
        }

        @AfterEach
        void tearDown(){
            produtoRepository.deleteAll();
        }
        @Test
        @DisplayName("Teste da API ao realizar a busca de todos os produtos")
        void quandoUsuarioAPIGetALLProdutos() throws Exception {
            //Arrange
            produtoRepository.save(produto);
            produtoRepository.save(produto1);
            //Act
            String responseJsonString = driver.perform(get(URI_PRODUTOS)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Produto> resultados = objectMapper
                    .readerForListOf(Produto.class)
                    .readValue(responseJsonString);


            //Assert
            assertEquals(2, resultados.size());
        }
        //get(id)
        //post()->body
        //put(id)->body
        //delete(id)
    }
}
