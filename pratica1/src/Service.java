package src;

public class Service {

    ProdutoRepository repositorio;

    public Service() {
        this.repositorio = new ProdutoRepository();
    }

    public void criaProduto(String nome, String fabricante, double preco) {
        repositorio.add(nome, fabricante, preco);
    }

}