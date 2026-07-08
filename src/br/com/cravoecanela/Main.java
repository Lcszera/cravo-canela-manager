package br.com.cravoecanela;

import br.com.cravoecanela.config.DatabaseInitializer;
import br.com.cravoecanela.dao.ProdutoDAO;
import br.com.cravoecanela.entities.Produto;
import br.com.cravoecanela.enums.TipoProduto;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        System.out.println("Sistema iniciado com sucesso!");

    }

}