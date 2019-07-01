package implementacoes;

/**
 * Classe para a geracao de Data que sera
 * utilizada ao longo do programa.
 * 
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
 * @version 1.0 (junho-2019)
 */
public class Historico {
	Movimentacao movimentacao;
	
	/**
	 * @param movimentacao
	 */
	public Historico(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	/**
	 * @return the movimentacao
	 */
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	/**
	 * @param movimentacao the movimentacao to set
	 */
	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	
}
