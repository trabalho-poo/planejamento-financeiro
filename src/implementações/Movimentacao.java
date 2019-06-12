package implementações;

public class Movimentacao {
	private Data data;
	private String descricao;
	private Double valor;
	
	/**
	 * @param data
	 * @param descricao
	 * @param valor
	 */
	public Movimentacao(Data _data, String _descricao, Double _valor) {
		this.setData(_data);
		this.setDescricao(_descricao);
		this.setValor(_valor);
	}
	
	/**
	 * 
	 */
	public Movimentacao() {
	}


	/**
	 * @return the data
	 */
	public Data getData() {
		return this.data;
	}
	/**
	 * @param data the data to set
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
	 * @param descricao the descricao to set
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
	 * @param valor the valor to set
	 */
	public void setValor(Double _valor) {
		this.valor = _valor;
	}
	
	
}
