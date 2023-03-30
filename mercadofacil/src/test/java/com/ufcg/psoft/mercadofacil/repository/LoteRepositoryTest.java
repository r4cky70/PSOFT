package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LoteRepositoryTest {

    @Autowired
    LoteRepository<Lote, Long> driver;

    Lote lote;
    Lote resultado;
    Produto produto;
    List<Lote> resultadoList;


    @BeforeEach
    void setUp() {
        produto = Produto.builder()
                .id(1L)
                .nome("Produto Base")
                .codigoBarra("123456789")
                .fabricante("Fabricante Base")
                .preco(125.36)
                .build();
        lote = Lote.builder()
                .id(1L)
                .numeroDeItens(100)
                .produto(produto)
                .build();

    }

    @AfterEach
    void tearDown() {
        produto = null;
        driver.deleteAll();
    }

    @Test
    @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
    void salvarPrimeiroLote(){
        // Arrange
        driver.deleteAll();
        // Act
        resultado = driver.save(lote);
        // Assert
        assertEquals(driver.findAll().size(), 1);
        assertEquals(resultado.getId().longValue(), lote.getId().longValue());
        assertEquals(resultado.getProduto(), produto);
    }

    @Test
    @DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
    /*
    * Teste falhou!
    * O erro se encontra no método save, a implementação retorna sempre o primeiro Lote do container
    * mas deveria retornar o Lote que acabou de ser inserido.
    */
    void salvarSegundoLoteOuPosterior() {
        // Arrange
        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();

        Lote lote2 = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produto2)
                .build();
        driver.save(lote);
        // Act
        resultado = driver.save(lote2);
        // Assert
        assertNotNull(resultado);
        assertEquals(driver.findAll().size(), 2);
        assertEquals(resultado.getId().longValue(), lote2.getId().longValue());
        assertEquals(resultado.getProduto(), produto2);
    }
    @Test
    @DisplayName("Encontrar um lote pelo id")
    /*
    * Teste falhou!
    * O teste apresentou um erro de Index out of bounds.
    * O erro ocorre porque o metodo find() busca o indice do lote armazenado no repositório
    * e o correto seria que ele mostrase o lote que possui o atributo id correspondente.
    */
    void BuscarLoteId() {
        // Arrange
        driver.save(lote);
        // Act
        resultado = driver.find(1L);
        // Assert
        assertNotNull(resultado);
        assertEquals(resultado.getId().longValue(), lote.getId().longValue());
        assertEquals(resultado.getProduto(), produto);
    }

    @Test
    @DisplayName("Encontrar todos os lotes")
    void buscaTodosOsLotes() {
        // Arrange
        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();

        Lote lote2 = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produto2)
                .build();
        List<Lote> lotes = new ArrayList<>();
        // Act
        driver.save(lote);
        driver.save(lote2);
        lotes.add(lote);
        lotes.add(lote2);
        resultadoList = driver.findAll();
        // Assert
        assertNotNull(resultadoList);
        assertEquals(resultadoList, lotes);
    }

    @Test
    @DisplayName("Atualizar um lote")
    /*
    * Acredito que a implementação do método update está errada,
    * e a idéia do que o método deveria fazer não foi esclarecida direito,
    * por isso não sei projetar testes para algo que não sei o que deveria fazer.
    * */
    void update() {
        // Arrange
        // Act
        driver.update(lote);
        // Assert
    }

    @Test
    @DisplayName("Deletar um único lote, lote existente")
    /*
    * Teste Falhou!
    * O método delete está apagando todos os lotes ao invés de deletar apenas o especificado.
    * */
    void deleteLoteExistente() {
        // Arrange
        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();

        Lote lote2 = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produto2)
                .build();
        List <Lote> lotes = new ArrayList<>();
        lotes.add(lote);
        // Act
        driver.save(lote);
        driver.save(lote2);
        driver.delete(lote2);
        resultadoList = driver.findAll();
        // Assert
        assertNotNull(resultadoList);
        assertEquals(resultadoList, lotes);
    }

    @Test
    @DisplayName("Deletar um único lote, lote não existente")
    /*
     * Teste Falhou!
     * O método delete está apagando todos os lotes ao invés de deletar apenas o especificado.
     * */
    void deleteLoteNaoExistente() {
        // Arrange
        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();

        Lote lote2 = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produto2)
                .build();

        Produto produto3 = Produto.builder()
                .id(2L)
                .nome("Produto Extra Extra")
                .codigoBarra("123454321")
                .fabricante("Fabricante Extra Extra")
                .preco(125.36)
                .build();

        Lote lote3 = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produto3)
                .build();
        List <Lote> lotes = new ArrayList<>();
        lotes.add(lote);
        lotes.add(lote2);
        // Act
        driver.save(lote);
        driver.save(lote2);
        driver.delete(lote3);
        resultadoList = driver.findAll();
        // Assert
        assertNotNull(resultadoList);
        assertEquals(resultadoList, lotes);
    }

    @Test
    @DisplayName("Deleta todos os lotes")
    void deleteTodosOsLotes() {
        // Arrange
        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();

        Lote lote2 = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produto2)
                .build();
        // Act
        driver.save(lote);
        driver.save(lote2);
        driver.deleteAll();
        resultadoList = driver.findAll();
        // Assert
        assertNotNull(resultadoList);
        assertTrue(resultadoList.isEmpty());
    }
}