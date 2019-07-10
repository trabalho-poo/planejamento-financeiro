package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import implementacoes.BancoDeDados;
import implementacoes.Data;
import implementacoes.Sexo;
import implementacoes.TipoDespesa;
import implementacoes.TipoMovimentacao;
import implementacoes.TipoReceita;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JTable;

public class Relatorio extends JFrame {

	private JPanel contentPane;
	private JTextField dataInicio;
	private JTextField dataFim;
	JLabel lblDataInicio;
	JLabel lblDataFim;
	JComboBox tipoMovimentacao;
	JComboBox tipoEspecifico;
	private JTable getMatrizDadosGeral;
	private JTable getMatrizDadosIntervalo;
	JScrollPane paneMatrizDadosGeral;
	JScrollPane paneMatrizDadosIntervalo;
	Object[][] dados;
	JCheckBox chckbxEspecificarIntervaloDe;
	ChartPanel grafico;
	DefaultPieDataset pieDataset;

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
	public Relatorio(int _idUsuario, BancoDeDados bd) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colunas = { "Descricao", "Data", "Tipo", "Classificacao", "Valor" };

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

		JButton btnRelatorio = new JButton("Filtrar");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafico.setVisible(true);
				if (chckbxEspecificarIntervaloDe.isSelected()) {
					if (dataInicio.getText().equals("") || dataFim.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane,
								"Alguma data nao foi inserida corretamente. Certifique-se de preencher todos os campos.",
								"Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
					} else if (!Data.isDataValida(dataInicio.getText()) || !Data.isDataValida(dataFim.getText())) {
						JOptionPane.showMessageDialog(contentPane,
								"Alguma data nao foi inserida corretamente. Certifique-se de inserir a data no formato: DD/MM/YYYY.",
								"Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
					} else if(Data.compareTo(dataInicio.getText(), dataFim.getText()) == -1){
						JOptionPane.showMessageDialog(contentPane,
								"Certifique-se de colocar a data de início como sendo menor que a data de fim do intervalo.",
								"Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
					}else {
						//FAZER METODO PARA DATAS
						pieDataset.clear();
						double porcentagemReceita = bd.getPorcentagemReceitaIntervalo(_idUsuario,Data.getData(dataInicio.getText()),Data.getData(dataFim.getText()));
						double porcentagemDespesa = (100 - porcentagemReceita);
						pieDataset.setValue("Despesa", new Double(porcentagemDespesa));
						pieDataset.setValue("Receita", new Double(porcentagemReceita));
						if (!TipoMovimentacao.TODOS.equals(tipoMovimentacao.getSelectedItem())) {
							dados = bd.getRelatorioIntervalo((TipoMovimentacao) tipoMovimentacao.getSelectedItem(),
									tipoEspecifico.getSelectedItem().toString(), _idUsuario, Data.getData(dataInicio.getText()),Data.getData(dataFim.getText()),bd);
						} else {
							
							dados = bd.getRelatorioIntervalo((TipoMovimentacao) tipoMovimentacao.getSelectedItem(), "todos",_idUsuario, Data.getData(dataInicio.getText()),Data.getData(dataFim.getText()),bd);
						}
						//getRelatorioIntervalo(TipoMovimentacao _tipoMovimentacao, String _tipoEspecifico, int _idUsuario,int[] dataInicio, int[] dataFim, BancoDeDados bd)
					}
					paneMatrizDadosGeral.setVisible(false);
					getMatrizDadosIntervalo = new JTable(dados, colunas);
					paneMatrizDadosIntervalo.setViewportView(getMatrizDadosIntervalo);
					paneMatrizDadosIntervalo.setVisible(true);
				} else {
					pieDataset.clear();
					pieDataset.setValue("Despesa", new Double(bd.getPorcentagemDespesa(_idUsuario)));
					pieDataset.setValue("Receita", new Double(bd.getPorcentagemReceita(_idUsuario)));
					if (!TipoMovimentacao.TODOS.equals(tipoMovimentacao.getSelectedItem())) {
						dados = bd.getRelatorio((TipoMovimentacao) tipoMovimentacao.getSelectedItem(),
								tipoEspecifico.getSelectedItem().toString(), _idUsuario);
					} else {
						
						dados = bd.getRelatorio((TipoMovimentacao) tipoMovimentacao.getSelectedItem(), "todos",
								_idUsuario);
					}
					paneMatrizDadosIntervalo.setVisible(false);
					getMatrizDadosGeral = new JTable(dados, colunas);
					paneMatrizDadosGeral.setViewportView(getMatrizDadosGeral);
					paneMatrizDadosGeral.setVisible(true);
				}
			}
		});
		btnRelatorio.setBounds(390, 100, 134, 30);
		contentPane.add(btnRelatorio);

		chckbxEspecificarIntervaloDe = new JCheckBox("Intervalo de datas");
		chckbxEspecificarIntervaloDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxEspecificarIntervaloDe.isSelected()) {
					lblDataInicio.setVisible(true);
					lblDataFim.setVisible(true);
					dataFim.setVisible(true);
					dataInicio.setVisible(true);
					btnRelatorio.setBounds(438, 139, 134, 30);
				} else {
					lblDataInicio.setVisible(false);
					lblDataFim.setVisible(false);
					dataFim.setVisible(false);
					dataInicio.setVisible(false);
					btnRelatorio.setBounds(390, 100, 134, 30);
				}
			}
		});

		lblDataInicio = new JLabel("Data In\u00EDcio:");
		lblDataInicio.setVisible(false);
		lblDataInicio.setBounds(215, 100, 74, 19);
		contentPane.add(lblDataInicio);
		lblDataInicio.setVisible(false);
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblDataFim = new JLabel("Data Fim:");
		lblDataFim.setVisible(false);
		lblDataFim.setBounds(422, 101, 64, 19);
		contentPane.add(lblDataFim);
		lblDataFim.setVisible(false);
		lblDataFim.setFont(new Font("Tahoma", Font.PLAIN, 15));

		dataFim = new JTextField();
		dataFim.setBounds(513, 100, 116, 22);
		contentPane.add(dataFim);
		dataFim.setVisible(false);
		dataFim.setColumns(10);

		dataInicio = new JTextField();
		dataInicio.setBounds(294, 100, 116, 22);
		contentPane.add(dataInicio);
		dataInicio.setVisible(false);
		dataInicio.setColumns(10);

		chckbxEspecificarIntervaloDe.setBackground(Color.WHITE);
		chckbxEspecificarIntervaloDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxEspecificarIntervaloDe.setBounds(439, 62, 149, 22);
		contentPane.add(chckbxEspecificarIntervaloDe);

		// Botão utilizado para voltar
		Button btnVoltar = new Button("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home(_idUsuario, bd).setVisible(true);
			}
		});
		btnVoltar.setForeground(Color.RED);
		btnVoltar.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(743, 10, 57, 30);
		contentPane.add(btnVoltar);

		JLabel lblSelecione = new JLabel("Selecione:");
		lblSelecione.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelecione.setBounds(215, 58, 69, 30);
		contentPane.add(lblSelecione);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setVisible(false);
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipo.setBounds(439, 58, 47, 30);
		contentPane.add(lblTipo);

		tipoEspecifico = new JComboBox();
		tipoEspecifico.setVisible(false);
		tipoEspecifico.setBounds(486, 63, 120, 22);
		contentPane.add(tipoEspecifico);

		tipoMovimentacao = new JComboBox();
		tipoMovimentacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tipoMovimentacao.getSelectedItem() == TipoMovimentacao.RECEITA) {
					tipoEspecifico.setVisible(true);
					lblTipo.setVisible(true);
					chckbxEspecificarIntervaloDe.setBounds(641, 62, 149, 22);
					tipoEspecifico.setModel(new DefaultComboBoxModel(new String[] { "TODOS", "SALARIO", "OUTROS" }));
				} else if (tipoMovimentacao.getSelectedItem() == TipoMovimentacao.DESPESA) {
					tipoEspecifico.setVisible(true);
					lblTipo.setVisible(true);
					chckbxEspecificarIntervaloDe.setBounds(641, 62, 149, 22);
					tipoEspecifico.setModel(new DefaultComboBoxModel(new String[] { "TODOS", "ALUGUEL", "TELEFONE",
							"INTERNET", "ACADEMIA", "CLUBE", "SUPERMERCADO", "LUZ", "AGUA", "OUTROS" }));
				} else {
					tipoEspecifico.setVisible(false);
					lblTipo.setVisible(false);
					chckbxEspecificarIntervaloDe.setBounds(439, 62, 149, 22);

				}
			}
		});
		tipoMovimentacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipoMovimentacao.setModel(new DefaultComboBoxModel(TipoMovimentacao.values()));
		tipoMovimentacao.setBounds(294, 62, 120, 22);
		contentPane.add(tipoMovimentacao);

		paneMatrizDadosGeral = new JScrollPane();
		paneMatrizDadosGeral.setVisible(false);
		paneMatrizDadosGeral.setBounds(200, 169, 378, 360);
		contentPane.add(paneMatrizDadosGeral);
//		getMatrizDadosGeral = new JTable(dados, colunas);
//		paneMatrizDadosGeral.setViewportView(getMatrizDadosGeral);
//		getMatrizDadosGeral.setBounds(200, 169, 378, 360);
//		contentPane.add(getMatrizDadosGeral);
		
		paneMatrizDadosIntervalo = new JScrollPane();
		paneMatrizDadosIntervalo.setVisible(false);
		paneMatrizDadosIntervalo.setBounds(200, 169, 378, 360);
		contentPane.add(paneMatrizDadosIntervalo);
//		getMatrizDadosIntervalo = new JTable();
//		getMatrizDadosIntervalo.setVisible(false);
//		getMatrizDadosIntervalo.setBounds(200, 169, 378, 360);
//		contentPane.add(getMatrizDadosIntervalo);

		pieDataset = new DefaultPieDataset();
//		pieDataset.clear();
//		pieDataset.setValue("Despesa", new Double(bd.getPorcentagemDespesa(_idUsuario)));
//		pieDataset.setValue("Receita", new Double(bd.getPorcentagemReceita(_idUsuario)));
		JFreeChart chart = ChartFactory.createPieChart("", pieDataset, true, true, true);

		grafico = new ChartPanel(chart);
		grafico.setVisible(false);
		grafico.setBackground(Color.WHITE);
		grafico.setBounds(580, 216, 240, 235);
		contentPane.add(grafico);
		grafico.setDisplayToolTips(true);
		grafico.setMouseWheelEnabled(true);
		grafico.setLayout(null);
	}
}
