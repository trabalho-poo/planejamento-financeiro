package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import implementacoes.BancoDeDados;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.CardLayout;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	int xx,xy;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					Home frame = new Home();
	//					frame.setUndecorated(true);
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}


	// going to borrow code from a gist to move frame.


	/**
	 * Create the frame.
	 */
	public Home(int _idUsuario, BancoDeDados bd) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 188, 553);
		contentPane.add(panel);
		panel.setLayout(null);

		Button button_3 = new Button("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		button_3.setBackground(new Color(47, 70, 79));
		button_3.setBounds(-16, 458, 222, 40);
		panel.add(button_3);

		Button button_1 = new Button("Hist\u00F3rico");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		button_1.setBackground(new Color(49, 113, 37));
		button_1.setBounds(-16, 211, 222, 40);
		panel.add(button_1);

		Button button_2 = new Button("Relatorios");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		button_2.setBackground(new Color(79, 79, 79));
		button_2.setBounds(-16, 318, 222, 40);
		panel.add(button_2);

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		Button button = new Button("Nova Movimenta\u00E7\u00E3o");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 15));
		button.setBackground(new Color(47, 79, 79));
		button.setBounds(-16, 112, 222, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new NovaMovimentacao(_idUsuario, bd).setVisible(true);

			}
		});

		panel.add(button);

		JLabel lblFundo = new JLabel("Nova Movimenta\u00E7\u00E3o");
		lblFundo.setIcon(new ImageIcon(Home.class.getResource("/img/essa - Copia.jpg")));
		lblFundo.setBounds(0, 0, 188, 553);
		panel.add(lblFundo);


		
//		BancoDeDados bd = new BancoDeDados();
//		try {
//			bd.conectar();
//			if(bd.isConectado()) {
//				
//				pieDataset.setValue("Despesa", new Double(bd.getPorcentagemDespesa()));
//				pieDataset.setValue("Receita", new Double(bd.getPorcentagemReceita()));
//			}
//		}catch(Exception e) {
//			
//		}
		
		// Primeiro Grafico
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Despesa", new Double(bd.getPorcentagemDespesa()));
		pieDataset.setValue("Receita", new Double(bd.getPorcentagemReceita()));
//		pieDataset.setValue("Despesa", new Integer(bd.getPorcentagemDespesa()));
//		pieDataset.setValue("Receita", new Integer(20));
		JFreeChart chart = ChartFactory.createPieChart("Tipo de movimentação", pieDataset, true, true, true);
		
		
//		JFreeChart chart2 = ChartFactory.createPieChart("Tipo das despesas", pie2, true, true, true);
		//		P.setForegroundAlpha(TOP_ALIGNMENT);
		ChartPanel grafico = new ChartPanel(chart);
		grafico.setBounds(187, 0, 331, 277);
		contentPane.add(grafico);
		grafico.setDisplayToolTips(true);
		grafico.setMouseWheelEnabled(true);
		grafico.setLayout(null);
		
		// Segundo Grafico
		DefaultPieDataset pie2 = new DefaultPieDataset();
		pie2.setValue("Aluguel", new Double(bd.getPorcentagemTipo("DESPESA","ALUGUEL")));
		pie2.setValue("Telefone", new Double(bd.getPorcentagemTipo("DESPESA","TELEFONE")));
		pie2.setValue("Internet", new Double(bd.getPorcentagemTipo("DESPESA","INTERNET")));
		pie2.setValue("Academia", new Double(bd.getPorcentagemTipo("DESPESA","ACADEMIA")));
		pie2.setValue("Clube", new Double(bd.getPorcentagemTipo("DESPESA","CLUBE")));
		pie2.setValue("Supermercado", new Double(bd.getPorcentagemTipo("DESPESA","SUPERMERCADO")));
		pie2.setValue("Luz", new Double(bd.getPorcentagemTipo("DESPESA","LUZ")));
		pie2.setValue("Água", new Double(bd.getPorcentagemTipo("DESPESA","AGUA")));
		pie2.setValue("Outros", new Double(bd.getPorcentagemTipo("DESPESA","OUTROS")));
		JFreeChart chart2 = ChartFactory.createPieChart("Tipo das despesas", pie2, true, true, true);
		
		ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setLayout(null);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setDisplayToolTips(true);
		chartPanel.setBounds(521, 0, 311, 277);
		contentPane.add(chartPanel);
		
		
		// Grafico 3
		DefaultPieDataset pie3 = new DefaultPieDataset();
		pieDataset.setValue("TESTE", new Double(bd.getPorcentagemDespesa()));
		pieDataset.setValue("TESTE 2", new Double(bd.getPorcentagemReceita()));
//		pieDataset.setValue("Despesa", new Integer(bd.getPorcentagemDespesa()));
//		pieDataset.setValue("Receita", new Integer(20));
		JFreeChart chart3 = ChartFactory.createPieChart("Tipo de movimentação", pie3, true, true, true);
		
		ChartPanel grafico3 = new ChartPanel((JFreeChart) null);
		grafico3.setLayout(null);
		grafico3.setMouseWheelEnabled(true);
		grafico3.setDisplayToolTips(true);
		grafico3.setBounds(187, 276, 331, 277);
		contentPane.add(grafico3);
		ChartPanel grafico_3 = new ChartPanel(chart3);
		grafico_3.setBounds(0, 0, 338, 277);
		grafico3.add(grafico_3);
		grafico_3.setDisplayToolTips(true);
		grafico_3.setMouseWheelEnabled(true);
		grafico_3.setLayout(null);
		
		
		// Grafico 4
		ChartPanel chartPanel_4 = new ChartPanel((JFreeChart) null);
		chartPanel_4.setLayout(null);
		chartPanel_4.setMouseWheelEnabled(true);
		chartPanel_4.setDisplayToolTips(true);
		chartPanel_4.setBounds(516, 276, 316, 277);
		contentPane.add(chartPanel_2);
		ChartPanel grafico4 = new ChartPanel(chart2);
		grafico4.setBounds(-27, 0, 338, 277);
		chartPanel.add(grafico4);
		grafico4.setDisplayToolTips(true);
		grafico4.setMouseWheelEnabled(true);
		grafico4.setLayout(null);
		//		P.setForegroundAlpha(TOP_ALIGNMENT);
		
		ChartPanel grafico2 = new ChartPanel(chart2);
		grafico2.setBounds(521, 276, 311, 277);
		contentPane.add(grafico2);
		grafico2.setDisplayToolTips(true);
		grafico2.setMouseWheelEnabled(true);
		grafico2.setLayout(null);

		//		JButton Chart = new JButton("BOTAOGRAFICO");
		//		Chart.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				DefaultPieDataset pieDataset = new DefaultPieDataset();
		//				pieDataset.setValue("One", new Integer(10));
		//				pieDataset.setValue("Two", new Integer(20));
		//				pieDataset.setValue("Three", new Integer(30));
		//				JFreeChart chart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, true);
		//				PiePlot P = (PiePlot)chart.getPlot();
		//				P.setForegroundAlpha(TOP_ALIGNMENT);
		//				ChartFrame frame = new ChartFrame("Pie Chart",chart);
		//				frame.setVisible(true);
		//				frame.setSize(450,500);
		//			}
		//		});
		//		grafico.add(Chart);




		//		
		//		DefaultPieDataset pieDataset = new DefaultPieDataset();
		//		pieDataset.setValue("One", new Integer(10));
		//		pieDataset.setValue("Two", new Integer(20));
		//		pieDataset.setValue("Three", new Integer(30));
		//		JFreeChart chart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, true);
		//		PiePlot P = (PiePlot)chart.getPlot();
		//		
		//		ChartFrame frame = new ChartFrame("Pie Chart",chart);
		//		frame.setVisible(true);
		//		frame.setSize(450,500);

	}
}
