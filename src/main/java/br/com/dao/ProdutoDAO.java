package br.com.dao;


import br.com.model.Produto;
import br.com.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para a entidade Produto.
 * Gerencia todas as operações de banco de dados relacionadas aos produtos:
 * inserção, leitura, atualização e exclusão.
 */
public class ProdutoDAO{

    /**
     * Lista todos os produtos cadastrados.
     * Ordena por ID de produto em ordem ascendente.
     *
     * @return Lista de todos os produtos
     * @throws RuntimeException em caso de erro SQL
     */
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id_produto, nome_produto, preco, estoque, id_categoria FROM PRODUTOS ORDER BY id_produto";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setPrecoProduto(rs.getDouble("preco"));
                produto.setEstoqueProduto(rs.getDouble("estoque"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos.", e);
        }

        return produtos;
    }

    /**
     * Busca um produto específico pelo ID.
     *
     * @param id o ID do produto
     * @return o Produto encontrado, ou null se não existir
     * @throws RuntimeException em caso de erro SQL
     */
    public Produto buscarPorId(int id) {
        String sql = "SELECT id_produto, nome_produto, preco, estoque, id_categoria FROM PRODUTOS WHERE id_produto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setIdProduto(rs.getInt("id_produto"));
                    produto.setNomeProduto(rs.getString("nome_produto"));
                    produto.setPrecoProduto(rs.getDouble("preco"));
                    produto.setEstoqueProduto(rs.getDouble("estoque"));
                    produto.setIdCategoria(rs.getInt("id_categoria"));
                    return produto;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID.", e);
        }

        return null;
    }

    /**
     * Insere um novo produto no banco de dados.
     *
     * @param produto o Produto a ser inserido
     * @throws RuntimeException em caso de erro SQL
     */
    public void inserir(Produto produto) {
        String sql = "INSERT INTO PRODUTOS (nome_produto, preco, estoque, id_categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setDouble(3, produto.getEstoqueProduto());
            stmt.setInt(4, produto.getIdCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto.", e);
        }
    }

    /**
     * Atualiza as informações de um produto existente.
     *
     * @param produto o Produto com as informações atualizadas
     * @throws RuntimeException em caso de erro SQL
     */
    public void atualizar(Produto produto) {
        String sql = "UPDATE PRODUTOS SET nome_produto = ?, preco = ?, estoque = ?, id_categoria = ? WHERE id_produto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setDouble(3, produto.getEstoqueProduto());
            stmt.setInt(4, produto.getIdCategoria());
            stmt.setInt(5, produto.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto.", e);
        }
    }

    /**
     * Remove um produto do banco de dados pelo ID.
     *
     * @param id o ID do produto a ser removido
     * @throws RuntimeException em caso de erro SQL
     */
    public void excluir(int id) {
        String sql = "DELETE FROM PRODUTOS WHERE id_produto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto.", e);
        }
    }


}