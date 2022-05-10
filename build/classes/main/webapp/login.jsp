<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import = "br.ufpb.ci.mirrorfashion.model.Cliente" %>
    
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
        <link href="/css/layout.css"
        rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Autenticação de Usuário</h1>
        <%
			Object msg = request.getAttribute("msg");
			if(msg!=null){
				String msgStr = (String)msg;
		%>
			
			<div id="alertCad"><i style='font-size:24px' class='far'>&#xf058;</i><%out.print(msg);%></div><br>
		
		<% } %>
        <form method="post" action="login">
            <table>
                <tr>
                    <th>Login: </th>
                    <td><input type="text" name="login"
                               value=""/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="bOK" value="Entrar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>