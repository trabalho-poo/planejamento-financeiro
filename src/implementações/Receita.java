package implementações;

public class Receita extends Movimentacao {
	private TipoReceita tipo;

	/**
	 * @param _data
	 * @param _descricao
	 * @param _valor
	 * @param tipo
	 */
	public Receita(Data _data, String _descricao, Double _valor, TipoReceita _tipo) {
		super(_data, _descricao, _valor);
		this.setTipo(_tipo);
	}

	/**
	 * @return the tipo
	 */
	public TipoReceita getTipo() {
		return this.tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoReceita _tipo) {
		this.tipo = _tipo;
	}


}
