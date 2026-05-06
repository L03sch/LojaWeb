package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory para criação de conexões com o banco de dados MySQL.
 * Centraliza a configuração de conexão e oferece um método estático
 * para obter conexões do pool.
 */
public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/loja_db?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USER = "loja";
    private static final String PASSWORD = "loja";

    /**
     * Construtor privado para impedir instanciação.
     */
    private ConnectionFactory() {
    }

    /**
     * Obtém uma conexão com o banco de dados.
     * Garante que o driver do MySQL está carregado antes de conectar.
     *
     * @return uma Connection ativa com o banco
     * @throws SQLException se houver erro ao conectar ou ao carregar o driver
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
