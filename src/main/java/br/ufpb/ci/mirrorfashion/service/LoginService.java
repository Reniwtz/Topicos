package br.ufpb.ci.mirrorfashion.service;

import br.ufpb.ci.mirrorfashion.control.LoginManager;
import br.ufpb.ci.mirrorfashion.model.Cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private LoginManager loginManager;
	
	private Connection c;
	
	public LoginService() {
		super();
		System.out.println();
	}
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");	
			c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/simplehr", "sa", "");
			loginManager = new LoginManager(c);
			super.init();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		Cliente cli = new Cliente();
		
		cli.setLogin(login);
		cli.setSenha(senha);
		
		boolean r_login = loginManager.login(cli);
		RequestDispatcher dispatcher;
		
		if (r_login == true) {
			request.setAttribute("msg", "Login realizado com Sucesso!");
			dispatcher = request.getRequestDispatcher("cliente.jsp");
		} else {
			request.setAttribute("msg", "Falha ao realizar login!");
			dispatcher = request.getRequestDispatcher("login.jsp");
		}
		dispatcher.forward(request, response);
	}

}
