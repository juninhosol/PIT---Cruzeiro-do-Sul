package br.com.catalogo.controller;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.service.ItemMidiaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador principal da aplicação (padrão MVC).
 * Gerencia exclusivamente o roteamento das requisições HTTP e o despacho para as views JSP,
 * delegando regras de negócio à classe ItemMidiaService.
 */
@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    private ItemMidiaService service;

    @Override
    public void init() {
        this.service = new ItemMidiaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        if (acao == null || acao.isEmpty()) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "novo":
                    request.getRequestDispatcher("/formItem.jsp").forward(request, response);
                    break;
                case "editar":
                    exibirFormularioEdicao(request, response);
                    break;
                case "detalhes":
                    exibirDetalhes(request, response);
                    break;
                case "excluir":
                    excluirItem(request, response);
                    break;
                case "buscar":
                    buscarItens(request, response);
                    break;
                default:
                    listarItens(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao processar solicitação: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idParam = request.getParameter("id");

        ItemMidia item = new ItemMidia();
        item.setTitulo(request.getParameter("titulo"));
        item.setAutorDiretor(request.getParameter("autorDiretor"));
        item.setGenero(request.getParameter("genero"));
        item.setSinopse(request.getParameter("sinopse"));
        item.setTipoMidia(request.getParameter("tipoMidia"));

        try {
            String anoParam = request.getParameter("anoLancamento");
            if (anoParam == null || anoParam.trim().isEmpty()) {
                throw new IllegalArgumentException("O Ano de Lançamento deve ser preenchido.");
            }
            item.setAnoLancamento(Integer.parseInt(anoParam.trim()));

            if (idParam != null && !idParam.trim().isEmpty()) {
                item.setId(Integer.parseInt(idParam.trim()));
                service.atualizarItem(item);
            } else {
                service.cadastrarItem(item);
            }

            response.sendRedirect(request.getContextPath() + "/catalogo?acao=listar");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "O campo Ano de Lançamento deve conter apenas números inteiros.");
            request.setAttribute("item", item);
            request.getRequestDispatcher("/formItem.jsp").forward(request, response);
        } catch (IllegalArgumentException | SQLException e) {
            request.setAttribute("erro", e.getMessage());
            request.setAttribute("item", item);
            request.getRequestDispatcher("/formItem.jsp").forward(request, response);
        }
    }

    private void listarItens(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<ItemMidia> lista = service.listarTodos();
        request.setAttribute("listaItens", lista);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void buscarItens(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String termo = request.getParameter("termo");
        List<ItemMidia> lista = service.pesquisar(termo);
        request.setAttribute("listaItens", lista);
        request.setAttribute("termoBuscado", termo);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void exibirDetalhes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ItemMidia item = service.obterPorId(id);
        request.setAttribute("item", item);
        request.getRequestDispatcher("/detalhes.jsp").forward(request, response);
    }

    private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ItemMidia item = service.obterPorId(id);
        request.setAttribute("item", item);
        request.getRequestDispatcher("/formItem.jsp").forward(request, response);
    }

    private void excluirItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        service.removerItem(id);
        response.sendRedirect(request.getContextPath() + "/catalogo?acao=listar");
    }
}
