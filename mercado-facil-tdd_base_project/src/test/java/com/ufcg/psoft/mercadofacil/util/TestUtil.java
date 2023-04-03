package com.ufcg.psoft.mercadofacil.util;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class TestUtil {
    public static Produto criarProduto(Long id, String nome, String fabricante, String codigoDeBarras, Double preco){
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setCodigoBarra(codigoDeBarras);
        produto.setFabricante(fabricante);
        return produto;
    }
}
