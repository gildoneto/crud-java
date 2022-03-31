package br.com.cafe.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// Nome do usuário do mysql
	private static final String USERNAME = "root";
	
	// senha do BD
	private static final String PASSWORD = "root";
	
	// caminho do banco de dados, porta, nome
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cafe?useSSL=false";
	
	// Conexão com o BD
	public static Connection createConnectionToMySQL() throws Exception {
		// Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		//Recuperar uma conexao com o BD
		Connection con = createConnectionToMySQL();
		
		// testar se a conexao é nula
		if(con != null) {
			System.out.println("Conexao obtida com sucesso");
			con.close();
		}
		}
	}
