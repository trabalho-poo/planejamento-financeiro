package implementacoes;

/**
 * Oferece metodos de cadastro e edicao de informacoes de usuarios, assim como
 * possui um metodo para calculo do valor do saldo atual.
 * <p>
 *
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
 * @version 1.0 (junho-2019)
 *
 */
public class Usuario {
	/** Campos de variaveis */
	private String nome;
	private Sexo sexo;
	private Data dataNascimento;
	private String rg;
	private String cpf;
	private String email;
	private String senha;

	/**
	 * Construtor Completo de Usuario
	 * 
	 * @param _nome
	 * @param _sexo
	 * @param _dataNascimento
	 * @param _rg
	 * @param _cpf
	 * @param _email
	 * @param _senha
	 */
	public Usuario(String _nome, Sexo _sexo, Data _dataNascimento, String _rg, String _cpf, String _email,
			String _senha) {
		this.setNome(_nome);
		this.setSexo(_sexo);
		this.setDataNascimento(_dataNascimento);
		this.setRg(_rg);
		this.setCpf(_cpf);
		this.setEmail(_email);
		this.setSenha(_senha);
	}

	/**
	 * @return O nome do Usuario
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @return O sexo do usuario
	 */
	public Sexo getSexo() {
		return this.sexo;
	}

	/**
	 * Retorna a data de nascimento do Usuario
	 * 
	 * @return Data
	 */
	public Data getDataNascimento() {
		return this.dataNascimento;
	}

	/**
	 * @return O RG do usuario
	 */
	public String getRg() {
		return this.rg;
	}

	/**
	 * @return O CPF do usuario
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Setar o nome do Usuario
	 * 
	 * @param _nome do usuario
	 */
	public void setNome(String _nome) {
		this.nome = _nome;
	}

	/**
	 * Setar o sexo do Usuario
	 * 
	 * @param _sexo do usuario
	 */
	public void setSexo(Sexo _sexo) {
		this.sexo = _sexo;
	}

	/**
	 * Setar a data de Nascimento do Usuario
	 * @param _dataNascimento
	 */
	public void setDataNascimento(Data _dataNascimento) {
		this.dataNascimento = _dataNascimento;
	}

	/**
	 * Setar o RG do usuario
	 * @param _rg do usuario
	 */
	public void setRg(String _rg) {
		this.rg = _rg;
	}

	/** 
	 * Metodo para retornar o email do usuario.
	 * @return O email do usuario.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo para retornar a Senha do usuario
	 * @return A senha do usuario.
	 */
	public String getSenha() {
		return this.senha;
	}

	/**
	 * Setar o CPF do usuario
	 * @param _cpf do usuario
	 */
	public void setCpf(String _cpf) {
		this.cpf = _cpf;
	}

	/**
	 * Setar o Email do Usuario
	 * @param _email do usuario
	 */
	public void setEmail(String _email) {
		this.email = _email;
	}

	/**
	 * Setar a senha para login do usuario
	 * @param _senha do usuario
	 */
	public void setSenha(String _senha) {
		this.senha = _senha;
	}
	
	/**
	 * Compara os usuarios por nome em ordem alfabetica.
	 * 
	 * @return retorna 1 caso o Nome do objeto for maior que o do parametro,
	 * retorna -1 caso seja menor, 0 caso sejam iguais.
	 */
	public int compareToNome(Usuario _usuario) {
		return (this.getNome().compareToIgnoreCase(_usuario.getNome()));
	}

	/**
	 * Compara os usuarios por Data de Nascimento em ordem alfabetica.
	 * 
	 * @return retorna 1 caso o Data do objeto for maior que a do parametro,
	 * retorna -1 caso seja menor, 0 caso sejam iguais.
	 */
	public int compareToDataNascimento(Usuario _usuario) {
		return (this.getDataNascimento().compareTo(_usuario.getDataNascimento()));
	}

	/**
	 * Sobreposicaoo do metodo toString para retornar todo o conteudo do objeto
	 * 
	 * @return retorna uma String com todos os dados cadastrados do usuario (exceto
	 *         senha)
	 */
	public String toString() {
		StringBuilder dados = new StringBuilder();
		dados.append("\nNome: ");
		dados.append(this.getNome());
		dados.append("\nSexo: ");
		dados.append(this.getSexo());
		dados.append("\nData de Nascimento: ");
		dados.append(this.getDataNascimento());
		dados.append("\nRG: ");
		dados.append(this.getRg());
		dados.append("\nCPF: ");
		dados.append(this.getCpf());
		dados.append("\nEmail: ");
		dados.append(this.getEmail());
		return dados.toString();
	}
}
