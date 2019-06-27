package implementações;

public class Usuario {
	private String nome;
	private Sexo sexo;
	private Data dataNascimento;
	private String rg;
	private String cpf;
	private String email;
	private String senha;
	
	/**
	 * Construtor Completo de Usuário
	 * 
	 * @param _nome
	 * @param _sexo
	 * @param _dataNascimento
	 * @param _rg
	 * @param _cpf
	 * @param _email
	 * @param _senha
	 */
	public Usuario(String _nome, Sexo _sexo, Data _dataNascimento, String _rg, String _cpf, String _email, String _senha) {
		this.setNome(_nome);
		this.setSexo(_sexo);
		this.setDataNascimento(_dataNascimento);
		this.setRg(_rg);
		this.setCpf(_cpf);
		this.setEmail(_email);
		this.setSenha(_senha);
	}
	/**
	 * @return O nome do usuário
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * Pega o nome do Usuário
	 * @param _nome 
	 */
	public void setNome(String _nome) {
		this.nome = _nome;
	}
	/**
	 * @return O sexo do usuário
	 */
	public Sexo getSexo() {
		return this.sexo;
	}
	/**
	 * Pega o sexo do Usuário
	 * @param _sexo
	 */
	public void setSexo(Sexo _sexo) {
		this.sexo = _sexo;
	}
	/**
	 * Retorna a Data de Nascimento do Usuário
	 * @return dataNascimento
	 */
	public Data getDataNascimento() {
		return this.dataNascimento;
	}
	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(Data _dataNascimento) {
		this.dataNascimento = _dataNascimento;
	}
	/**
	 * @return the rg
	 */
	public String getRg() {
		return this.rg;
	}
	/**
	 * @param rg the rg to set
	 */
	public void setRg(String _rg) {
		this.rg = _rg;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return this.cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String _cpf) {
		this.cpf = _cpf;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String _email) {
		this.email = _email;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return this.senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String _senha) {
		this.senha = _senha;
	}
	
	
}
