package br.ufpb.ci.mirrorfashion.service;

import br.ufpb.ci.mirrorfashion.control.ClienteManager;
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

/**
 * Servlet implementation class ClientService
 */
@WebServlet("/client")
public class ClientService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ClienteManager clienteManager;
	
	private Connection c;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientService() {
        super();
        System.out.println();
    }
    
    
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");	
			c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/simplehr", "sa", "");
			clienteManager = new ClienteManager(c);
			super.init();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	
		
		
	}
	
	
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			int cliId = Integer.parseInt(request.getParameter("id"));
			if (action.equalsIgnoreCase("delete")) {
				clienteManager.excluir(cliId);
				request.setAttribute("msg", "Cadastro excluído com Sucesso!");
			} else if (action.equalsIgnoreCase("edit")) {
				request.setAttribute("cliente", clienteManager.getCliente(cliId));
				request.getRequestDispatcher("editar.jsp").forward(request, response);
			}
		}
		request.setAttribute("lista", clienteManager.getTodosclientes());
		request.getRequestDispatcher("cliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String statusCad = request.getParameter("statusCad");
		
		Cliente cli = new Cliente("","","");
		cli.setLogin(login);
		cli.setSenha(senha);
		cli.setStatus(statusCad);
		
		if (id != null) {
			cli.setId(id);
			
			boolean atualizar = clienteManager.atualizar(cli);
			
			if (atualizar == true) {
				request.setAttribute("msg", "Cadastro atualizado com Sucesso!");
			} else {
				request.setAttribute("msg", "Falha ao atualizar cadastro!");
			}
		} else {
			boolean cadastro = clienteManager.cadastrar(cli);
			
			if (cadastro == true) {
				request.setAttribute("msg", "Cadastrado Realizado com Sucesso!");
			} else {
				request.setAttribute("msg", "Falha ao realizar o cadastro!");
			}	
		}
			
		//BUSCA
		
		//String status = request.getParameter("status");
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
		request.setAttribute("lista", clienteManager.getTodosclientes());
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}


	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doDelete(req, resp);
		System.out.println(req.getHeader(getServletName()));;
	}


}
