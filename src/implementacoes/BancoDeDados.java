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
	public static final String PASSWORD = "";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public BancoDeDados() {
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
	}

	/**
	 * Faz conexão com o banco de dados
	 */
	public void conectar() throws Exception {

		// Class.forName("com.mysql.jdbc.Driver");

		// monta a url do banco (exemplo:
		// jdbc:mysql://localhost:3306/compras?useTimezone=true&serverTimezone=UTC)
		String url = BancoDeDados.PREFIX + "//" + BancoDeDados.HOSTNAME + ":" + BancoDeDados.PORT + "/"
				+ BancoDeDados.DATABASE + "?" + BancoDeDados.TIMEZONE;

		// estabele uma conexï¿½o com o banco de dados em 'url'
		this.connection = DriverManager.getConnection(url, BancoDeDados.USER, BancoDeDados.PASSWORD);
		this.statement = this.connection.createStatement();
	}

	/**
	 * Verifica a conexão do banco de dados
	 * 
	 * @return true para conexão realizada com sucesso
	 *         <p>
	 *         false banco de dados não conectado
	 */
	public boolean isConectado() throws Exception {
		if (this.connection != null)
			return true;
		else
			return false;
	}

	/**
	 * Faz conexão com o banco de dados
	 */
	public String listarUsuarios() {
		try {
			String query = "SELECT * FROM usuario ORDER BY nome";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			StringBuilder resultset = new StringBuilder();
			while (this.resultSet.next()) {
				resultset.append("\nID: ");
				resultset.append(this.resultSet.getString("id"));
				resultset.append(" - Nome: ");
				resultset.append(this.resultSet.getString("nome"));
			}
			return resultset.toString();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Insere um usuário no banco de dados
	 * 
	 * @param _usuario Uma instancia da classe Usuario
	 */
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

	/**
	 * Faz uma autenticação de um usuario
	 * 
	 * @param _email E-mail do usuário
	 * @param _senha Senha do Usuário
	 * @return true caso o email e senha estejam cadastrados e corretos
	 *         <p>
	 *         false para usuário não cadastrado ou alguma informação esteja
	 *         incorreta
	 */
	public boolean acesso(String _email, String _senha) throws SQLException {
		String query = "SELECT senha FROM planejamento.Usuario WHERE email='" + _email + "';";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		if (this.resultSet.next()) {
			if (this.resultSet.getString("senha").equals(_senha))
				return true;
		}
		return false;
	}

	/**
	 * Insere uma data no banco de dados
	 * 
	 * @param _data Uma instancia da classe Data
	 */
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

	/**
	 * Apartir de uma data retorna o Id da mesma
	 * 
	 * @param _data Uma instancia da classe Data
	 * @return o id da data
	 */
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

	public String getStringData(int _idData) throws SQLException {
		StringBuilder data = new StringBuilder();
		String query = "SELECT * from planejamento.Data WHERE idData=" + _idData + ";";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		while (this.resultSet.next()) {
			data.append(Integer.parseInt(this.resultSet.getString("dia")));
			data.append("/");
			data.append(Integer.parseInt(this.resultSet.getString("mes")));
			data.append("/");
			data.append(Integer.parseInt(this.resultSet.getString("ano")));
		}
		return data.toString();
	}


	/**
	 * Apartir de um email de um Usuário retorna o Id da mesmo
	 * 
	 * @param _email E-mail do usuário
	 * @return o id do usuário
	 */
	public int idUsuario(String _email) throws SQLException {
		String query = "SELECT (idUsuario) from planejamento.Usuario WHERE email='" + _email + "';";
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
		while (this.resultSet.next()) {
			StringBuilder resultset = new StringBuilder();
			resultset.append(this.resultSet.getString("idUsuario"));
			return Integer.parseInt(resultset.toString());
		}
		return 0;
	}

	/**
	 * Insere uma movimentação do tipo Receita
	 * 
	 * @param _movimentacao Uma instancia da classe Receita
	 * @para _idUsuario O id do usuário que fez a movimentação
	 */
	public void inserirMovimentacaoReceita(Receita _movimentacao, int _idUsuario) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append(
				"INSERT INTO planejamento.Movimentacao (valor,descricao,Usuario_idUsuario,Data_idData,tipo,tipoReceita) VALUES (");
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

	/**
	 * Insere uma movimentação do tipo Despesa
	 * 
	 * @param _movimentacao Uma instancia da classe Receita
	 * @para _idUsuario O id do usuário que fez a movimentação
	 */
	public void inserirMovimentacaoDespesa(Despesa _movimentacao, int _idUsuario) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append(
				"INSERT INTO planejamento.Movimentacao (valor,descricao,Usuario_idUsuario,Data_idData,tipo,tipoDespesa) VALUES (");
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

	/**
	 * Calcula o porcentagem das despesas
	 * 
	 * @return a pordecetagem das depesas
	 */
	public double getPorcentagemDespesa(int _idUsuario) {
		try {
			String query = "SELECT * FROM planejamento.Movimentacao WHERE Usuario_idUsuario=" + _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double receita = 0.0, despesa = 0.0;
			while (this.resultSet.next()) {
				if (this.resultSet.getString("tipo").equalsIgnoreCase("receita")) {
					receita += Double.parseDouble(this.resultSet.getString("valor"));
				} else {
					despesa += Double.parseDouble(this.resultSet.getString("valor"));
				}
				if ((receita + despesa) == 0.0) {
					return 0.0;
				} else {
					porcentagem = despesa * 100 / (receita + despesa);
				}
			}
			return porcentagem;
		} catch (Exception e) {
			
			System.out.println("Erro1: " + e.getMessage());
			return 0.0;
		}

	}

	/**
	 * Calcula o porcentagem das receita
	 * 
	 * @return a pordecetagem das receita
	 */
	public double getPorcentagemReceita(int _idUsuario) {
		try {
			String query = "SELECT * FROM planejamento.Movimentacao WHERE Usuario_idUsuario=" + _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double receita = 0.0, despesa = 0.0;
			while (this.resultSet.next()) {
				if (this.resultSet.getString("tipo").equalsIgnoreCase("receita")) {
					receita += Double.parseDouble(this.resultSet.getString("valor"));
				} else {
					despesa += Double.parseDouble(this.resultSet.getString("valor"));
				}
				if ((receita + despesa) == 0.0) {
					return 0;
				} else {
					porcentagem = receita * 100 / (receita + despesa);
				}
			}
			return porcentagem;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return 0;
		}

	}

	/**
	 * Calcula o porcentagem do tipo
	 * 
	 * @return a pordecetagem do tipo
	 */
	public double getPorcentagemTipo(String _tipoGeral, String _tipoEspecifico, int _idUsuario) {
		try {
			String query = "SELECT * FROM planejamento.Movimentacao  WHERE Usuario_idUsuario=" + _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double especifico = 0.0, total = 0.0;
			while (this.resultSet.next()) {
				if (_tipoGeral.equalsIgnoreCase("RECEITA")) {
					if (this.resultSet.getString("tipo").equalsIgnoreCase(_tipoGeral)) {
						if (this.resultSet.getString("tipoReceita").equalsIgnoreCase(_tipoEspecifico)) {
							especifico += Double.parseDouble(this.resultSet.getString("valor"));
							total += Double.parseDouble(this.resultSet.getString("valor"));
						} else {
							total += Double.parseDouble(this.resultSet.getString("valor"));
						}
					} else {
						total += Double.parseDouble(this.resultSet.getString("valor"));
					}
				} else {
					if (this.resultSet.getString("tipo").equalsIgnoreCase(_tipoGeral)) {
						if (this.resultSet.getString("tipoDespesa").equalsIgnoreCase(_tipoEspecifico)) {
							especifico += Double.parseDouble(this.resultSet.getString("valor"));
							total += Double.parseDouble(this.resultSet.getString("valor"));
						} else {
							total += Double.parseDouble(this.resultSet.getString("valor"));
						}
					} else {
						total += Double.parseDouble(this.resultSet.getString("valor"));
					}
				}

				if (total == 0) {
					return 0;
				} else {
					porcentagem = especifico * 100 / total;
				}
			}
			return porcentagem;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * Geara uma matriz com as receitas e despesas
	 * 
	 * @return uma matriz com as receitas e despesas
	 */
	public Object[][] getMatrizDados(String _tipo, int _idUsuario) {
		int i = 0;
		try {
			String query = "SELECT * FROM planejamento.Movimentacao WHERE (Usuario_idUSuario = " + _idUsuario
					+ " AND tipo='" + _tipo + "');";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				i++;
			}
			Object[][] dados = new Object[i][2];
			i = 0;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				// Avaliando o tipo passado como parâmetro para pegar o valor do tipo específico
				if (_tipo.equalsIgnoreCase("RECEITA")) {
					dados[i][0] = this.resultSet.getString("tipoReceita");
				} else {
					dados[i][0] = this.resultSet.getString("tipoDespesa");
				}
				dados[i][1] = this.resultSet.getString("valor");
				i++;
			}
			return dados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Calcula o valor das receitas
	 * 
	 * @return O valor das receitas
	 */
	public double getValorTotal(int _idUsuario) {
		try {
			double total = 0.0;
			String query = "SELECT * FROM planejamento.Movimentacao WHERE Usuario_idUSuario = " + _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				if (this.resultSet.getString("tipo").equalsIgnoreCase("RECEITA")) {
					total += Double.parseDouble(this.resultSet.getString("valor"));
				} else {
					total -= Double.parseDouble(this.resultSet.getString("valor"));
				}
			}
			return total;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return 0.0;
		}
	}

	/**
	 * Geara uma matriz com as receitas e despesas
	 * <p>
	 * com Descrição, tipo, valor, editar, excluir
	 * 
	 * @return uma matriz com as receitas e despesas
	 */
	public Object[][] getHistorico(int _idUsuario) {
		int i = 0;
		try {
			String query = "SELECT * FROM planejamento.Movimentacao WHERE Usuario_idUSuario = " + _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				i++;
			}
			Object[][] dados = new Object[i][6];
			i = 0;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				dados[i][0] = this.resultSet.getString("descricao");
				dados[i][1] = this.resultSet.getString("tipo");
				// Avaliando o tipo passado como parâmetro para pegar o valor do tipo específico
				if (this.resultSet.getString("tipo").equalsIgnoreCase("RECEITA")) {
					dados[i][2] = this.resultSet.getString("tipoReceita");
				} else {
					dados[i][2] = this.resultSet.getString("tipoDespesa");
				}
				dados[i][3] = this.resultSet.getString("valor");
				dados[i][4] = "Editar";
				dados[i][5] = "Excluir";
				i++;
			}
			return dados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public Object[][] getRelatorio(TipoMovimentacao _tipoMovimentacao, String _tipoEspecifico, int _idUsuario) {
		int i = 0;
		String query;
		try {
			if (_tipoMovimentacao == TipoMovimentacao.TODOS) {
				query = "SELECT * FROM planejamento.Movimentacao WHERE Usuario_idUSuario = " + _idUsuario + ";";
			} else if (_tipoMovimentacao == TipoMovimentacao.RECEITA) {
				if (_tipoEspecifico.equalsIgnoreCase("todos"))
					query = "SELECT * FROM planejamento.Movimentacao WHERE (Usuario_idUSuario = " + _idUsuario
							+ " AND tipo='" + _tipoMovimentacao + "');";
				else
					query = "SELECT * FROM planejamento.Movimentacao WHERE (Usuario_idUSuario = " + _idUsuario
							+ " AND tipo='" + _tipoMovimentacao + "' AND tipoReceita='" + _tipoEspecifico.toUpperCase()
							+ "');";
			} else {
				if (_tipoEspecifico.equalsIgnoreCase("todos"))
					query = "SELECT * FROM planejamento.Movimentacao WHERE (Usuario_idUSuario = " + _idUsuario
							+ " AND tipo='" + _tipoMovimentacao + "');";
				else
					query = "SELECT * FROM planejamento.Movimentacao WHERE (Usuario_idUSuario = " + _idUsuario
							+ " AND tipo='" + _tipoMovimentacao + "' AND tipoDespesa='" + _tipoEspecifico.toUpperCase()
							+ "');";
			}
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				i++;
			}
			Object[][] dados = new Object[i][4];
			i = 0;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				dados[i][0] = this.resultSet.getString("descricao");
				dados[i][1] = this.resultSet.getString("tipo");
				// Avaliando o tipo passado como parâmetro para pegar o valor do tipo específico
				if (this.resultSet.getString("tipo").equalsIgnoreCase("Receita")) {
					dados[i][2] = this.resultSet.getString("tipoReceita");
				} else {
					dados[i][2] = this.resultSet.getString("tipoDespesa");
				}
				dados[i][3] = this.resultSet.getString("valor");
				i++;
			}
			return dados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}


	public Object[][] getRelatorioIntervalo(TipoMovimentacao _tipoMovimentacao, String _tipoEspecifico, int _idUsuario,
			int[] dataInicio, int[] dataFim, BancoDeDados bd) {
		int i = 0, j = 0;
		String query;
		int[][] dataBanco;
		try {
			if (_tipoMovimentacao == TipoMovimentacao.TODOS) {
				query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE Usuario_idUSuario = "
						+ _idUsuario + ";";
			} else if (_tipoMovimentacao == TipoMovimentacao.RECEITA) {
				if (_tipoEspecifico.equalsIgnoreCase("todos"))
					query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE (Usuario_idUSuario = "
							+ _idUsuario + " AND tipo='" + _tipoMovimentacao + "');";
				else
					query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE (Usuario_idUSuario = "
							+ _idUsuario + " AND tipo='" + _tipoMovimentacao + "' AND tipoReceita='"
							+ _tipoEspecifico.toUpperCase() + "');";
			} else {
				if (_tipoEspecifico.equalsIgnoreCase("todos"))
					query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE (Usuario_idUSuario = "
							+ _idUsuario + " AND tipo='" + _tipoMovimentacao + "');";
				else
					query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE (Usuario_idUSuario = "
							+ _idUsuario + " AND tipo='" + _tipoMovimentacao + "' AND tipoDespesa='"
							+ _tipoEspecifico.toUpperCase() + "');";
			}
			// Pegar a quantidade de elementos
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				if (Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
						Integer.parseInt(this.resultSet.getString("mes")),
						Integer.parseInt(this.resultSet.getString("ano")), dataInicio[0], dataInicio[1],
						dataInicio[2]) != 1
						&& Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
								Integer.parseInt(this.resultSet.getString("mes")),
								Integer.parseInt(this.resultSet.getString("ano")), dataFim[0], dataFim[1],
								dataFim[2]) != -1) {
					i++;
				}
			}
			Object[][] dados = new Object[i][4];
			i = 0;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				if (Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
						Integer.parseInt(this.resultSet.getString("mes")),
						Integer.parseInt(this.resultSet.getString("ano")), dataInicio[0], dataInicio[1],
						dataInicio[2]) != 1
						&& Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
								Integer.parseInt(this.resultSet.getString("mes")),
								Integer.parseInt(this.resultSet.getString("ano")), dataFim[0], dataFim[1],
								dataFim[2]) != -1) {
					dados[i][0] = this.resultSet.getString("descricao");
					dados[i][1] = this.resultSet.getString("tipo");
					// Avaliando o tipo passado como parâmetro para pegar o valor do tipo específico
					if (this.resultSet.getString("tipo").equalsIgnoreCase("Receita")) {
						dados[i][2] = this.resultSet.getString("tipoReceita");
					} else {
						dados[i][2] = this.resultSet.getString("tipoDespesa");
					}
					dados[i][3] = this.resultSet.getString("valor");
					i++;
				}
			}
			return dados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
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
