<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Livros, Filmes e Séries</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px auto; max-width: 960px; color: #222; }
        h1 { color: #1b365d; }
        .toolbar { display: flex; justify-content: space-between; margin-bottom: 20px; gap: 10px; }
        input[type="text"] { padding: 8px; width: 280px; border: 1px solid #ccc; border-radius: 4px; }
        button, .btn { padding: 8px 14px; background: #1b365d; color: #fff; text-decoration: none; border: none; border-radius: 4px; cursor: pointer; }
        .btn-sec { background: #555; }
        .btn-del { background: #b02a37; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f4f6f9; }
        .erro { background: #f8d7da; color: #842029; padding: 10px; border-radius: 4px; margin-bottom: 15px; }
    </style>
</head>
<body>
    <h1>Catálogo de Mídias</h1>

    <c:if test="${not empty erro}">
        <div class="erro"><c:out value="${erro}"/></div>
    </c:if>

    <div class="toolbar">
        <form action="${pageContext.request.contextPath}/catalogo" method="get">
            <input type="hidden" name="acao" value="buscar">
            <input type="text" name="termo" placeholder="Buscar por título ou autor/diretor..."
                   value="<c:out value='${termoBuscado}'/>">
            <button type="submit">Buscar</button>
            <a href="${pageContext.request.contextPath}/catalogo?acao=listar" class="btn btn-sec">Limpar</a>
        </form>
        <a href="${pageContext.request.contextPath}/catalogo?acao=novo" class="btn">+ Novo Item</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>Tipo</th>
                <th>Título</th>
                <th>Autor / Diretor</th>
                <th>Ano</th>
                <th>Gênero</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${listaItens}">
                <tr>
                    <td><c:out value="${item.tipoMidia}"/></td>
                    <td><c:out value="${item.titulo}"/></td>
                    <td><c:out value="${item.autorDiretor}"/></td>
                    <td><c:out value="${item.anoLancamento}"/></td>
                    <td><c:out value="${item.genero}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/catalogo?acao=detalhes&id=${item.id}">Detalhes</a> |
                        <a href="${pageContext.request.contextPath}/catalogo?acao=editar&id=${item.id}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/catalogo?acao=excluir&id=${item.id}"
                           onclick="return confirm('Deseja realmente excluir este item?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listaItens}">
                <tr>
                    <td colspan="6">Nenhum item encontrado no catálogo.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
