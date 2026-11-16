package br.com.catalogo.service;

import br.com.catalogo.dao.IItemMidiaDAO;
import br.com.catalogo.dao.ItemMidiaDAO;
import br.com.catalogo.model.ItemMidia;

import java.sql.SQLException;
import java.time.Year;
import java.util.List;

/**
 * Camada de serviço responsável por centralizar as regras de negócio e validações de entrada,
 * isolando a camada de controle (Servlet) da camada de persistência (DAO).
 */
public class ItemMidiaService {

    private final IItemMidiaDAO dao;

    public ItemMidiaService() {
        this.dao = new ItemMidiaDAO();
    }

    /**
     * Construtor com injeção de dependência para facilitar testes unitários com mocks.
     */
    public ItemMidiaService(IItemMidiaDAO dao) {
        this.dao = dao;
    }

    /**
     * Valida os dados de um novo item e solicita sua inserção no banco.
     */
    public void cadastrarItem(ItemMidia item) throws IllegalArgumentException, SQLException {
        validarItem(item);
        dao.inserir(item);
    }

    /**
     * Retorna todos os itens cadastrados no catálogo.
     */
    public List<ItemMidia> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    /**
     * Realiza a busca por título ou autor/diretor. Caso o termo esteja vazio, retorna a lista completa.
     */
    public List<ItemMidia> pesquisar(String termo) throws SQLException {
        if (termo == null || termo.trim().isEmpty()) {
            return dao.listarTodos();
        }
        return dao.buscarPorTermo(termo.trim());
    }

    /**
     * Busca um item específico pelo seu identificador único.
     */
    public ItemMidia obterPorId(int id) throws IllegalArgumentException, SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Identificador de mídia inválido.");
        }
        return dao.buscarPorId(id);
    }

    /**
     * Valida e atualiza os dados de um item existente.
     */
    public void atualizarItem(ItemMidia item) throws IllegalArgumentException, SQLException {
        if (item.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        validarItem(item);
        dao.atualizar(item);
    }

    /**
     * Remove um item do catálogo a partir do seu ID.
     */
    public void removerItem(int id) throws IllegalArgumentException, SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        dao.excluir(id);
    }

    /**
     * Aplica regras de validação de integridade nos campos obrigatórios da entidade.
     */
    private void validarItem(ItemMidia item) {
        if (item == null) {
            throw new IllegalArgumentException("Os dados do item não podem ser nulos.");
        }
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Título é obrigatório.");
        }
        if (item.getTitulo().length() > 255) {
            throw new IllegalArgumentException("O Título excede o limite de 255 caracteres.");
        }
        if (item.getAutorDiretor() == null || item.getAutorDiretor().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Autor/Diretor é obrigatório.");
        }
        if (item.getGenero() == null || item.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Gênero é obrigatório.");
        }
        if (item.getTipoMidia() == null || item.getTipoMidia().trim().isEmpty()) {
            throw new IllegalArgumentException("Selecione o Tipo de Mídia (Livro, Filme ou Série).");
        }
        int anoAtual = Year.now().getValue() + 2;
        if (item.getAnoLancamento() < 1450 || item.getAnoLancamento() > anoAtual) {
            throw new IllegalArgumentException("Informe um Ano de Lançamento válido.");
        }
    }
}
