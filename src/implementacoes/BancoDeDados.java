package implementacoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public void inserirContato(Usuario _usuario){
		try{
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO usuario (nome,email,senha,rg,cpf,sexo,dataNascimento_idData) VALUES (");
			query.append("'");
			query.append(_usuario.getNome());
			query.append("','");
			query.append(_usuario.getEmail());
			query.append("','");
			query.append(_usuario.getSenha());
			query.append("','");
			query.append(_usuario.getRg());
			query.append("','");
			query.append(_usuario.getCpf());
			query.append("','");
			query.append(_usuario.getSexo().toString());
			query.append("','");
			query.append(idData(_usuario.getDataNascimento()));
			query.append("');");

			this.statement.executeUpdate(query.toString());
		}catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void inserirData(Data _data) throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO data (dia,mes,ano) VALUES (");
		query.append("'");
		query.append(_data.getDia());
		query.append("','");
		query.append(_data.getMes());
		query.append("','");
		query.append(_data.getAno());
		query.append("');");

		this.statement.executeUpdate(query.toString());
	}

	public int idData(Data _data) throws SQLException{
		this.inserirData(_data);
		String query = "SELECT FIRST (idData) from data ORDER BY idData DESC";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		while(this.resultSet.next()) {
			StringBuilder resultset = new StringBuilder();
			resultset.append(this.resultSet.getString("idData"));
			return Integer.parseInt(resultset.toString());
		}
		return 0;
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
