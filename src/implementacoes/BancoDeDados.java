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

	// BD

	/**
	 * Faz conex�o com o banco de dados
	 * @throws Exception Erro
	 */
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

	/**
	 * Verifica a conex�o do banco de dados
	 * 
	 * @return true para conex�o realizada com sucesso
	 *         <p>
	 *         false banco de dados n�o conectado
	 * @throws Exception Erro
	 */
	public boolean isConectado() throws Exception {
		if (this.connection != null)
			return true;
		else
			return false;
	}

	// USUARIO

	/**
	 * Insere um usu�rio no banco de dados
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
	 * Faz uma autentica��o de um usuario
	 * 
	 * @param _email E-mail do usu�rio
	 * @param _senha Senha do Usu�rio
	 * @return true caso o email e senha estejam cadastrados e corretos
	 *         <p>
	 *         false para usu�rio n�o cadastrado ou alguma informa��o esteja
	 *         incorreta
	 * @throws SQLException Erro
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
	 * A partir de um email de um Usu�rio retorna o Id do mesmo
	 * 
	 * @param _email E-mail do usu�rio
	 * @return int o id do usu�rio
	 * @throws SQLException Erro
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

	// DATA

	/**
	 * Insere uma data no banco de dados
	 * 
	 * @param _data Uma instancia da classe Data
	 * @throws SQLException Erro
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
	 * A partir de uma data, retorna o Id da mesma
	 * 
	 * @param _data Uma instancia da classe Data
	 * @return o id da data
	 * @throws SQLException Erro
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

	/**
	 * M�todo que retorna a data formatada no modelo D/M/Y do banco de dados ao
	 * receber seu id como parametro
	 * 
	 * @param _idData id da data do banco de dados
	 * @return String da data formatada no modelo D/M/Y
	 * @throws SQLException Erro
	 */
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

	// MOVIMENTACAO

	/**
	 * Insere uma movimenta��o do tipo Receita
	 * 
	 * @param _movimentacao Uma instancia da classe Receita
	 * @param _idUsuario O id do usu�rio que fez a movimenta��o
	 * @throws SQLException Erro
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
	 * Insere uma movimenta��o do tipo Despesa
	 * 
	 * @param _movimentacao Uma instancia da classe Receita
	 * @param _idUsuario O id do usu�rio que fez a movimenta��o
	 * @throws SQLException Erro
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
	 * M�todo que deleta uma movimenta��o do banco de dados por meio do seu id
	 * passado como parametro.
	 * 
	 * @param _idMovimentacao id da movimentacao
	 * @throws SQLException Erro
	 */
	public void deleteMovimentacao(int _idMovimentacao) throws SQLException {
		String query = "DELETE FROM `planejamento`.`movimentacao` WHERE (`idMovimentacao` = '" + _idMovimentacao + "')";
		this.statement.executeUpdate(query);
	}

	/**
	 * M�todo que deleta uma movimenta��o do banco de dados por meio dos seus campos
	 * valor, descricao e idUsuario passados como parametro.
	 * 
	 * @param _valor     valor da movimentacao
	 * @param _descricao descricao da movimentacao
	 * @param _idUsuario id do usuario
	 * @throws SQLException Erro 
	 */
	public void deleteMovimentacao(double _valor, String _descricao, int _idUsuario) throws SQLException {
		String query = "DELETE FROM `planejamento`.`movimentacao` WHERE (`valor` = '" + _valor + "' AND `descricao` = '"
				+ _descricao + "' AND `Usuario_idUsuario` = '" + _idUsuario + "')";
		this.statement.executeUpdate(query);
	}

	// RELATORIOS
	/**
	 * Calcula o porcentagem das despesas
	 * 
	 * @param _idUsuario id do usuario
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
	 * @param _idUsuario id do usuario
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
	 * @param _tipoGeral tipo Geral da movimentacao
	 * @param _tipoEspecifico tipo especifico da movimentacao
	 * @param _idUsuario id do usuario
	 * @return a porcentagem do tipo
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
	 * @param _idUsuario id do usuario
	 * @param _tipo tipo
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
				// Avaliando o tipo passado como par�metro para pegar o valor do tipo espec�fico
				if (_tipo.equalsIgnoreCase("RECEITA")) {
					dados[i][0] = this.resultSet.getString("tipoReceita");
				} else {
					dados[i][0] = this.resultSet.getString("tipoDespesa");
				}
				dados[i][1] = "R$ " + this.resultSet.getString("valor");
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
	 * @param _idUsuario id do usuario
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
	 * com Descri��o, tipo, valor, editar, excluir
	 * 
	 * @param _idUsuario id do usuario
	 * 
	 * @return uma matriz com as receitas e despesas
	 */
	public Object[][] getHistorico(int _idUsuario) {
		int i = 0;
		try {
			String query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE Usuario_idUSuario = "
					+ _idUsuario + ";";
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
				dados[i][1] = this.resultSet.getString("dia") + "/" + this.resultSet.getString("mes") + "/"
						+ this.resultSet.getString("ano");
				dados[i][2] = this.resultSet.getString("tipo");
				// Avaliando o tipo passado como par�metro para pegar o valor do tipo espec�fico
				if (this.resultSet.getString("tipo").equalsIgnoreCase("RECEITA")) {
					dados[i][3] = this.resultSet.getString("tipoReceita");
				} else {
					dados[i][3] = this.resultSet.getString("tipoDespesa");
				}
				dados[i][4] = "R$ " + this.resultSet.getString("valor");
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
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				i++;
			}
			Object[][] dados = new Object[i][5];
			i = 0;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while (this.resultSet.next()) {
				dados[i][0] = this.resultSet.getString("descricao");
				dados[i][1] = this.resultSet.getString("dia") + "/" + this.resultSet.getString("mes") + "/"
						+ this.resultSet.getString("ano");
				dados[i][2] = this.resultSet.getString("tipo");
				// Avaliando o tipo passado como par�metro para pegar o valor do tipo espec�fico
				if (this.resultSet.getString("tipo").equalsIgnoreCase("Receita")) {
					dados[i][3] = this.resultSet.getString("tipoReceita");
				} else {
					dados[i][3] = this.resultSet.getString("tipoDespesa");
				}
				dados[i][4] = "R$ " + this.resultSet.getString("valor");
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
		int i = 0;
		String query;
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
			Object[][] dados = new Object[i][5];
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
					dados[i][1] = this.resultSet.getString("dia") + "/" + this.resultSet.getString("mes") + "/"
							+ this.resultSet.getString("ano");
					dados[i][2] = this.resultSet.getString("tipo");
					// Avaliando o tipo passado como par�metro para pegar o valor do tipo espec�fico
					if (this.resultSet.getString("tipo").equalsIgnoreCase("Receita")) {
						dados[i][3] = this.resultSet.getString("tipoReceita");
					} else {
						dados[i][3] = this.resultSet.getString("tipoDespesa");
					}
					dados[i][4] = "R$ " + this.resultSet.getString("valor");
					i++;
				}
			}
			return dados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public Object[][] getMatrizDadosIntervalo(TipoMovimentacao _tipoMovimentacao, String _tipoEspecifico,
			int _idUsuario, int[] dataInicio, int[] dataFim, BancoDeDados bd) {
		int i = 0;
		String query;
		try {
			if (_tipoMovimentacao == TipoMovimentacao.RECEITA) {
				query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE (Usuario_idUSuario = "
						+ _idUsuario + " AND tipo='" + _tipoMovimentacao + "');";
			} else {
				query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE (Usuario_idUSuario = "
						+ _idUsuario + " AND tipo='" + _tipoMovimentacao + "');";
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
			Object[][] dados = new Object[i][2];
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
					// Avaliando o tipo passado como par�metro para pegar o valor do tipo espec�fico
					if (this.resultSet.getString("tipo").equalsIgnoreCase("Receita")) {
						dados[i][0] = this.resultSet.getString("tipoReceita");
					} else {
						dados[i][0] = this.resultSet.getString("tipoDespesa");
					}
					dados[i][1] = "R$ " + this.resultSet.getString("valor");
					i++;
				}
			}
			return dados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	// RELATORIO GRAFICOS
	public double getPorcentagemDespesaIntervalo(int _idUsuario, int[] dataInicio, int[] dataFim) {
		try {
			String query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE Usuario_idUsuario="
					+ _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double receita = 0.0, despesa = 0.0;
			while (this.resultSet.next()) {
				if (Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
						Integer.parseInt(this.resultSet.getString("mes")),
						Integer.parseInt(this.resultSet.getString("ano")), dataInicio[0], dataInicio[1],
						dataInicio[2]) != 1
						&& Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
								Integer.parseInt(this.resultSet.getString("mes")),
								Integer.parseInt(this.resultSet.getString("ano")), dataFim[0], dataFim[1],
								dataFim[2]) != -1) {
					if (this.resultSet.getString("tipo").equalsIgnoreCase("receita")) {
						receita += Double.parseDouble(this.resultSet.getString("valor"));
					} else {
						despesa += Double.parseDouble(this.resultSet.getString("valor"));
					}
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
	 * @param _idUsuario id do usuario
	 * @param dataInicio Data inicio do intervalo
	 * @param dataFim Data fim do intervalo
	 * @return a pordecetagem das receita
	 */
	public double getPorcentagemReceitaIntervalo(int _idUsuario, int[] dataInicio, int[] dataFim) {
		try {
			String query = "SELECT movimentacao.*, data.* FROM planejamento.Movimentacao AS movimentacao INNER JOIN planejamento.Data as data ON (movimentacao.Data_idData=data.idData) WHERE Usuario_idUsuario="
					+ _idUsuario + ";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			double porcentagem = 0.0;
			double receita = 0.0, despesa = 0.0;
			while (this.resultSet.next()) {
				if (Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
						Integer.parseInt(this.resultSet.getString("mes")),
						Integer.parseInt(this.resultSet.getString("ano")), dataInicio[0], dataInicio[1],
						dataInicio[2]) != 1
						&& Data.compareTo(Integer.parseInt(this.resultSet.getString("dia")),
								Integer.parseInt(this.resultSet.getString("mes")),
								Integer.parseInt(this.resultSet.getString("ano")), dataFim[0], dataFim[1],
								dataFim[2]) != -1) {
					if (this.resultSet.getString("tipo").equalsIgnoreCase("receita")) {
						receita += Double.parseDouble(this.resultSet.getString("valor"));
					} else {
						despesa += Double.parseDouble(this.resultSet.getString("valor"));
					}
				}

			}
			if ((receita + despesa) == 0.0) {
				return 0;
			} else {
				porcentagem = receita * 100 / (receita + despesa);
			}
			return porcentagem;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return 0;
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
