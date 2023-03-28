package src;

import java.util.HashMap;
import java.util.Map;

public class ProdutoRepository {
    private Map<Integer, Produto> dic;

    public ProdutoRepository() {
        this.dic = new HashMap<>();
    }

    public void add(String nome, String fabricante, double preco) {
        Produto p = new Produto (nome, fabricante, preco);
        dic.put(p.getId(), p);
    }
}