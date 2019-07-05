package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sun.javafx.event.EventQueue;

public class Tesr extends JFrame {
	
    //variaveis para uso da JTable 
   private JTable table;
   private final String colunas[]={"Nome:","Idade:","Sexo:"};
   private final String dados[][]={
           {"Jack","19","Masculino"},
           {"Eddie","56","Masculino"},
           {"Gina","34","Feminino"},
           {"Klaus","18","Masculino"},
           {"Erika","20","Feminino"},
           {"Roberto","29","Masculino"},
           {"Maria","30","Feminino"}};
    
       /*Construtor da classe ,
         antes de executar o metodo main(),
         irá construir o JFrame e a JTable*
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Tesr frame = new Tesr();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Tesr() {
		 setLayout(new FlowLayout());//tipo de layout
	        setSize(new Dimension(600, 200));//tamanho do Formulario
	        setLocationRelativeTo(null);//centralizado
	        setTitle("Exemplo JTable");//titulo
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setando a ação padrão de fechamento do Formulário,
	                                                               // neste caso  irá fechar o programa
	         
	                //instanciando a JTable
	        table=new JTable(dados,colunas);
	        table.setPreferredScrollableViewportSize(new Dimension(500,100));//barra de rolagem
	        table.setFillsViewportHeight(true);
	         
	                //adicionando a tabela em uma barra de rolagem, ficará envolta , pela mesma 
	        JScrollPane scrollPane=new JScrollPane(table);
	                 
	                //adicionando ao JFrame "Formulário" a JTable "Tabela" 
	        add(scrollPane);
	    }
	     
	    
	     
	}


