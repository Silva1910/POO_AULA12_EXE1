<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TIMES</title>
<link rel="stylesheet" href="./css/estilo.css">
</head>
<body>
    <nav id="menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/jogador">Jogadores</a></li>
            <li><a href="Times.jsp">Times</a></li>
        </ul>
    </nav>

    <div class="container" align="center">
        <form action="time" method="post">
            <p class="title"><b> Times </b></p>
            <table>
                <tr>
                    <td><input class="input_data" type="number" id="codigo"
                        name="codigo" step="1" placeholder="C�digo do Time" value="${Time.codigoTime}"></td>
                </tr>
                <tr>
                    <td><input class="input_data" type="text" id="nome"
                        name="nome" placeholder="Nome do Time" value="${Time.nome}"></td>
                </tr>
                <tr>
                    <td><input class="input_data" type="text" id="cidade"
                        name="cidade" placeholder="Cidade do Time" value="${Time.cidade}"></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="cmd" id="cmd" value="">
                        <input type="submit" id="EnviarExcluir" name="botao"
                            value="Excluir" onclick="document.getElementById('cmd').value = 'Excluir';">
                        <input type="submit" id="EnviarAtualizar" name="botao"
                            value="Atualizar" onclick="document.getElementById('cmd').value = 'Atualizar';">
                        <input type="submit" id="EnviarCadastrar" name="botao"
                            value="Cadastrar" onclick="document.getElementById('cmd').value = 'Cadastrar';">
                        <input type="submit" id="EnviarListar" name="botao" value="Listar"
                            onclick="document.getElementById('cmd').value = 'Buscar';">
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <br />
    <br />
    <div align="center">
        <c:if test="${not empty erro}">
            <h2><b><c:out value="${erro}" /></b></h2>
        </c:if>
    </div>

    <div align="center">
        <c:if test="${not empty saida}">
            <h3><b><c:out value="${saida}" /></b></h3>
        </c:if>
    </div>

    <br />

    <div class="container2" align="center">
        <c:if test="${not empty times}">
        <h2>Lista de Times</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>C�digo</th>
                        <th>Nome</th>
                        <th>Cidade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="time" items="${times}">
                        <tr>
                            <td>${time.codigoTime}</td>
                            <td>${time.nome}</td>
                            <td>${time.cidade}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
