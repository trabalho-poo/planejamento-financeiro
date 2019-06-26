package implementacoes;

/**
 * Oferece métodos de cadastro e edicao de informacoes de usuarios, assim como
 * possui um metodo para calculo do valor do saldo atual.
 * <p>
 * blabla
 *
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natália Lopes)
 * @version 1.0 (junho-2019)
 *
 */

public class Usuario {
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
	 * @return the rg
	 */
	public String getRg() {
		return this.rg;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Seta o nome do Usuario
	 * 
	 * @param _nome
	 */
	public void setNome(String _nome) {
		this.nome = _nome;
	}

	/**
	 * Seta o sexo do Usuario
	 * 
	 * @param _sexo
	 */
	public void setSexo(Sexo _sexo) {
		this.sexo = _sexo;
	}

	/**
	 * @param _dataNascimento
	 */
	public void setDataNascimento(Data _dataNascimento) {
		this.dataNascimento = _dataNascimento;
	}

	/**
	 * @param rg
	 */
	public void setRg(String _rg) {
		this.rg = _rg;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return this.senha;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String _cpf) {
		this.cpf = _cpf;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String _email) {
		this.email = _email;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String _senha) {
		this.senha = _senha;
	}
	
	
	/**
	 * Compara os usuários por nome em ordem alfabetica.
	 * 
	 * @return retorna 1 caso 	 */
	public int compareToNome(Usuario _usuario) {
		return (this.getNome().compareToIgnoreCase(_usuario.getNome()));
	}

	public int compareToDataNascimento(Usuario _usuario) {
		return (this.getDataNascimento().compareTo(_usuario.getDataNascimento()));
	}

	/**
	 * Sobreposição do método toString para retornar todo o conteúdo do objeto
	 * 
	 * @return retorna um String com todos os dados cadastrados do usuario (exceto
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
