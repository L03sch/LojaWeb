package br.com.dao;

import br.com.model.Categoria;
import br.com.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para a entidade Categoria.
 * Responsável por todas as operações CRUD (Create, Read, Update, Delete)
 * relacionadas a categorias no banco de dados.
 */
public class CategoriaDAO {

    /**
     * Lista todas as categorias cadastradas no banco.
     * Ordena o resultado por ID de forma ascendente.
     *
     * @return Lista contendo todas as categorias
     * @throws RuntimeException se ocorrer erro na consulta SQL
     */
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id_categoria, nome_categoria, descricao FROM CATEGORIAS ORDER BY id_categoria";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias.", e);
        }

        return categorias;
    }

    /**
     * Busca uma categoria específica pelo seu ID.
     *
     * @param id o identificador da categoria
     * @return A categoria encontrada, ou null caso não exista
     * @throws RuntimeException se ocorrer erro na consulta SQL
     */
    public Categoria buscarPorId(int id) {
        String sql = "SELECT id_categoria, nome_categoria, descricao FROM CATEGORIAS WHERE id_categoria = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt("id_categoria"));
                    categoria.setNomeCategoria(rs.getString("nome_categoria"));
                    categoria.setDescricao(rs.getString("descricao"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categoria por ID.", e);
        }

        return null;
    }

    /**
     * Insere uma nova categoria no banco de dados.
     *
     * @param categoria A categoria a ser inserida
     * @throws RuntimeException se ocorrer erro na inserção
     */
    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO CATEGORIAS (nome_categoria, descricao) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir categoria.", e);
        }
    }

    /**
     * Atualiza os dados de uma categoria existente.
     *
     * @param categoria A categoria com os novos dados
     * @throws RuntimeException se ocorrer erro na atualização
     */
    public void atualizar(Categoria categoria) {
        String sql = "UPDATE CATEGORIAS SET nome_categoria = ?, descricao = ? WHERE id_categoria = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria.", e);
        }
    }

    /**
     * Remove uma categoria do banco de dados.
     *
     * @param id O identificador da categoria a ser removida
     * @throws RuntimeException se ocorrer erro na exclusão
     */
    public void excluir(int id) {
        String sql = "DELETE FROM CATEGORIAS WHERE id_categoria = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir categoria.", e);
        }
    }
}
