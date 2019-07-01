package implementacoes;

/**
 * Classe para a de Movimentacoes do tipo Despesa, que sera
 * utilizada ao longo do programa.
 * 
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
 * @version 1.0 (junho-2019)
 */
public class Despesa extends Movimentacao {
	private TipoDespesa tipo;
	
	/**
	 * Construtor completo para criacao de Despesa
	 * @param _data
	 * @param _descricao
	 * @param _valor
	 * @param _tipo
	 */
	public Despesa(Data _data, String _descricao, Double _valor, TipoDespesa _tipo) {
		super(_data, _descricao, _valor);
		this.setTipo(_tipo);
	}

	/**
	 * Metodo parar retornar o tipo da movimentacao.
	 * @return tipo de movimentacao
	 */
	public TipoDespesa getTipo() {
		return this.tipo;
	}

	/**
	 * Setar o tipo da movimentacao.
	 * @param _tipo de movimentacao.
	 */
	public void setTipo(TipoDespesa _tipo) {
		this.tipo = _tipo;
	}
	
	/**
	 * Sobreposicaoo do metodo toString para retornar todo o conteudo do objeto
	 * 
	 * @return retorna uma String com todos os dados cadastrados da receita 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nTipo: ");
		builder.append(getTipo());
		builder.append(super.toString());
		return builder.toString();
	}
}
