package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.util.FabricaDeConexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta do padrão Data Access Object (DAO) via JDBC.
 * Utiliza PreparedStatement em todas as consultas para mitigar vulnerabilidades de SQL Injection
 * e blocos try-with-resources para garantir o fechamento seguro das conexões.
 */
public class ItemMidiaDAO implements IItemMidiaDAO {

    @Override
    public void inserir(ItemMidia item) throws SQLException {
        String sql = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<ItemMidia> listarTodos() throws SQLException {
        String sql = "SELECT id, titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia " +
                     "FROM item_midia ORDER BY titulo ASC";
        List<ItemMidia> itens = new ArrayList<>();

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                itens.add(mapearResultSet(rs));
            }
        }
        return itens;
    }

    @Override
    public List<ItemMidia> buscarPorTermo(String termo) throws SQLException {
        String sql = "SELECT id, titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia " +
                     "FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ? ORDER BY titulo ASC";
        List<ItemMidia> itens = new ArrayList<>();
        String padraoBusca = "%" + termo + "%";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, padraoBusca);
            stmt.setString(2, padraoBusca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    itens.add(mapearResultSet(rs));
                }
            }
        }
        return itens;
    }

    @Override
    public ItemMidia buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia " +
                     "FROM item_midia WHERE id = ?";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void atualizar(ItemMidia item) throws SQLException {
        String sql = "UPDATE item_midia SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, " +
                     "genero = ?, sinopse = ?, tipo_midia = ? WHERE id = ?";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());
            stmt.setInt(7, item.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM item_midia WHERE id = ?";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Método auxiliar privado para converter uma linha do ResultSet em um objeto ItemMidia.
     */
    private ItemMidia mapearResultSet(ResultSet rs) throws SQLException {
        return new ItemMidia(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor_diretor"),
                rs.getInt("ano_lancamento"),
                rs.getString("genero"),
                rs.getString("sinopse"),
                rs.getString("tipo_midia")
        );
    }
}
