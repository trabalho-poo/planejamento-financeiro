package implementacoes;

/**
 * Classe para a geracao de Movimentacao que sera
 * utilizada ao longo do programa.
 * 
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
 * @version 1.0 (junho-2019)
 */
public class Movimentacao {
	private Data data;
	private String descricao;
	private Double valor;
	
	/**
	 * Construtor completo para criacao de uma Movimentacao.
	 * 
	 * @param _data A data da Movimentacao
	 * @param _descricao A descricao da Movimentacao
	 * @param _valor O valor da Movimentacao
	 */
	public Movimentacao(Data _data, String _descricao, Double _valor) {
		this.setData(_data);
		this.setDescricao(_descricao);
		this.setValor(_valor);
	}

	/**
	 * Metodo para retornar a data da Movimentacao
	 * @return A data da Movimentacao
	 */
	public Data getData() {
		return this.data;
	}
	/**
	 * Metodo para setar a data da Movimentacao
	 * @param _data data da Movimentacao
	 */
	public void setData(Data _data) {
		this.data = _data;
	}
	/**
	 * Metodo para retornar a descricao da Movimentacao
	 * @return A descricao da Movimentacao
	 */
	public String getDescricao() {
		return this.descricao;
	}
	/**
	 * Metodo para setar a descricao da Movimentacao
	 * @param _descricao descricao da Movimentacao
	 */
	public void setDescricao(String _descricao) {
		this.descricao = _descricao;
	}
	/**
	 * Metodo para retornar o valor da Movimentacao
	 * @return O valor da Movimentacao
	 */
	public Double getValor() {
		return this.valor;
	}
	/**
	 * Metodo para setar o valor da Movimentacao
	 * @param _valor O valor da Movimentacao
	 */
	public void setValor(Double _valor) {
		this.valor = _valor;
	}

	/**
	 * Sobreposicaoo do metodo toString para retornar todo o conteudo do objeto
	 * 
	 * @return retorna uma String com todos os dados cadastrados da Movimentacao. 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nData: ");
		builder.append(getData());
		builder.append("\n");
		builder.append(getDescricao());
		builder.append("\nValor: ");
		builder.append(getValor());
		return builder.toString();
	}
}
