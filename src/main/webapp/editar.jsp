<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import = "br.ufpb.ci.mirrorfashion.model.Cliente" %>
    
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuário | Mirror Fashion</title>
</head>
<body>
<h1>SISTEMA DE GERENCIAMENTO DE USUARIOS</h1>

<form method="post" action="client">
	<fieldset>
	 	<legend>Usuário</legend>
	 	<% Cliente c = (Cliente)request.getAttribute("cliente"); %>
	 	<input type="number" name="id" value="<% out.print(c.getId()); %>" hidden/>
		Login: <input id = "login" type="text" value="<% out.print(c.getLogin()); %>" name="login"/><br><br>
		Senha: <input type="password" value="<% out.print(c.getSenha()); %>" name="senha"><br><br>
		Status:	<% if (c.getStatus().equalsIgnoreCase("ativo")) {%> 
		<input type="radio" name="statusCad" value="ativo" checked>ativo <input type="radio" name="statusCad" value="inativo">inativo<% } else { %><input type="radio" name="statusCad" value="ativo">ativo <input type="radio" name="statusCad" value="inativo" checked>inativo <%} %><br><br>
				 <input type="submit" value="Atualizar"/><br><br>
	</fieldset>
</form>
<br>
</body>
</html>