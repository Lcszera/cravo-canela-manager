package br.com.cravoecanela.dao;

import br.com.cravoecanela.config.ConnectionFactory;
import br.com.cravoecanela.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDAO {

    private static final String INSERT = """
            INSERT INTO produtos
            (nome, descricao, tipo, valor, quantidade, estoque_minimo, prateleira, coluna, palavras_chave)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    public void salvar(Produto produto) {

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT)
        ) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getTipo().name());
            stmt.setDouble(4, produto.getValor());
            stmt.setInt(5, produto.getQuantidade());
            stmt.setInt(6, produto.getEstoqueMinimo());
            stmt.setInt(7, produto.getPrateleira());
            stmt.setInt(8, produto.getColuna());
            stmt.setString(9, produto.getPalavrasChave());

            stmt.executeUpdate();

            System.out.println("Produto salvo com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao salvar produto.", e);

        }

    }

    public void atualizar(Produto produto) {

    }

    public void excluir(Integer id) {

    }

    public Produto buscarPorId(Integer id) {

        return null;

    }

    public List<Produto> listarTodos() {

        return null;

    }

    public List<Produto> pesquisar(String filtro) {

        return null;

    }

}