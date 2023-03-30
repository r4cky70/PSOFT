package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do Controlador de Produtos")
public class ProdutoV1Controller {

//    @Autowired
//    MockMvc mockMVC;
//
//    ObjectMapper objectMapper;
//    Produto produto;
//
//    @BeforeEach
//    void setup(){
//        produto = Produto.builder()
//                .id(1L)
//                .nome("Produto Base")
//                .codigoBarra("123456789")
//                .fabricante("Fabricante Base")
//                .preco(125.36)
//                .build();
//    }
//
//    @Test
//    @DisplayName("Quando altero produto com nome válido")
//    void alteroProdutoComNomeValido(){
//        // Arrange
//        produto.setNome("Chiclete");
//        // Act
//        produtoModificadoJSONString = mockMVC.perform(
//                put("/v1/produtos/" + 10)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(produto)
//                .content(objectMapper.writeValueAsString(produto)))
//            .andExpect(status().isOK())
//            .andDo(print())
//            .andReturn().getResponse().getContentAsString();
//
//        // Assert
//        Produto produtoModificado= objectMapper.readValue(produtoModificadoJSONString, Produto)
//        assertEquals("Chiclete", produtoModificado.getNome());
//    }
}
