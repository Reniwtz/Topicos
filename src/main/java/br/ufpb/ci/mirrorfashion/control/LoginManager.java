package br.ufpb.ci.mirrorfashion.control;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufpb.ci.mirrorfashion.model.Cliente;

public class LoginManager {
	
	private Connection c;
	
	public LoginManager(Connection c) {
		this.c = c;
	}
	
	public boolean login(Cliente cli) {
		String QUERY = "SELECT LOGIN, SENHA, STATUS FROM EMPLOYEE WHERE LOGIN = '" + cli.getLogin() + "' AND STATUS = 'ativo'";
		Statement stmt;
		Cliente aux = new Cliente();
		try {
			stmt = this.c.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				aux.setLogin(rs.getString("LOGIN"));
				aux.setSenha(rs.getString("SENHA"));
				aux.setStatus(rs.getString("STATUS"));
			}
			if (aux.getStatus().equalsIgnoreCase("ativo")) {
				if (aux.getLogin().equalsIgnoreCase(cli.getLogin()) && aux.getSenha().equals(cli.getSenha())) {
					return true;
				}	
			} else {
				return false;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
