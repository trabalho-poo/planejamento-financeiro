package implementacoes;

/**
 * Classe para a geracao de Movimentacoes do tipo Receita, que contem
 * metodos para criar e editar receitas, que sera utilizada ao longo do programa.
 * 
 * @author Guilherme Mattos
 * @version 1.0 (junho-2019)
 */
public class Receita extends Movimentacao {
	private TipoReceita tipo;

	/**
	 * Construtor completo para criacao de Receita.
	 * 
	 * @param _data
	 * @param _descricao
	 * @param _valor
	 * @param _tipo
	 */
	public Receita(Data _data, String _descricao, Double _valor, TipoReceita _tipo) {
		super(_data, _descricao, _valor);
		this.setTipo(_tipo);
	}

	/**
	 * Metodo parar retornar o tipo da movimentacao.
	 * @return tipo de movimentacao
	 */
	public TipoReceita getTipo() {
		return this.tipo;
	}

	/**
	 * Setar o tipo da movimentacao.
	 * @param _tipo de movimentacao.
	 */
	public void setTipo(TipoReceita _tipo) {
		this.tipo = _tipo;
	}


}
