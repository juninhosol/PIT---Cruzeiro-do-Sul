package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define o contrato de persistência para a entidade ItemMidia.
 * Facilita o desacoplamento entre a camada de negócios e a tecnologia de banco de dados.
 */
public interface IItemMidiaDAO {

    void inserir(ItemMidia item) throws SQLException;

    List<ItemMidia> listarTodos() throws SQLException;

    List<ItemMidia> buscarPorTermo(String termo) throws SQLException;

    ItemMidia buscarPorId(int id) throws SQLException;

    void atualizar(ItemMidia item) throws SQLException;

    void excluir(int id) throws SQLException;
}
