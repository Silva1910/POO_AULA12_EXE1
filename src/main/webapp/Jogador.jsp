<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JOGADORES</title>
<link rel="stylesheet" href="./css/Styles.css">
</head>
<body>
	<nav id="menu">
		<ul>
			<li><a href="Jogador.jsp">Jogadores </a></li>
			<li><a href="Times.jsp">Times </a></li>
		</ul>
	</nav>
	<div class="container" align="center">
		<form action="jogador" method="post" onsubmit="preencherTime()">
			<p class="title">
				<b> Jogadores </b>
			</p>
			<table>
				<tr>
				<tr>
					<td>
        <select class="input_data" id="Timecodigo" name="Timecodigo">
            <option value="">Escolha um time</option>
            <c:forEach var="time" items="${time}">
                <option value="${time.codigo}">
                    <c:out value="${time.nome} - ${time.cidade}" />
                </option>
            </c:forEach>
        </select>
    </td>
</tr>
                    
                </tr>
				
               
                <tr>
                    <td><input class="input_data" type="number"
						id="Id" name="Id" step="1" placeholder="ID do Jogador"></td>
                </tr>
                <tr>
                    <td><input class="input_data" type="text"
						id="nome" name="nome" placeholder="Nome do Jogador"></td>
                </tr>
                <tr>
                    <td><input class="input_data" type="date"
						id="nascimento" name="nascimento" placeholder="Data de Nascimento"></td>
                </tr>
                <tr>
                    <td><input class="input_data" type="number"
						id="altura" name="altura" step="0.01" placeholder="Altura"></td>
                </tr>
                <tr>
                    <td><input class="input_data" type="number"
						id="peso" name="peso" step="0.01" placeholder="Peso"></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" id="TimecodigoHidden"
						name="Timecodigo" value="">
                        <input type="hidden" name="cmd" id="cmd"
						value="">
                        <input type="submit" id="EnviarExcluir"
						name="botao" value="Excluir"
						onclick="document.getElementById('cmd').value = 'Excluir';">
                        <input type="submit" id="EnviarAtualizar"
						name="botao" value="Atualizar"
						onclick="document.getElementById('cmd').value = 'Alterar';">
                        <input type="submit" id="EnviarCadastrar"
						name="botao" value="Cadastrar"
						onclick="document.getElementById('cmd').value = 'Cadastrar';">
                        <input type="submit" id="EnviarListar"
						name="botao" value="Listar"
						onclick="document.getElementById('cmd').value = 'Listar';">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <br />
    <br />
    <div align="center">
        <c:if test="${not empty erro}">
            <h2>
				<b><c:out value="${erro}" /></b>
			</h2>
        </c:if>
    </div>

    <script>
        function preencherTime() {
            var timeSelect = document.getElementById("Timecodigo");
            var selectedTime = timeSelect.options[timeSelect.selectedIndex].value;
            document.getElementById("TimecodigoHidden").value = selectedTime;
        }
    </script>
</body>
</html>
