<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>${empty item.id || item.id == 0 ? 'Cadastrar Item' : 'Editar Item'}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px auto; max-width: 600px; color: #222; }
        label { display: block; margin-top: 12px; font-weight: bold; }
        input, select, textarea { width: 100%; padding: 8px; margin-top: 4px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .actions { margin-top: 20px; display: flex; gap: 10px; }
        button, .btn { padding: 10px 16px; background: #1b365d; color: #fff; text-decoration: none; border: none; border-radius: 4px; cursor: pointer; }
        .btn-sec { background: #666; }
        .erro { background: #f8d7da; color: #842029; padding: 10px; border-radius: 4px; margin-bottom: 15px; }
    </style>
</head>
<body>
    <h1>${empty item.id || item.id == 0 ? 'Cadastrar Novo Item' : 'Editar Item'}</h1>

    <c:if test="${not empty erro}">
        <div class="erro"><c:out value="${erro}"/></div>
    </c:if>

    <form action="${pageContext.request.contextPath}/catalogo" method="post">
        <c:if test="${not empty item.id && item.id > 0}">
            <input type="hidden" name="id" value="${item.id}">
        </c:if>

        <label for="tipoMidia">Tipo de Mídia *</label>
        <select id="tipoMidia" name="tipoMidia" required>
            <option value="Livro" ${item.tipoMidia == 'Livro' ? 'selected' : ''}>Livro</option>
            <option value="Filme" ${item.tipoMidia == 'Filme' ? 'selected' : ''}>Filme</option>
            <option value="Série" ${item.tipoMidia == 'Série' ? 'selected' : ''}>Série</option>
        </select>

        <label for="titulo">Título *</label>
        <input type="text" id="titulo" name="titulo" maxlength="255" required value="<c:out value='${item.titulo}'/>">

        <label for="autorDiretor">Autor / Diretor *</label>
        <input type="text" id="autorDiretor" name="autorDiretor" maxlength="255" required value="<c:out value='${item.autorDiretor}'/>">

        <label for="anoLancamento">Ano de Lançamento *</label>
        <input type="number" id="anoLancamento" name="anoLancamento" required value="<c:out value='${item.anoLancamento}'/>">

        <label for="genero">Gênero *</label>
        <input type="text" id="genero" name="genero" maxlength="100" required value="<c:out value='${item.genero}'/>">

        <label for="sinopse">Sinopse</label>
        <textarea id="sinopse" name="sinopse" rows="4"><c:out value="${item.sinopse}"/></textarea>

        <div class="actions">
            <button type="submit">Salvar</button>
            <a href="${pageContext.request.contextPath}/catalogo?acao=listar" class="btn btn-sec">Cancelar</a>
        </div>
    </form>
</body>
</html>
