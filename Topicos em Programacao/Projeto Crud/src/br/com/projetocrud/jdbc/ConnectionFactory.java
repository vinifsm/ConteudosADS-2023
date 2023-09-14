package br.com.projetocrud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdcrud","user","123");
			
		} catch (Exception erro) {
			throw new RuntimeException(erro);
		}
		
	}

}
