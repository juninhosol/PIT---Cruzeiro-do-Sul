package br.com.catalogo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Elimina a duplicação de strings de conexão nas classes de acesso a dados.
 */
public class FabricaDeConexoes {

    private static final String URL = "jdbc:mysql://localhost:3306/catalogo_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    /**
     * Obtém uma nova conexão ativa com o banco de dados relacional.
     * @throws SQLException Caso ocorra falha na autenticação
     */
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do MySQL não encontrado no classpath.", e);
        }
    }
}
