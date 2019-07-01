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
	 * @param _data
	 * @param _descricao
	 * @param _valor
	 */
	public Movimentacao(Data _data, String _descricao, Double _valor) {
		this.setData(_data);
		this.setDescricao(_descricao);
		this.setValor(_valor);
	}

	/**
	 * @return A data da movimentacao
	 */
	public Data getData() {
		return this.data;
	}
	/**
	 * @param _data 
	 */
	public void setData(Data _data) {
		this.data = _data;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}
	/**
	 * @param _descricao the descricao to set
	 */
	public void setDescricao(String _descricao) {
		this.descricao = _descricao;
	}
	/**
	 * @return the valor
	 */
	public Double getValor() {
		return this.valor;
	}
	/**
	 * @param _valor the valor to set
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
