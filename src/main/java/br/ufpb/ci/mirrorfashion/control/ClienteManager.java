package br.ufpb.ci.mirrorfashion.control;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufpb.ci.mirrorfashion.model.Cliente;

public class ClienteManager{

	private static List<Cliente> lista = new ArrayList<>();
	private Connection c;

	public ClienteManager(Connection c) {
			this.c = c;
	}
	public boolean cadastrar(Cliente cliente) {
		String QUERY = "INSERT INTO EMPLOYEE (LOGIN, SENHA, STATUS) VALUES ('" + cliente.getLogin() + "', '" + cliente.getSenha() + "', '" + cliente.getStatus() + "')";
		Statement stmt;
		try {
			stmt = this.c.createStatement();
			boolean rs = stmt.execute(QUERY);
			this.c.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Cliente> getTodosclientes(){
		List<Cliente> lista = new ArrayList<>();
		String QUERY = "SELECT * FROM EMPLOYEE";
		Cliente aux;
		Statement stmt;
		try {
			stmt = this.c.createStatement();
		
        ResultSet rs = stmt.executeQuery(QUERY);
        while(rs.next()){
           //Display values
        	aux = new Cliente();
        	aux.setId(rs.getString("ID"));
        	aux.setLogin(rs.getString("LOGIN"));
        	aux.setSenha(rs.getString("SENHA"));
        	aux.setStatus(rs.getString("STATUS"));
        	lista.add(aux);
        }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean excluir(int cliId) {
		String QUERY = "DELETE FROM EMPLOYEE WHERE ID = " + cliId;
		Statement stmt;
		try {
			stmt = this.c.createStatement();
			boolean rs = stmt.execute(QUERY);
			this.c.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Cliente getCliente(int cliId) {
		Cliente c = new Cliente();
		String QUERY = "SELECT * FROM EMPLOYEE WHERE ID = " + cliId;
		Statement stmt;
		try {
			stmt = this.c.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				c.setId(rs.getString("ID"));
				c.setLogin(rs.getString("LOGIN"));
				c.setSenha(rs.getString("SENHA"));
				c.setStatus(rs.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean atualizar(Cliente c) {
		String QUERY = "UPDATE EMPLOYEE SET LOGIN = '" + c.getLogin() + "', SENHA = '" + c.getSenha() + "', STATUS = '" + c.getStatus() + "' WHERE ID = " + c.getId();
		Statement stmt;
		try {
			stmt = this.c.createStatement();
			boolean rs = stmt.execute(QUERY);
			this.c.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<String> buscar(String st){
		
		List<String> sts = new ArrayList<>();
		
		for (Cliente c : lista) {
			if (c.getStatus().equalsIgnoreCase(st)) {
				sts.add(c.getLogin());

			}
		}
		
		return sts;
	
	}
	
	public String statusBusc(String st) {
		return st;
	}
	
}

