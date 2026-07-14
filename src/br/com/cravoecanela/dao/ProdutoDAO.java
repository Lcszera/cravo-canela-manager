package br.com.cravoecanela.dao;

import br.com.cravoecanela.config.ConnectionFactory;
import br.com.cravoecanela.entities.Produto;
import br.com.cravoecanela.enums.TipoProduto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

        String sql = """
            UPDATE produtos
            SET nome = ?,
                descricao = ?,
                tipo = ?,
                valor = ?,
                quantidade = ?,
                estoque_minimo = ?,
                prateleira = ?,
                coluna = ?,
                palavras_chave = ?
            WHERE id = ?
            """;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
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
            stmt.setInt(10, produto.getId());

            stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao atualizar produto.", e);

        }

    }

    public void excluir(Integer id) {

        String sql = "DELETE FROM produtos WHERE id = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Produto excluído com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao excluir produto.", e);

        }

    }

    public Produto buscarPorId(Integer id) {

        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(TipoProduto.valueOf(rs.getString("tipo")));
                produto.setValor(rs.getDouble("valor"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                produto.setPrateleira(rs.getInt("prateleira"));
                produto.setColuna(rs.getInt("coluna"));
                produto.setPalavrasChave(rs.getString("palavras_chave"));

                return produto;

            }

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao buscar produto.", e);

        }

        return null;

    }

    public List<Produto> listarTodos() {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(TipoProduto.valueOf(rs.getString("tipo")));
                produto.setValor(rs.getDouble("valor"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                produto.setPrateleira(rs.getInt("prateleira"));
                produto.setColuna(rs.getInt("coluna"));
                produto.setPalavrasChave(rs.getString("palavras_chave"));

                produtos.add(produto);

            }

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao listar produtos.", e);

        }

        return produtos;

    }

    public List<Produto> pesquisar(String filtro) {

        List<Produto> produtos = new ArrayList<>();

        String sql = """
            SELECT *
            FROM produtos
            WHERE nome LIKE ?
               OR descricao LIKE ?
               OR tipo LIKE ?
               OR palavras_chave LIKE ?
            """;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            String pesquisa = "%" + filtro + "%";

            stmt.setString(1, pesquisa);
            stmt.setString(2, pesquisa);
            stmt.setString(3, pesquisa);
            stmt.setString(4, pesquisa);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(TipoProduto.valueOf(rs.getString("tipo")));
                produto.setValor(rs.getDouble("valor"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                produto.setPrateleira(rs.getInt("prateleira"));
                produto.setColuna(rs.getInt("coluna"));
                produto.setPalavrasChave(rs.getString("palavras_chave"));

                produtos.add(produto);

            }

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao pesquisar produtos.", e);

        }

        return produtos;

    }

}