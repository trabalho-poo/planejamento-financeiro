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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import implementacoes.BancoDeDados;
import implementacoes.Sexo;
import implementacoes.TipoDespesa;
import implementacoes.TipoReceita;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Panel;

public class Relatorio extends JFrame {

	private JPanel contentPane;
	private JTextField dataInicio;
	private JTextField dataFim;

	/**
	 * Create the frame.
	 */
	public Relatorio(int _idUsuario,BancoDeDados bd) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colunas = { "Tipo", "Valor" };
		
		Panel panelData = new Panel();
		panelData.setVisible(false);
		
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
		panelData.setBounds(231, 103, 542, 30);
		contentPane.add(panelData);
		panelData.setLayout(null);
		
		JLabel lblDataInicio = new JLabel("Data In\u00EDcio:");
		lblDataInicio.setBounds(50, 6, 74, 19);
		panelData.add(lblDataInicio);
		lblDataInicio.setVisible(false);
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		dataInicio = new JTextField();
		dataInicio.setBounds(136, 5, 116, 22);
		panelData.add(dataInicio);
		dataInicio.setVisible(false);
		dataInicio.setColumns(10);
		
		JLabel lblDataFim = new JLabel("Data Fim:");
		lblDataFim.setBounds(313, 6, 64, 19);
		panelData.add(lblDataFim);
		lblDataFim.setVisible(false);
		lblDataFim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		dataFim = new JTextField();
		dataFim.setBounds(389, 5, 116, 22);
		panelData.add(dataFim);
		dataFim.setVisible(false);
		dataFim.setColumns(10);
		
		JButton btnRelatorio = new JButton("Filtrar");
		btnRelatorio.setBounds(390, 100, 134, 30);
		contentPane.add(btnRelatorio);
		
		JCheckBox chckbxEspecificarIntervaloDe = new JCheckBox("Intervalo de datas");
		chckbxEspecificarIntervaloDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxEspecificarIntervaloDe.isSelected()) {
					panelData.setVisible(true);
					btnRelatorio.setBounds(438, 139, 134, 30);
				}else{
					panelData.setVisible(false);
					btnRelatorio.setVisible(true);
					btnRelatorio.setBounds(390, 139, 134, 30);
				}
			}
		});
		
		
		chckbxEspecificarIntervaloDe.setBackground(Color.WHITE);
		chckbxEspecificarIntervaloDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxEspecificarIntervaloDe.setBounds(439, 62, 149, 22);
		contentPane.add(chckbxEspecificarIntervaloDe);
		
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
		
	
		
		JComboBox tipoEspecifico = new JComboBox();
		tipoEspecifico.setVisible(false);
		tipoEspecifico.setBounds(486, 63, 120, 22);
		contentPane.add(tipoEspecifico);
		
		JComboBox tipoMovimentacao = new JComboBox();
		tipoMovimentacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(((String) tipoMovimentacao.getSelectedItem()).equalsIgnoreCase("Receita")) {
					tipoEspecifico.setVisible(true);
					lblTipo.setVisible(true);
					chckbxEspecificarIntervaloDe.setBounds(641, 62, 149, 22);
					tipoEspecifico.setModel(new DefaultComboBoxModel(TipoReceita.values()));
				}else if(((String) tipoMovimentacao.getSelectedItem()).equalsIgnoreCase("Despesa")) {
					tipoEspecifico.setVisible(true);
					lblTipo.setVisible(true);
					chckbxEspecificarIntervaloDe.setBounds(641, 62, 149, 22);
					tipoEspecifico.setModel(new DefaultComboBoxModel(TipoDespesa.values()));
				}else{
					tipoEspecifico.setVisible(false);
					lblTipo.setVisible(false);
					chckbxEspecificarIntervaloDe.setBounds(439, 62, 149, 22);
					
				}
			}
		});
		tipoMovimentacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipoMovimentacao.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Despesa", "Receita" }));
		tipoMovimentacao.setBounds(294, 62, 120, 22);
		contentPane.add(tipoMovimentacao);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(194, 175, 628, 378);
		contentPane.add(panel_2);
	}
}
