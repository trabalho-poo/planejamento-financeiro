package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import implementacoes.BancoDeDados;
import implementacoes.Data;
import implementacoes.Despesa;
import implementacoes.Receita;
import implementacoes.Sexo;
import implementacoes.TipoDespesa;
import implementacoes.TipoReceita;
import implementacoes.Usuario;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Choice;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Scrollbar;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class NovaMovimentacao extends JFrame {

	private JPanel contentPane;
	private JTextField descricao;
	private JTextField valor;
	private JTextField data;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// NovaMovimentacao frame = new NovaMovimentacao();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public NovaMovimentacao(int _idUsuario, BancoDeDados bd) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
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
			}
		});

		panel.add(button);

		JLabel lblFundo = new JLabel("Nova Movimenta\u00E7\u00E3o");
		lblFundo.setIcon(new ImageIcon(Home.class.getResource("/img/essa - Copia.jpg")));
		lblFundo.setBounds(-6, 0, 212, 553);
		panel.add(lblFundo);

		JPanel Movimentacao = new JPanel();
		Movimentacao.setBackground(Color.WHITE);
		Movimentacao.setBounds(187, 0, 645, 553);
		contentPane.add(Movimentacao);
		Movimentacao.setLayout(null);

		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(61, 152, 116, 16);
		Movimentacao.add(lblNewLabel);

		JLabel lblCadastrarMovimentao = new JLabel("Cadastrar Movimenta\u00E7\u00E3o");
		lblCadastrarMovimentao.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarMovimentao.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCadastrarMovimentao.setEnabled(false);
		lblCadastrarMovimentao.setBounds(157, 13, 280, 75);
		Movimentacao.add(lblCadastrarMovimentao);

		descricao = new JTextField();
		descricao.setBounds(61, 181, 326, 30);
		Movimentacao.add(descricao);
		descricao.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValor.setBounds(61, 261, 116, 16);
		Movimentacao.add(lblValor);

		valor = new JTextField();
		valor.setBounds(61, 290, 116, 25);
		Movimentacao.add(valor);
		valor.setColumns(10);

		JLabel lblTpo = new JLabel("Tipo:");
		lblTpo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTpo.setBounds(436, 261, 116, 16);
		Movimentacao.add(lblTpo);

		JRadioButton rdbtnReceita = new JRadioButton("Receita");
		buttonGroup.add(rdbtnReceita);
		rdbtnReceita.setBounds(214, 290, 81, 25);
		Movimentacao.add(rdbtnReceita);
		
		JRadioButton rdbtnDespesa = new JRadioButton("Despesa");
		buttonGroup.add(rdbtnDespesa);
		rdbtnDespesa.setBounds(300, 290, 87, 25);
		Movimentacao.add(rdbtnDespesa);
		
//		JCheckBox chckb_Receita = new JCheckBox("Receita");
//		chckb_Receita.setToolTipText("");
//		chckb_Receita.setBounds(215, 290, 81, 25);
//		Movimentacao.add(chckb_Receita);
//
//		JCheckBox chckb_Despesa = new JCheckBox("Despesa");
//		chckb_Despesa.setBounds(300, 290, 87, 25);
//		Movimentacao.add(chckb_Despesa);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategoria.setBounds(254, 261, 116, 16);
		Movimentacao.add(lblCategoria);

		JComboBox<?> tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] { "" }));
		tipo.setSelectedIndex(0);

		// o que vai aparecer no comboBox

		// caso seja selecionado receita
		rdbtnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDespesa.isSelected() == false) {
					if (rdbtnReceita.isSelected() == true) {
						tipo.setModel(new DefaultComboBoxModel(new String[] { "SALARIO", "OUTROS" }));
					} else {
						tipo.setModel(new DefaultComboBoxModel(new String[] { "" }));
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Você já seleconou DESPESA", "Erro",
							JOptionPane.ERROR_MESSAGE);
					rdbtnReceita.setSelected(false);
				}
			}
		});
		// caso seja selecionado DESPESA
		rdbtnDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnReceita.isSelected() == false) {
					if (rdbtnDespesa.isSelected() == true) {
						tipo.setModel(new DefaultComboBoxModel(new String[] { "ACADEMIA", "AGUA", "ALUGUEL", "CLUBE",
								"INTERNET", "TELEFONE", "LUZ", "SUPERMERCADO", "OUTROS" }));
					} else {
						tipo.setModel(new DefaultComboBoxModel(new String[] { "" }));
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Você já seleconou RECEITA", "Erro",
							JOptionPane.ERROR_MESSAGE);
					rdbtnDespesa.setSelected(false);
				}

			}
		});

		tipo.setBounds(436, 291, 163, 22);
		Movimentacao.add(tipo);

		data = new JTextField();
		data.setColumns(10);
		data.setBounds(436, 181, 163, 30);
		Movimentacao.add(data);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblData.setBounds(436, 154, 116, 16);
		Movimentacao.add(lblData);

		Button btnSalvar = new Button("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				BancoDeDados bd = new BancoDeDados();
				try {
//					bd.conectar();
					if (bd.isConectado()) {
						if (rdbtnReceita.isSelected()) {
							// ACADEMIA", "AGUA", "ALUGUEL", "CLUBE
							switch (((String) tipo.getSelectedItem()).toLowerCase()) {
							case "salario":
								bd.inserirMovimentacaoReceita(new Receita(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoReceita.SALARIO), _idUsuario);
								break;
							case "outros":
								bd.inserirMovimentacaoReceita(new Receita(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoReceita.OUTROS), _idUsuario);
								break;
							}

						} else {
							switch (((String) tipo.getSelectedItem()).toLowerCase()) {
							// ALUGUEL, TELEFONE, INTERNET, ACADEMIA, CLUBE, SUPERMERCADO, LUZ, AGUA
							case "aluguel":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.ALUGUEL), _idUsuario);
								break;
							case "telefone":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.TELEFONE), _idUsuario);
								break;
							case "internet":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.INTERNET), _idUsuario);
								break;
							case "academia":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.ACADEMIA), _idUsuario);
								break;
							case "clube":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.CLUBE), _idUsuario);
								break;
							case "supermercado":
								bd.inserirMovimentacaoDespesa(
										new Despesa(new Data(data.getText()), descricao.getText(),
												Double.parseDouble(valor.getText()), TipoDespesa.SUPERMERCADO),
										_idUsuario);
								break;
							case "luz":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.LUZ), _idUsuario);
								break;
							case "agua":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.AGUA), _idUsuario);
								break;
							case "outros":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(data.getText()), descricao.getText(),
										Double.parseDouble(valor.getText()), TipoDespesa.OUTROS), _idUsuario);
								break;
							}
						}

						JOptionPane.showMessageDialog(contentPane, "Movimentação cadastrada com sucesso!", "",
								JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						new Home(_idUsuario, bd).setVisible(true);
					} else {
						Component contentPane = null;
						JOptionPane.showMessageDialog(contentPane, "Não foi possivel conectar ao banco de dados",
								"Erro ao cadastrar movimentaçao.", JOptionPane.ERROR_MESSAGE);

					}
				} catch (Exception ex) {
					System.out.println("Erro: " + ex.getMessage());
					// e.printStackTrace();
				}
			}
		});
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnSalvar.setBackground(new Color(47, 79, 79));
		btnSalvar.setBounds(231, 426, 177, 33);
		Movimentacao.add(btnSalvar);

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
		btnVoltar.setBounds(578, 13, 57, 30);
		Movimentacao.add(btnVoltar);
	}
}
