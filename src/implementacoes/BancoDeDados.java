package implementacoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoDeDados {
	public static final String PREFIX 		= "jdbc:mysql:";
	public static final String HOSTNAME 	= "localhost";
	public static final String PORT 		= "3306";
	public static final String DATABASE 	= "planejamento";
	public static final String TIMEZONE 	= "useTimezone=true&serverTimezone=UTC";
	public static final String USER 		= "root";
	public static final String PASSWORD 	= "skxkffldk";
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public BancoDeDados() {
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
	}
	
	public void conectar() throws Exception {
		// monta a url do banco (exemplo: jdbc:mysql://localhost:3306/compras?useTimezone=true&serverTimezone=UTC) 
		String url = BancoDeDados.PREFIX + "//" + BancoDeDados.HOSTNAME + ":" + BancoDeDados.PORT + "/" + BancoDeDados.DATABASE + "?" + BancoDeDados.TIMEZONE;
		// estabele uma conex�o com o banco de dados em 'url' 
		this.connection = DriverManager.getConnection(url,  BancoDeDados.USER, BancoDeDados.PASSWORD);
		this.statement = this.connection.createStatement();
	}
	
	public boolean isConectado() throws Exception{
		if(this.connection != null)
			return true;
		else
			return false;
	}
	
	public void listarUsuarios() {
		try {
			String query = "SELECT * FROM usuario ORDER BY nome";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultSet.next()) {
				StringBuilder resultset = new StringBuilder();
				resultset.append("\nID: ");
				resultset.append(this.resultSet.getString("id"));
				resultset.append(" - Nome: ");
				resultset.append(this.resultSet.getString("nome"));
				System.out.println(resultset.toString());
			}
		}catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void listarMovimentacoes() {
		try {
			String query = "SELECT * FROM movimentacao ORDER BY data";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultSet.next()) {
				StringBuilder resultset = new StringBuilder();
				resultset.append("\nID: ");
				resultset.append(this.resultSet.getString("id"));
				resultset.append(" - Data: ");
				resultset.append(this.resultSet.getString("data"));
				System.out.println(resultset.toString());
			}
		}catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	
}
