package implementacoes;

public class Despesa  extends Movimentacao {
	private TipoDespesa tipo;
	
	/**
	 * @param _data
	 * @param _descricao
	 * @param _valor
	 * @param tipo
	 */
	public Despesa(Data _data, String _descricao, Double _valor, TipoDespesa _tipo) {
		super(_data, _descricao, _valor);
		this.setTipo(_tipo);
	}

	/**
	 * @return the tipo
	 */
	public TipoDespesa getTipo() {
		return this.tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoDespesa _tipo) {
		this.tipo = _tipo;
	}
	
}
