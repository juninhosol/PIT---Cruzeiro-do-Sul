<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Detalhes do Item</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px auto; max-width: 650px; color: #222; }
        .card { border: 1px solid #ddd; border-radius: 6px; padding: 20px; background: #fafafa; }
        .campo { margin-bottom: 12px; }
        .rotulo { font-weight: bold; color: #1b365d; }
        .btn { display: inline-block; margin-top: 15px; padding: 8px 14px; background: #1b365d; color: #fff; text-decoration: none; border-radius: 4px; }
    </style>
</head>
<body>
    <h1>Detalhes da Mídia</h1>

    <c:if test="${not empty item}">
        <div class="card">
            <div class="campo"><span class="rotulo">Tipo:</span> <c:out value="${item.tipoMidia}"/></div>
            <div class="campo"><span class="rotulo">Título:</span> <c:out value="${item.titulo}"/></div>
            <div class="campo"><span class="rotulo">Autor / Diretor:</span> <c:out value="${item.autorDiretor}"/></div>
            <div class="campo"><span class="rotulo">Ano de Lançamento:</span> <c:out value="${item.anoLancamento}"/></div>
            <div class="campo"><span class="rotulo">Gênero:</span> <c:out value="${item.genero}"/></div>
            <div class="campo"><span class="rotulo">Sinopse:</span><br><c:out value="${item.sinopse}"/></div>
        </div>
    </c:if>

    <a href="${pageContext.request.contextPath}/catalogo?acao=listar" class="btn">Voltar ao Catálogo</a>
</body>
</html>
