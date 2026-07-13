package br.com.cravoecanela;

import br.com.cravoecanela.entities.Produto;
import br.com.cravoecanela.config.DatabaseInitializer;
import br.com.cravoecanela.dao.ProdutoDAO;
import br.com.cravoecanela.enums.TipoProduto;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        System.out.println("Sistema iniciado com sucesso!");

        ProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();

        System.out.println("Quantidade de produtos: " + dao.listarTodos().size());

        produto.setNome("Chá Verde");
        produto.setDescricao("Auxilia no emagrecimento");
        produto.setTipo(TipoProduto.CHA);
        produto.setValor(15.90);
        produto.setQuantidade(20);
        produto.setEstoqueMinimo(5);
        produto.setPrateleira(1);
        produto.setColuna(2);
        produto.setPalavrasChave("emagrecer, detox");

        dao.salvar(produto);

        System.out.println("Produtos cadastrados:");

        for (Produto p : dao.listarTodos()) {

            System.out.println(
                    p.getId() + " - " +
                            p.getNome() + " - " +
                            p.getQuantidade()
            );

        }

        Produto encontrado = dao.buscarPorId(1);

        if (encontrado != null) {
            System.out.println("Produto encontrado: " + encontrado.getNome());
        }

    }

}