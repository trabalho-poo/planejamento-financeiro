package implementacoes;

public class Uso {

	public static void main(String[] args) {
	
		BancoDeDados bd = new BancoDeDados();
		try {
			
			bd.conectar();
			if(bd.isConectado()) {
				System.out.println("Conectei ao banco de dados");
			}else {
				System.out.println("Não foi possível conectar ao banco de dados");
			}
			Usuario _usuario = new Usuario("N", Sexo.FEMININO, new Data("1/1/1995"), "o", "o", "o@o",
			"o");
			bd.inserirContato(_usuario);
		}catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

}
