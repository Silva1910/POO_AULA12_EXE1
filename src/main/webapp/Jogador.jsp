<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JOGADORES</title>
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
		<form action="jogador" method="post" onsubmit="preencherTime()">
			<p class="title">
				<b>Jogadores</b>
			</p>
			<table>
				<tr>
				<tr>
					<td><select class="input_data" id="Timecodigo"
						name="Timecodigo">
							<option value="">Escolha um time</option>
							<c:forEach var="time" items="${times}">
								<option value="${time.codigoTime}"
									<c:if test="${time.codigoTime eq param.Timecodigo}">selected</c:if>>
									<c:out value="${time.nome} - ${time.cidade}" />
								</option>
							</c:forEach>
					</select>
				</tr>

				</tr>


				<tr>
					<td><input class="input_data" type="number" id="Id" name="Id"
						step="1" placeholder="ID do Jogador"></td>
				</tr>
				<tr>
					<td><input class="input_data" type="text" id="nome"
						name="nome" placeholder="Nome do Jogador"></td>
				</tr>
				<tr>
					<td><input class="input_data" type="date" id="nascimento"
						name="nascimento" placeholder="Data de Nascimento"></td>
				</tr>
				<tr>
					<td><input class="input_data" type="number" id="altura"
						name="altura" step="0.01" placeholder="Altura"></td>
				</tr>
				<tr>
					<td><input class="input_data" type="number" id="peso"
						name="peso" step="0.01" placeholder="Peso"></td>
				</tr>
				<tr>
					<td><input type="hidden" id="TimecodigoHidden"
						name="Timecodigo" value=""> <input type="hidden"
						name="cmd" id="cmd" value=""> <input type="submit"
						id="EnviarExcluir" name="botao" value="Excluir"
						onclick="document.getElementById('cmd').value = 'Excluir';">
						<input type="submit" id="EnviarAtualizar" name="botao"
						value="Atualizar"
						onclick="document.getElementById('cmd').value = 'Alterar';">
						<input type="submit" id="EnviarCadastrar" name="botao"
						value="Cadastrar"
						onclick="document.getElementById('cmd').value = 'Cadastrar';">
						<input type="submit" id="EnviarListar" name="botao" value="Listar"
						onclick="document.getElementById('cmd').value = 'Listar';">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<div  align = "center">
	<c:if test="${not empty saida}">
		<p>${saida}</p>
	</c:if>
	<c:if test="${not empty erro}">
		<p style="color: red;">${erro}</p>
	</c:if>
	</div>
<div class = "container2" align = "center">
	<c:if test="${not empty jogadores}">
		<h2>Lista de Jogadores</h2>
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Data de Nascimento</th>
					<th>Altura</th>
					<th>Peso</th>
					<th>Time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="jogador" items="${jogadores}">
					<tr>
						<td>${jogador.id}</td>
						<td>${jogador.nome}</td>
						<td>${jogador.dataNasc}</td>
						<td>${jogador.altura}</td>
						<td>${jogador.peso}</td>
						<td>${jogador.time.nome}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	</div>


</body>
</html>
