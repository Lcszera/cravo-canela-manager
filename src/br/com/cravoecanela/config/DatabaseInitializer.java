package br.com.cravoecanela.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    private static final String CREATE_TABLE_PRODUTOS = """
        CREATE TABLE IF NOT EXISTS produtos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            descricao TEXT,
            tipo TEXT NOT NULL,
            valor REAL NOT NULL,
            quantidade INTEGER NOT NULL,
            estoque_minimo INTEGER DEFAULT 5,
            prateleira INTEGER NOT NULL,
            coluna INTEGER NOT NULL,
            palavras_chave TEXT
        );
        """;

    private static final String CREATE_TABLE_VENDAS = """
            CREATE TABLE IF NOT EXISTS vendas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                produto_id INTEGER NOT NULL,
                quantidade INTEGER NOT NULL,
                valor_total REAL NOT NULL,
                data_venda TEXT NOT NULL,
                FOREIGN KEY(produto_id) REFERENCES produtos(id)
            );
            """;

    public static void initializeDatabase() {


        try (
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement()
        ) {

            stmt.execute("DROP TABLE IF EXISTS vendas");
            stmt.execute("DROP TABLE IF EXISTS produtos");

            stmt.execute(CREATE_TABLE_PRODUTOS);
            stmt.execute(CREATE_TABLE_VENDAS);

            System.out.println("Banco inicializado com sucesso.");

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao inicializar banco.", e);

        }

    }

}