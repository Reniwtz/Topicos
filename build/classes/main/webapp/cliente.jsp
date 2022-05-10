<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import = "br.ufpb.ci.mirrorfashion.model.Cliente" %>
    
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gerenciamento de Usuários | Mirror Fashion</title>
<script src="js/jquery.js"></script>
<script src="js/cliente.js"></script>
</head>
<body>
<h1>SISTEMA DE GERENCIAMENTO DE USUARIOS</h1>


<%
	Object msg = request.getAttribute("msg");
	if(msg!=null){
		String msgStr = (String)msg;
%>
	
	<div id="alertCad"><i style='font-size:24px' class='far'>&#xf058;</i><%out.print(msg);%></div><br>

<% } %>

<form method="post" action="client">
	<fieldset>
	 	<legend>Cadastro</legend>
		Login: <input id = "login" type="text" value="" name="login"/><br><br>
		Senha: <input type="password" value="" name="senha"><br><br>
		Status: <input type="radio" name="statusCad" value="ativo">ativo  <input type="radio" name="statusCad" value="inativo">inativo<br><br>
				 <input type="submit" value="Cadastrar"/><br><br>
	</fieldset>
</form>
<br>
<table>
<thead>
<tr>
<th>LOGIN</th>
<th>SENHA</th>
<th>STATUS</th>
<th>  </th>
</tr>
</thead>
<tbody>
<tr>
<%
try {
List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
for (Cliente c:lista){
%>
	<td><% out.print(c.getLogin()); %></td>
	<td><% out.print(c.getSenha()); %></td>
	<td><% out.print(c.getStatus()); %></td>
	<td><%out.print("<a href='?action=delete&id="+c.getId()+"'>excluir</a>"); %></td>
	<td><%out.print("<a href='?action=edit&id="+c.getId()+"'>editar</a>"); %></td>
</tr>

<%}} catch (RuntimeException e){%>
	<td> - </td>
	<td> - </td>
	<td> - </td>
</tr>

<%} %>
</tbody>
</table>
</body>
</html>