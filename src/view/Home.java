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
import implementacoes.TipoMovimentacao;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
//	private JTextField textField;
//	private JTextField textField_1;
//	private JPasswordField passwordField;
//	private JPasswordField passwordField_1;

	int xx, xy;
	private JTextField saldo;
	private JTable tableReceita;
	private JTable tableDespesa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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

		String[] colunas = { "Tipo", "Valor" };

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
				setVisible(false);
				new Historico(_idUsuario, bd).setVisible(true);
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
				setVisible(false);
				new Relatorio(_idUsuario, bd).setVisible(true);
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

		// Primeiro Grafico
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		
		pieDataset.setValue("Despesa", new Double(bd.getPorcentagemDespesa(_idUsuario)));
		pieDataset.setValue("Receita", new Double(bd.getPorcentagemReceita(_idUsuario)));
		JFreeChart chart = ChartFactory.createPieChart("Tipo de movimentação", pieDataset, true, true, true);

		ChartPanel grafico = new ChartPanel(chart);
		grafico.setBackground(Color.WHITE);
		grafico.setBounds(375, 13, 266, 248);
		contentPane.add(grafico);
		grafico.setDisplayToolTips(true);
		grafico.setMouseWheelEnabled(true);
		grafico.setLayout(null);

		saldo = new JTextField();
		saldo.setEditable(false);
		saldo.setText("R$ " + bd.getValorTotal(_idUsuario));
		saldo.setBounds(653, 171, 116, 22);
		contentPane.add(saldo);
		saldo.setColumns(10);
		
		Date date = new Date();
		int[] homeInicioDataInicioVetor = new int[3];
		int[] homeInicioDataFimVetor = { date.getDate(), date.getMonth() + 1,
				date.getYear() + 1900 };
		homeInicioDataInicioVetor[0] = date.getDate();
		if (date.getMonth() == 0) {
			homeInicioDataInicioVetor[1] = 12;
			homeInicioDataInicioVetor[2] = date.getYear() + 1900 - 1;
		} else {
			homeInicioDataInicioVetor[1] = date.getMonth();
			homeInicioDataInicioVetor[2] = date.getYear() + 1900;
		}

		tableReceita = new JTable(bd.getMatrizDadosIntervalo(TipoMovimentacao.RECEITA, "TODOS", _idUsuario, homeInicioDataInicioVetor, homeInicioDataFimVetor, bd), colunas);
		tableReceita.setBackground(new Color(255, 255, 255));
		tableReceita.setBounds(216, 282, 266, 258);
		JScrollPane paneTableReceita = new JScrollPane(tableReceita);
		paneTableReceita.setLocation(216, 318);
		paneTableReceita.setSize(266, 230);
		contentPane.add(paneTableReceita);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(691, 120, 56, 38);
		contentPane.add(lblSaldo);
		
		tableDespesa = new JTable(bd.getMatrizDadosIntervalo(TipoMovimentacao.DESPESA, "TODOS", _idUsuario, homeInicioDataInicioVetor, homeInicioDataFimVetor, bd), colunas);
		tableDespesa.setBackground(Color.WHITE);
		tableDespesa.setBounds(542, 282, 266, 258);
		JScrollPane paneTableDespesa = new JScrollPane(tableDespesa);
		paneTableDespesa.setLocation(535, 318);
		paneTableDespesa.setSize(266, 230);
		contentPane.add(paneTableDespesa);

		JLabel lblListaDeMovimentacoes = new JLabel("Lista de Receitas do M\u00EAs");
		lblListaDeMovimentacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeMovimentacoes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDeMovimentacoes.setBounds(216, 274, 266, 31);
		contentPane.add(lblListaDeMovimentacoes);

		JLabel lblListaDeMovimentacoes_1 = new JLabel("Lista de Despesas do M\u00EAs");
		lblListaDeMovimentacoes_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeMovimentacoes_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDeMovimentacoes_1.setBounds(535, 274, 266, 31);
		contentPane.add(lblListaDeMovimentacoes_1);

	}
}
