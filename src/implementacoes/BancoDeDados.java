package implementacoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Oferece metodos de cadastro e edicao de informacoes no Banco de Dados.
 * <p>
 *
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
 * @version 1.0 (julho-2019)
 *
 */
public class BancoDeDados {

	public static final String PREFIX = "jdbc:mysql:";
	public static final String HOSTNAME = "localhost";
	// public static final String HOSTNAME = "127.0.0.1";
	public static final String PORT = "3306";
	public static final String DATABASE = "planejamento";
	public static final String TIMEZONE = "useTimezone=true&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public BancoDeDados() {
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
	}

	public void conectar() throws Exception {

		// Class.forName("com.mysql.jdbc.Driver");

		// monta a url do banco (exemplo:
		// jdbc:mysql://localhost:3306/compras?useTimezone=true&serverTimezone=UTC)
		String url = BancoDeDados.PREFIX + "//" + BancoDeDados.HOSTNAME + ":" + BancoDeDados.PORT + "/"
				+ BancoDeDados.DATABASE + "?" + BancoDeDados.TIMEZONE;

		// estabele uma conex�o com o banco de dados em 'url'
		this.connection = DriverManager.getConnection(url, BancoDeDados.USER, BancoDeDados.PASSWORD);
		this.statement = this.connection.createStatement();
	}

	public boolean isConectado() throws Exception {
		if (this.connection != null)
			return true;
		else
			return false;
	}

	public void listarUsuarios() {
		try {
			String query = "SELECT * FROM usuario ORDER BY nome";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				StringBuilder resultset = new StringBuilder();
				resultset.append("\nID: ");
				resultset.append(this.resultSet.getString("id"));
				resultset.append(" - Nome: ");
				resultset.append(this.resultSet.getString("nome"));
				System.out.println(resultset.toString());
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void inserirContato(Usuario _usuario) {
		try {
			StringBuilder query = new StringBuilder();
			query.append(
					"INSERT INTO planejamento.Usuario (nome,email,senha,rg,cpf,sexo,dataNascimento_idData) VALUES (");
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
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public boolean acesso(String _email, String _senha) throws SQLException {
		String query = "SELECT senha FROM planejamento.Usuario WHERE email='" + _email + "';";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		if (this.resultSet.next()) {
			System.out.println(this.resultSet.getString("senha"));
			if (this.resultSet.getString("senha").equals(_senha))
				return true;
		}
		return false;
	}

	public void inserirData(Data _data) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO planejamento.Data (dia,mes,ano) VALUES (");
		query.append("'");
		query.append(_data.getDia());
		query.append("','");
		query.append(_data.getMes());
		query.append("','");
		query.append(_data.getAno());
		query.append("');");

		this.statement.executeUpdate(query.toString());
	}

	public int idData(Data _data) throws SQLException {
		this.inserirData(_data);
		String query = "SELECT (idData) from planejamento.Data ORDER BY idData DESC LIMIT 1";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		while (this.resultSet.next()) {
			StringBuilder resultset = new StringBuilder();
			resultset.append(this.resultSet.getString("idData"));
			return Integer.parseInt(resultset.toString());
		}
		return 0;
	}
	
	public int idUsuario(String _email) throws SQLException {
		String query = "SELECT (idUsuario) from planejamento.Usuario WHERE email='"+_email+"';";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		while (this.resultSet.next()) {
			StringBuilder resultset = new StringBuilder();
			resultset.append(this.resultSet.getString("idUsuario"));
			return Integer.parseInt(resultset.toString());
		}
		return 0;
	}
	
	public void inserirMovimentacaoReceita(Receita _movimentacao, int _idUsuario) throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO planejamento.Movimentacao (valor,descricao,Usuario_idUsuario,Data_idData,tipo,tipoReceita) VALUES (");
		query.append("'");
		query.append(_movimentacao.getValor());
		query.append("','");
		query.append(_movimentacao.getDescricao());
		query.append("','");
		query.append(_idUsuario);
		query.append("','");
		query.append(idData(_movimentacao.getData()));
		query.append("','");
		query.append("RECEITA");
		query.append("','");
		query.append(_movimentacao.getTipo().toString());
		query.append("');");

		this.statement.executeUpdate(query.toString());
	}
	
	public void inserirMovimentacaoDespesa(Despesa _movimentacao, int _idUsuario) throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO planejamento.Movimentacao (valor,descricao,Usuario_idUsuario,Data_idData,tipo,tipoDespesa) VALUES (");
		query.append("'");
		query.append(_movimentacao.getValor());
		query.append("','");
		query.append(_movimentacao.getDescricao());
		query.append("','");
		query.append(_idUsuario);
		query.append("','");
		query.append(idData(_movimentacao.getData()));
		query.append("','");
		query.append("DESPESA");
		query.append("','");
		query.append(_movimentacao.getTipo().toString());
		query.append("');");

		this.statement.executeUpdate(query.toString());
	}

	public String stringData(int idData) {
		return "a";
	}

	public void listarMovimentacoes() {
		try {
			String query = "SELECT * FROM planejamento.Movimentacao ORDER BY data";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				StringBuilder resultset = new StringBuilder();
				resultset.append("\nID: ");
				resultset.append(this.resultSet.getString("id"));
				resultset.append(" - Data: ");
				resultset.append(this.resultSet.getString("data"));
				System.out.println(resultset.toString());
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public double getPorcentagemDespesa() {
		try {
			String query = "SELECT * FROM planejamento.Movimentacao";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double receita = 0.0, despesa = 0.0;
			while (this.resultSet.next()) {
				StringBuilder resultset = new StringBuilder();
				if(this.resultSet.getString("tipo").equalsIgnoreCase("receita")) {
					receita += Double.parseDouble(this.resultSet.getString("valor"));
				}else {
					despesa += Double.parseDouble(this.resultSet.getString("valor"));
				}
				if((receita+despesa) == 0.0) {
					return 0.0;
				}else {
					porcentagem = despesa*100/(receita+despesa);
				}
			}
			return porcentagem;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return 0.0;
		}
		
	}
	
	public double getPorcentagemReceita() {
		try {
			String query = "SELECT * FROM planejamento.Movimentacao";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double receita = 0.0, despesa = 0.0;
			while (this.resultSet.next()) {
				if(this.resultSet.getString("tipo").equalsIgnoreCase("receita")) {
					receita += Double.parseDouble(this.resultSet.getString("valor"));
				}else {
					despesa += Double.parseDouble(this.resultSet.getString("valor"));
				}
				if((receita+despesa) == 0.0) {
					return 0;
				}else {
					porcentagem = receita*100/(receita+despesa);
				}
			}
			return porcentagem;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return 0;
		}
		
	}
	public double getPorcentagemTipo(String _tipoGeral, String _tipoEspecifico) {
		return 0;
	}

	// Codigo banco de dados:
//	-- -----------------------------------------------------
//	-- Table `planejamento`.`Data`
//	-- -----------------------------------------------------
//	CREATE TABLE IF NOT EXISTS `planejamento`.`Data` (
//	  idData INT NOT NULL AUTO_INCREMENT,
//	  dia INT NULL,
//	  mes INT NULL,
//	  ano INT NULL,
//	  PRIMARY KEY (`idData`)
//	  );
//
//	-- -----------------------------------------------------
//	-- Table `planejamento`.`Usuario`
//	-- -----------------------------------------------------
//	CREATE TABLE IF NOT EXISTS `planejamento`.`Usuario` (
//	  idUsuario INT NOT NULL AUTO_INCREMENT,
//	  nome VARCHAR(45) NOT NULL,
//	  email VARCHAR(45) NOT NULL UNIQUE,
//	  senha VARCHAR(45) NOT NULL,
//	  dataNascimento_idData INT NOT NULL,
//	  FOREIGN KEY (`dataNascimento_idData`)
//	  REFERENCES `planejamento`.`Data` (`idData`)
//	  ON DELETE NO ACTION,
//	  rg VARCHAR(45) NOT NULL,
//	  cpf VARCHAR(45) NOT NULL,
//	  sexo ENUM('MASCULINO', 'FEMININO') NOT NULL,
//	  PRIMARY KEY (`idUsuario`)
//	  );
//
//	-- -----------------------------------------------------
//	-- Table `planejamento`.`Movimentacao`
//	-- -----------------------------------------------------
//	CREATE TABLE IF NOT EXISTS `planejamento`.`Movimentacao` (
//	  idMovimentacao INT NOT NULL AUTO_INCREMENT,
//	  valor DOUBLE NOT NULL,
//	  descricao VARCHAR(45) NOT NULL,
//	  Usuario_idUsuario INT NOT NULL,
//	  Data_idData INT NOT NULL,
//	  tipo ENUM('RECEITA', 'DESPESA') NOT NULL,
//	  tipoReceita ENUM('SALARIO', 'OUTROS') NULL,
//	  tipoDespesa ENUM('ACADEMIA', 'AGUA', 'ALUGUEL', 'CLUBE', 'INTERNET', 'TELEFONE', 'LUZ', 'SUPERMERCADO', 'OUTROS') NULL,
//	  PRIMARY KEY (`idMovimentacao`),
//	    FOREIGN KEY (`Usuario_idUsuario`)
//	    REFERENCES `planejamento`.`Usuario` (`idUsuario`)
//	    ON DELETE NO ACTION,
//	    FOREIGN KEY (`Data_idData`)
//	    REFERENCES `planejamento`.`Data` (`idData`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);

}
