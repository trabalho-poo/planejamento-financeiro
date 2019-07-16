package implementacoes;

/**
 * Classe para a geracao de Movimentacoes do tipo Receita, que contem
 * metodos para criar e editar receitas, que sera utilizada ao longo do programa.
 * 
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
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
	 * Metodo para retornar o tipo da movimentacao.
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

	public int compareTipo(Receita _receita) {
		String aux;
		String aux2;
		if (_receita.getTipo() == TipoReceita.SALARIO) {
			aux = "salario";
		}
		else{
			aux = "outro";
		}
		if (this.getTipo() == TipoReceita.SALARIO) {
			aux2 = "salario";
		}
		else{
			aux2 = "outro";
		}
		return (aux2.compareToIgnoreCase(aux));
	}
}
