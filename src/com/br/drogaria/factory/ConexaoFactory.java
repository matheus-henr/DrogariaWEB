package com.br.drogaria.factory;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA= "braga17caldeira";
	private static final String URL ="jdbc:mysql://localhost:3306/drogaria?useSSL=false";
	public static Connection conecta() throws SQLException{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
		
		return conexao;
	}

}
