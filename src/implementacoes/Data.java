package implementacoes;

/**
 * Classe para a geracao de Data que sera utilizada ao longo do programa.
 * 
 * @author Grupo (Cleisson diLauro, Franco Flores, Guilherme Mattos, Luciano
 *         Alves, Natalia Lopes)
 * @version 1.0 (junho-2019)
 */
public class Data {
	/** dia como um inteiro */
	private int dia;
	/** mes como um inteiro */
	private int mes;
	/** ano como um inteiro */
	private int ano;

	/**
	 * Construtor com data vazia. Atribui o data default 01/01/1900.
	 */
	public Data() {
		this.setDia(1);
		this.setMes(1);
		this.setAno(1900);
	}

	/**
	 * Construtor somente com o mes e ano. Atribui o dia default 1.
	 * 
	 * @param _mes mes da data como um inteiro
	 * @param _ano ano da data como um inteiro
	 */
	public Data(int _mes, int _ano) throws Exception {
		this.setData(1, _mes, _ano);
	}

	/**
	 * Construtor com Data completa.
	 * 
	 * @param _dia dia da data como um inteiro
	 * @param _mes mes da data como um inteiro
	 * @param _ano ano da data como um inteiro
	 */
	public Data(int _dia, int _mes, int _ano) throws Exception {
		this.setData(_dia, _mes, _ano);
	}

	/**
	 * Construtor com mes em string, dia e ano em Inteiro.
	 * 
	 * @param _dia dia da data como um inteiro
	 * @param _mes mes da data como uma String
	 * @param _ano ano da data como um inteiro
	 */
	public Data(int _dia, String _mes, int _ano) throws Exception {
		this.setData(_dia, this.setMes(_mes), _ano);
	}

	/**
	 * Construtor com Data completa passada em string
	 * 
	 * @param data data completa passada como String
	 */
	public Data(String data) throws Exception {
		setDataString(data);
	}

	/**
	 * Pega todos os valores para a data a data
	 * 
	 * @param _dia dia da data como um inteiro.
	 * @param _mes mes da data como um inteiro.
	 * @param _ano ano da data como um inteiro.
	 * @throws Exception Caso a data nao esteja em formato valido.
	 */
	public void setData(int _dia, int _mes, int _ano) throws Exception {
		if (!Data.isDataValida(_dia, _mes, _ano)) {
			throw new Exception("Data invalida");
		} else {
			this.setDia(_dia);
			this.setMes(_mes);
			this.setAno(_ano);
		}

	}

	/**
	 * Pega a data em String para atribuir aos campos dia, mes e ano.
	 * 
	 * @param data data como uma String.
	 * @throws Exception Caso a data nao esteja em formato valido.
	 */
	public void setDataString(String data) throws Exception {
		// Confere se a string possui o tamanho correto para ser do tipo d/m/aaaa ou
		// dd/mm/aaaa
		if (data.length() >= 8 || data.length() <= 10) {
			// Confere a localizacao do caracter / para saber se e do tipo d/ ou dd/
			if ((data.indexOf("/") == 1)) {
				this.dia = Integer.parseInt(data.substring(0, 1));

				// Confere a localizacao do caracter / para saber se e do tipo m/ ou mm/
				if (data.indexOf("/", 2) == 3) {
					this.mes = Integer.parseInt(data.substring(2, 3));
					this.ano = Integer.parseInt(data.substring(4, 8));
				}

				else if (data.indexOf("/", 2) == 4) {
					this.mes = Integer.parseInt(data.substring(2, 4));
					this.ano = Integer.parseInt(data.substring(5, 9));
				}
			}

			else if (data.indexOf("/") == 2) {
				this.dia = Integer.parseInt(data.substring(0, 2));

				// Confere a localizacao do caracter / para saber se e do tipo m/ ou mm/
				if (data.indexOf("/", 3) == 4) {
					this.mes = Integer.parseInt(data.substring(3, 4));
					this.ano = Integer.parseInt(data.substring(5, 9));
				}

				else if (data.indexOf("/", 3) == 5) {
					this.mes = Integer.parseInt(data.substring(3, 5));
					this.ano = Integer.parseInt(data.substring(6, 10));
				}
			}
		}
		if (!Data.isDataValida(this.dia, this.mes, this.ano)) {
			throw new Exception("Data invalida");
		}
	}

	/**
	 * Pega a String _mes e atribui o inteiro correspondente ao campo mes da Data.
	 * 
	 * @param _mes mes da data como uma String.
	 */
	public int setMes(String _mes) {
		_mes = _mes.toLowerCase();

		if (_mes.equals("janeiro"))
			return 1;
		if (_mes.equals("fevereiro"))
			return 2;
		if (_mes.equals("marï¿½o"))
			return 3;
		if (_mes.equals("abril"))
			return 4;
		if (_mes.equals("maio"))
			return 5;
		if (_mes.equals("junho"))
			return 6;
		if (_mes.equals("julho"))
			return 7;
		if (_mes.equals("agosto"))
			return 8;
		if (_mes.equals("setembro"))
			return 9;
		if (_mes.equals("outubro"))
			return 10;
		if (_mes.equals("novembro"))
			return 11;
		if (_mes.equals("dezembro"))
			return 12;
		else
			return 0;
	}

//	public Data getData() {
//		System.out.println(this.dia + "/" + this.mes + "/" + this.ano);
//		return this;
//	}

	/**
	 * Metodo para retornar o dia do objeto.
	 * 
	 * @return dia dia do objeto como um inteiro.
	 */
	public int getDia() {
		return this.dia;
	}

	/**
	 * Metodo para atribuir o dia para Data.
	 * 
	 * @param _dia da Data como um inteiro.
	 */
	public void setDia(int _dia) {
		this.dia = _dia;
	}

	/**
	 * Metodo para retornar o mes do objeto.
	 * 
	 * @return mes da data como um inteiro.
	 */
	public int getMes() {
		return this.mes;
	}

	/**
	 * Metodo para pegar o mes da data.
	 * 
	 * @param _mes mes da data como um inteiro.
	 */
	public void setMes(int _mes) {
		this.mes = _mes;
	}

	/**
	 * Metodo para retornar o dia do objeto.
	 * 
	 * @return dia
	 */
	public int getAno() {
		return this.ano;
	}

	/**
	 * Metodo para pegar o ano da data.
	 * 
	 * @param _ano ano da data como um inteiro.
	 */
	public void setAno(int _ano) {
		this.ano = _ano;
	}

	/**
	 * Método sobreposto para devolver os campos formatados em uma String
	 * 
	 * @return retorna String com todos os valores dos campos
	 */
	public String toString() {
		StringBuilder dados = new StringBuilder();
		dados.append(this.getDia());
		dados.append("/");
		dados.append(this.getMes());
		dados.append("/");
		dados.append(this.getAno());
		return dados.toString();
	}

	/**
	 * Metodo para verificar se o ano passado como parametro e Bissextp
	 * 
	 * @param _ano
	 * @return true se for bisexto, caso contrario, false.
	 */
	public static boolean isBissexto(int _ano) {
		if (_ano > 1582) {
			if (((_ano % 4) == 0) && ((_ano % 100) != 0)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * /** Metodo para verificar se o data passada como parametro esta em um
	 * intervalo valido.
	 * 
	 * @param _data
	 * @return true se a Data foi valida, caso contrario, false.
	 */
	public static boolean isDataValida(String _data) {
		try {
			int firstindex = _data.indexOf("/");
			int secindex = _data.indexOf("/", firstindex + 1);
			return Data.isDataValida(Integer.parseInt(_data.substring(0, firstindex)),
					Integer.parseInt(_data.substring((firstindex + 1), secindex)),
					Integer.parseInt(_data.substring((secindex + 1), (secindex + 5))));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * /** Metodo para verificar se o data passada como parametro esta em um
	 * intervalo valido.
	 * 
	 * @param _dia
	 * @param _mes
	 * @param _ano
	 * @return true se a Data foi valida, caso contrario, false.
	 */
	public static boolean isDataValida(int _dia, int _mes, int _ano) {
		if (_ano >= 1900) {
			if ((_mes <= 12) && (_dia <= 31) && (_dia > 0) && (_mes > 0)) {
				if (_mes == 2) {
					if ((Data.isBissexto(_ano)) && (_dia <= 29)) {
						return true;
					} else if (_dia <= 28) {
						return true;
					}
				} else if ((_mes == 4) || (_mes == 6) || (_mes == 9) || (_mes == 11)) {
					if (_dia < 30) {
						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * Método que incrementa um dia no objeto de chamada.
	 */
	public void incrementa() {
		try {
			this.setData(getDia() + 1, getMes(), getAno());
		} catch (Exception dia) {
			try {
				this.setData(1, getMes() + 1, getAno());
			} catch (Exception mes) {
				try {
					this.setData(1, 1, getAno() + 1);
				} catch (Exception ano) {
					new Exception("Ocorreu um erro ao incrementar essa Data!");
				}
			}
		}
	}

	/**
	 * Método que chama o metodo sobrecarregado incrementa com a quantidade de
	 * iterações passada como parametro.
	 * 
	 * @param dias quantidade de dias a ser incrementado.
	 * @return objeto de chamada com dias incrementados.
	 */
	public Data incrementa(int dias) {
		int i = 0;

		for (i = 0; i < dias; i++) {
			this.incrementa();
		}

		return this;
	}

	/**
	 * Método que chama o algoritimo de comparacao de Data.
	 * 
	 * @return inteiro retornado no metodo sobrecarregado compareTo
	 */
	public int compareTo(Data _data) {
		return Data.compareTo(this.getDia(), this.getMes(), this.getAno(), _data.getDia(), _data.getMes(),
				_data.getAno());
	}

	/**
	 * 
	 * * Método que recebe duas datas no formato string e chama o algoritimo de
	 * comparacao de Data que recebe a data em inteiros.
	 * 
	 * @param _data1
	 * @param _data2
	 * @return inteiro retornado no metodo sobrecarregado compareTo
	 */
	public static int compareTo(String _data1, String _data2) {
		int firstindex1 = _data1.indexOf("/");
		int secindex1 = _data1.indexOf("/", firstindex1 + 1);
		int firstindex2 = _data2.indexOf("/");
		int secindex2 = _data2.indexOf("/", firstindex2 + 1);
		return Data.compareTo(Integer.parseInt(_data1.substring(0, firstindex1)),
				Integer.parseInt(_data1.substring((firstindex1 + 1), secindex1)),
				Integer.parseInt(_data1.substring((secindex1 + 1), (secindex1 + 5))),
				Integer.parseInt(_data2.substring(0, firstindex2)),
				Integer.parseInt(_data2.substring((firstindex2 + 1), secindex2)),
				Integer.parseInt(_data2.substring((secindex2 + 1), (secindex2 + 5))));
	}
	
		
	public static int[] getData(String _data) {
		int firstindex = _data.indexOf("/");
		int secindex = _data.indexOf("/", firstindex + 1);
		int[] data= new int[3];
		data[0] = Integer.parseInt(_data.substring(0, firstindex));
		data[1] = Integer.parseInt(_data.substring((firstindex + 1), secindex));
		data[2] = Integer.parseInt(_data.substring((secindex + 1), (secindex + 5)));
		return data;
		
	}

	/**
	 * Método sobrecarregado que fornece um algoritmo para comparar elementos na
	 * busca.
	 * 
	 * @return inteiro, 1 se maior, -1 se menor ou 0 se igual
	 */
	
//	datainicio databanco ok != 1
//	datafim databanco falsp != -1
	public static int compareTo(int _dia1, int _mes1, int _ano1, int _dia2, int _mes2, int _ano2) {
		if (_ano1 > _ano2) {
			return -1;
		} else if (_ano1 < _ano2) {
			return 1;
		} else {
			if (_mes1 > _mes2) {
				return -1;
			} else if (_mes1 < _mes2) {
				return 1;
			} else {
				if (_dia1 > _dia2) {
					return -1;
				} else if (_dia1 < _dia2) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}
