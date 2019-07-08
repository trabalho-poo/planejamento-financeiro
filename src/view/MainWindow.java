package view;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import implementacoes.BancoDeDados;
import implementacoes.Data;
import implementacoes.Despesa;
import implementacoes.Receita;
import implementacoes.Sexo;
import implementacoes.TipoDespesa;
import implementacoes.TipoReceita;
import implementacoes.Usuario;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginEmailTexto;
	private JPasswordField loginSenhaTexto;
	private JTextField cadastroUsuarioNomeTexto;
	private JTextField cadastroUsuarioDataNascimentoTexto;
	private JTextField cadastroUsuarioEmailTexto;
	private JPasswordField cadastroUsuarioSenhaTexto;
	private BancoDeDados bd = new BancoDeDados();
	private int _idUsuario;
	JButton painelVoltarBtn;
	private JTextField homeInicioSaldoText;
	private final ButtonGroup novaMovimentacaoCategoriaButtonGroup = new ButtonGroup();

	// paineis
	JPanel login;
	JPanel cadastroUsuario;
	JPanel home;
	JPanel homeInicio;
	JPanel homeLogado;
	JPanel homeNovaMovimentacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainWindow() throws Exception {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		setBounds(100, 100, 1600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		setBounds(100, 100, 1600, 600);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(layeredPane, "name_684109986604577");
		layeredPane.setLayout(new CardLayout(0, 0));

		// ==================================================== LOGIN
		login = new JPanel();
		layeredPane.add(login, "name_685018489940878");
		login.setBackground(Color.WHITE);
		login.setVisible(true);
		login.setLayout(null);

		cadastroUsuario = new JPanel();
		cadastroUsuario.setBackground(Color.WHITE);
		layeredPane.add(cadastroUsuario, "name_685213681362476");
		cadastroUsuario.setLayout(null);

		JLabel cadastroUsuarioImagemLabel = new JLabel("");
		cadastroUsuarioImagemLabel.setBounds(0, 0, 448, 590);
		cadastroUsuarioImagemLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/essa - Copia.jpg")));
		cadastroUsuario.add(cadastroUsuarioImagemLabel);

		JButton cadastroUsuarioVoltarBtn = new JButton("Voltar");
		cadastroUsuarioVoltarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroUsuario.setVisible(false);
				login.setVisible(true);
			}
		});
		cadastroUsuarioVoltarBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioVoltarBtn.setBounds(1369, 13, 97, 25);
		cadastroUsuario.add(cadastroUsuarioVoltarBtn);

		JButton cadastroUsuarioFecharBtn = new JButton("Fechar");
		cadastroUsuarioFecharBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cadastroUsuarioFecharBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioFecharBtn.setBounds(1481, 13, 97, 25);
		cadastroUsuario.add(cadastroUsuarioFecharBtn);

		JLabel cadastroUsuarioTituloLabel = new JLabel("CADASTRO DE USU\u00C1RIO");
		cadastroUsuarioTituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastroUsuarioTituloLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		cadastroUsuarioTituloLabel.setEnabled(false);
		cadastroUsuarioTituloLabel.setBounds(858, 29, 317, 75);
		cadastroUsuario.add(cadastroUsuarioTituloLabel);

		JLabel cadastroUsuarioDadosPessoaisLabel = new JLabel("Dados Pessoais");
		cadastroUsuarioDadosPessoaisLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastroUsuarioDadosPessoaisLabel.setForeground(Color.BLACK);
		cadastroUsuarioDadosPessoaisLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		cadastroUsuarioDadosPessoaisLabel.setBackground(Color.WHITE);
		cadastroUsuarioDadosPessoaisLabel.setBounds(828, 142, 384, 16);
		cadastroUsuario.add(cadastroUsuarioDadosPessoaisLabel);

		JLabel cadastroUsuarioNomeLabel = new JLabel("Nome:");
		cadastroUsuarioNomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioNomeLabel.setBounds(790, 171, 56, 16);
		cadastroUsuario.add(cadastroUsuarioNomeLabel);

		cadastroUsuarioNomeTexto = new JTextField();
		cadastroUsuarioNomeTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioNomeTexto.setColumns(10);
		cadastroUsuarioNomeTexto.setBounds(790, 200, 286, 30);
		cadastroUsuario.add(cadastroUsuarioNomeTexto);

		JLabel cadastroUsuarioDataNascimentoLabel = new JLabel("Data Nascimento:");
		cadastroUsuarioDataNascimentoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioDataNascimentoLabel.setBounds(1111, 171, 140, 16);
		cadastroUsuario.add(cadastroUsuarioDataNascimentoLabel);

		cadastroUsuarioDataNascimentoTexto = new JTextField();
		cadastroUsuarioDataNascimentoTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioDataNascimentoTexto.setColumns(10);
		cadastroUsuarioDataNascimentoTexto.setBounds(1111, 200, 140, 30);
		cadastroUsuario.add(cadastroUsuarioDataNascimentoTexto);

		JLabel cadastroUsuarioCPFLabel = new JLabel("CPF:");
		cadastroUsuarioCPFLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioCPFLabel.setBounds(790, 253, 73, 16);
		cadastroUsuario.add(cadastroUsuarioCPFLabel);

		JFormattedTextField cadastroUsuarioCPFTexto = new JFormattedTextField();
		cadastroUsuarioCPFTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioCPFTexto.setBounds(790, 285, 155, 30);
		cadastroUsuario.add(cadastroUsuarioCPFTexto);

		JLabel cadastroUsuarioRGLabel = new JLabel("RG:");
		cadastroUsuarioRGLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioRGLabel.setBounds(957, 253, 73, 16);
		cadastroUsuario.add(cadastroUsuarioRGLabel);

		JFormattedTextField cadastroUsuarioRGTexto = new JFormattedTextField();
		cadastroUsuarioRGTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioRGTexto.setBounds(957, 285, 119, 30);
		cadastroUsuario.add(cadastroUsuarioRGTexto);

		JLabel cadastroUsuarioSexoLabel = new JLabel("Sexo:");
		cadastroUsuarioSexoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioSexoLabel.setBounds(1111, 253, 73, 16);
		cadastroUsuario.add(cadastroUsuarioSexoLabel);

		JComboBox<Sexo> cadastroUsuarioSexoComboBox = new JComboBox<Sexo>();
		cadastroUsuarioSexoComboBox.setModel(new DefaultComboBoxModel<Sexo>(Sexo.values()));
		cadastroUsuarioSexoComboBox.setBounds(1111, 285, 140, 30);
		cadastroUsuario.add(cadastroUsuarioSexoComboBox);

		JLabel cadastroUsuarioInformacoesContaLabel = new JLabel("Informa\u00E7\u00E3o da Conta");
		cadastroUsuarioInformacoesContaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastroUsuarioInformacoesContaLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		cadastroUsuarioInformacoesContaLabel.setBounds(828, 378, 384, 16);
		cadastroUsuario.add(cadastroUsuarioInformacoesContaLabel);

		JLabel cadastroUsuarioEmailLabel = new JLabel("E-mail:");
		cadastroUsuarioEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioEmailLabel.setBounds(790, 407, 56, 16);
		cadastroUsuario.add(cadastroUsuarioEmailLabel);

		cadastroUsuarioEmailTexto = new JTextField();
		cadastroUsuarioEmailTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioEmailTexto.setColumns(10);
		cadastroUsuarioEmailTexto.setBounds(790, 436, 286, 30);
		cadastroUsuario.add(cadastroUsuarioEmailTexto);

		JLabel cadastroUsuarioSenhaLabel = new JLabel("Senha:");
		cadastroUsuarioSenhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioSenhaLabel.setBounds(1111, 407, 140, 16);
		cadastroUsuario.add(cadastroUsuarioSenhaLabel);

		cadastroUsuarioSenhaTexto = new JPasswordField();
		cadastroUsuarioSenhaTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cadastroUsuarioSenhaTexto.setBounds(1111, 436, 140, 30);
		cadastroUsuario.add(cadastroUsuarioSenhaTexto);

		Button cadastroUsuarioCadastrarBtn = new Button("Cadastrar");
		cadastroUsuarioCadastrarBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if (!bd.isConectado())
						bd.conectar();
					if (bd.isConectado()) {
						if (cadastroUsuarioNomeTexto.getText().contentEquals("")
								|| cadastroUsuarioDataNascimentoTexto.getText().contentEquals("")
								|| cadastroUsuarioRGTexto.getText().contentEquals("")
								|| cadastroUsuarioCPFTexto.getText().contentEquals("")
								|| cadastroUsuarioEmailTexto.getText().contentEquals("")
								|| cadastroUsuarioSenhaTexto.getText().contentEquals("")) {
							JOptionPane.showMessageDialog(contentPane,
									"Não foi possivel cadastrar o usuário. Certifique-se de preencher todos os campos.",
									"Erro ao cadastrar usuário", JOptionPane.ERROR_MESSAGE);
						} else if (!Data.isDataValida(cadastroUsuarioDataNascimentoTexto.getText())) {
							JOptionPane.showMessageDialog(contentPane,
									"Não foi possivel cadastrar o usuário. Certifique-se de inserir a data no formato DD/MM/YYYY.",
									"Erro ao cadastrar usuário", JOptionPane.ERROR_MESSAGE);
						} else {
							if (cadastroUsuarioSexoComboBox.getSelectedItem() == Sexo.MASCULINO) {
								bd.inserirContato(new Usuario(cadastroUsuarioNomeTexto.getText(), Sexo.MASCULINO,
										new Data(cadastroUsuarioDataNascimentoTexto.getText()),
										cadastroUsuarioRGTexto.getText(), cadastroUsuarioCPFTexto.getText(),
										cadastroUsuarioEmailTexto.getText(), cadastroUsuarioSenhaTexto.getText()));
							} else if (cadastroUsuarioSexoComboBox.getSelectedItem() == Sexo.FEMININO) {
								bd.inserirContato(new Usuario(cadastroUsuarioNomeTexto.getText(), Sexo.FEMININO,
										new Data(cadastroUsuarioDataNascimentoTexto.getText()),
										cadastroUsuarioRGTexto.getText(), cadastroUsuarioCPFTexto.getText(),
										cadastroUsuarioEmailTexto.getText(), cadastroUsuarioSenhaTexto.getText()));
							}
							JOptionPane.showMessageDialog(contentPane, "Usuário cadastrado com sucesso!", "",
									JOptionPane.INFORMATION_MESSAGE);
							cadastroUsuario.setVisible(false);
							login.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Não foi possivel conectar ao banco de dados.",
								"Erro ao cadastrar usuário", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {

				}
			}
		});
		cadastroUsuarioCadastrarBtn.setForeground(Color.WHITE);
		cadastroUsuarioCadastrarBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
		cadastroUsuarioCadastrarBtn.setBackground(new Color(47, 79, 79));
		cadastroUsuarioCadastrarBtn.setBounds(949, 506, 177, 33);
		cadastroUsuario.add(cadastroUsuarioCadastrarBtn);

		homeLogado = new JPanel();
		homeLogado.setBackground(Color.WHITE);
		layeredPane.add(homeLogado, "name_685026164454422");
		homeLogado.setLayout(new CardLayout(0, 0));

		JLayeredPane layeredPane_1 = new JLayeredPane();
		homeLogado.add(layeredPane_1, "name_685049264052223");
		// HOME
		home = new JPanel();
		home.setBackground(Color.WHITE);
		home.setBounds(0, 0, 1590, 590);
		layeredPane_1.add(home);
		home.setLayout(null);
		
				painelVoltarBtn = new JButton("Voltar");
				painelVoltarBtn.setBounds(1336, 13, 97, 25);
				home.add(painelVoltarBtn);
				painelVoltarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						homeLogado.setVisible(true);
						home.setVisible(true);
						painelVoltarBtn.setVisible(false);
						homeNovaMovimentacao.setVisible(false);
						homeInicio.setVisible(true);
					}
				});
				painelVoltarBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));

		homeNovaMovimentacao = new JPanel();
		homeNovaMovimentacao.setBackground(Color.WHITE);
		homeNovaMovimentacao.setBounds(330, 0, 1145, 590);
		home.add(homeNovaMovimentacao);
		homeNovaMovimentacao.setLayout(null);

		JLabel novaMovimentacaoCadastrarMovimentacaoLabel = new JLabel("Cadastrar Movimenta\u00E7\u00E3o");
		novaMovimentacaoCadastrarMovimentacaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		novaMovimentacaoCadastrarMovimentacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		novaMovimentacaoCadastrarMovimentacaoLabel.setEnabled(false);
		novaMovimentacaoCadastrarMovimentacaoLabel.setBounds(449, 43, 280, 75);
		homeNovaMovimentacao.add(novaMovimentacaoCadastrarMovimentacaoLabel);

		JLabel novaMovimentacaoDescricaoLabel = new JLabel("Descri\u00E7\u00E3o:");
		novaMovimentacaoDescricaoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoDescricaoLabel.setBounds(353, 182, 116, 20);
		homeNovaMovimentacao.add(novaMovimentacaoDescricaoLabel);

		JTextField novaMovimentacaoDescricaoText = new JTextField();
		novaMovimentacaoDescricaoText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoDescricaoText.setBounds(353, 211, 326, 30);
		homeNovaMovimentacao.add(novaMovimentacaoDescricaoText);
		novaMovimentacaoDescricaoText.setColumns(10);

		JLabel novaMovimentacaoDataLabel = new JLabel("Data:");
		novaMovimentacaoDataLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoDataLabel.setBounds(728, 184, 116, 20);
		homeNovaMovimentacao.add(novaMovimentacaoDataLabel);

		JTextField novaMovimentacaoDataText = new JTextField();
		novaMovimentacaoDataText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoDataText.setColumns(10);
		novaMovimentacaoDataText.setBounds(728, 211, 163, 30);
		homeNovaMovimentacao.add(novaMovimentacaoDataText);

		JLabel novaMovimentacaoValorLabel = new JLabel("Valor:");
		novaMovimentacaoValorLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoValorLabel.setBounds(353, 291, 116, 20);
		homeNovaMovimentacao.add(novaMovimentacaoValorLabel);

		JTextField novaMovimentacaoValorText = new JTextField();
		novaMovimentacaoValorText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoValorText.setBounds(353, 320, 116, 30);
		homeNovaMovimentacao.add(novaMovimentacaoValorText);
		novaMovimentacaoValorText.setColumns(10);

		JLabel novaMovimentacaoCategoriaLabel = new JLabel("Categoria:");
		novaMovimentacaoCategoriaLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoCategoriaLabel.setBounds(546, 291, 116, 20);
		homeNovaMovimentacao.add(novaMovimentacaoCategoriaLabel);

		JComboBox<?> novaMovimentacaoTipoComboBox;
		novaMovimentacaoTipoComboBox = new JComboBox();
		novaMovimentacaoTipoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoTipoComboBox.setModel(new DefaultComboBoxModel(TipoReceita.values()));
		novaMovimentacaoTipoComboBox.setSelectedIndex(0);
		novaMovimentacaoTipoComboBox.setBounds(728, 320, 163, 30);
		homeNovaMovimentacao.add(novaMovimentacaoTipoComboBox);
		
		JRadioButton novaMovimentacaoReceitaRadio = new JRadioButton("Receita");
		novaMovimentacaoReceitaRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaMovimentacaoTipoComboBox.setModel(new DefaultComboBoxModel(TipoReceita.values()));
			}
		});
		novaMovimentacaoCategoriaButtonGroup.add(novaMovimentacaoReceitaRadio);
		novaMovimentacaoReceitaRadio.setSelected(true);
		novaMovimentacaoReceitaRadio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoReceitaRadio.setToolTipText("");
		novaMovimentacaoReceitaRadio.setBounds(507, 320, 85, 30);
		homeNovaMovimentacao.add(novaMovimentacaoReceitaRadio);

		JRadioButton novaMovimentacaoDespesaRadio = new JRadioButton("Despesa");
		novaMovimentacaoDespesaRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaMovimentacaoTipoComboBox.setModel(new DefaultComboBoxModel(TipoDespesa.values()));
			}
		});
		novaMovimentacaoCategoriaButtonGroup.add(novaMovimentacaoDespesaRadio);
		novaMovimentacaoDespesaRadio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoDespesaRadio.setBounds(596, 320, 108, 30);
		homeNovaMovimentacao.add(novaMovimentacaoDespesaRadio);

		JLabel novaMovimentacaoTipoLabel = new JLabel("Tipo:");
		novaMovimentacaoTipoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		novaMovimentacaoTipoLabel.setBounds(728, 291, 116, 20);
		homeNovaMovimentacao.add(novaMovimentacaoTipoLabel);

		Button novaMovimentacaoSalvarBtn = new Button("Salvar");
		novaMovimentacaoSalvarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (bd.isConectado()) {
						if (novaMovimentacaoReceitaRadio.isSelected()) {
							// ACADEMIA", "AGUA", "ALUGUEL", "CLUBE
							switch (((String) novaMovimentacaoTipoComboBox.getSelectedItem()).toLowerCase()) {
							case "salario":
								bd.inserirMovimentacaoReceita(new Receita(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoReceita.SALARIO),
										_idUsuario);
								break;
							case "outros":
								bd.inserirMovimentacaoReceita(new Receita(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoReceita.OUTROS),
										_idUsuario);
								break;
							}

						} else {
							switch (((String) novaMovimentacaoTipoComboBox.getSelectedItem()).toLowerCase()) {
							// ALUGUEL, TELEFONE, INTERNET, ACADEMIA, CLUBE, SUPERMERCADO, LUZ, AGUA
							case "aluguel":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.ALUGUEL),
										_idUsuario);
								break;
							case "telefone":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.TELEFONE),
										_idUsuario);
								break;
							case "internet":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.INTERNET),
										_idUsuario);
								break;
							case "academia":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.ACADEMIA),
										_idUsuario);
								break;
							case "clube":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.CLUBE),
										_idUsuario);
								break;
							case "supermercado":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()),
										TipoDespesa.SUPERMERCADO), _idUsuario);
								break;
							case "luz":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.LUZ),
										_idUsuario);
								break;
							case "agua":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.AGUA),
										_idUsuario);
								break;
							case "outros":
								bd.inserirMovimentacaoDespesa(new Despesa(new Data(novaMovimentacaoDataText.getText()),
										novaMovimentacaoDescricaoText.getText(),
										Double.parseDouble(novaMovimentacaoValorText.getText()), TipoDespesa.OUTROS),
										_idUsuario);
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
		novaMovimentacaoSalvarBtn.setForeground(Color.WHITE);
		novaMovimentacaoSalvarBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
		novaMovimentacaoSalvarBtn.setBackground(new Color(47, 79, 79));
		novaMovimentacaoSalvarBtn.setBounds(523, 456, 177, 33);
		homeNovaMovimentacao.add(novaMovimentacaoSalvarBtn);

		homeInicio = new JPanel();
		homeInicio.setBackground(Color.WHITE);
		homeInicio.setBounds(330, 0, 1145, 590);
		home.add(homeInicio);
		homeInicio.setLayout(null);

		JFreeChart homeInicioGraficoGeralChart;
		DefaultPieDataset homeInicioGraficoGeralDataSet = new DefaultPieDataset();
		homeInicioGraficoGeralChart = ChartFactory.createPieChart("Tipo de movimentação", homeInicioGraficoGeralDataSet,
				true, true, true);
		ChartPanel homeInicioGraficoGeral = new ChartPanel(homeInicioGraficoGeralChart);
		homeInicioGraficoGeral.setBackground(Color.WHITE);
		homeInicioGraficoGeral.setBounds(774, 295, 371, 295);
		homeInicio.add(homeInicioGraficoGeral);
		homeInicioGraficoGeral.setDisplayToolTips(true);
		homeInicioGraficoGeral.setMouseWheelEnabled(true);
		homeInicioGraficoGeral.setLayout(null);

		JLabel homeInicioSaldoLabel = new JLabel("SALDO:");
		homeInicioSaldoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeInicioSaldoLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		homeInicioSaldoLabel.setBounds(762, 36, 383, 66);
		homeInicio.add(homeInicioSaldoLabel);

		homeInicioSaldoText = new JTextField();
		homeInicioSaldoText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		homeInicioSaldoText.setHorizontalAlignment(SwingConstants.CENTER);
		homeInicioSaldoText.setBackground(Color.WHITE);
		homeInicioSaldoText.setEditable(false);
		homeInicioSaldoText.setBounds(829, 134, 254, 56);
		homeInicio.add(homeInicioSaldoText);
		homeInicioSaldoText.setColumns(10);

		JLabel label = new JLabel("Lista de Receitas do M\u00EAs");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(247, 0, 266, 31);
		homeInicio.add(label);

		JLabel lblListaDeDespesas = new JLabel("Lista de Despesas do M\u00EAs");
		lblListaDeDespesas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeDespesas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDeDespesas.setBounds(247, 295, 266, 31);
		homeInicio.add(lblListaDeDespesas);

		JButton painelHistoricoBtn = new JButton("Hist\u00F3rico");
		painelHistoricoBtn.setBounds(0, 171, 330, 37);
		painelHistoricoBtn.setForeground(Color.WHITE);
		painelHistoricoBtn.setFont(new Font("Dialog", Font.PLAIN, 15));
		painelHistoricoBtn.setBackground(new Color(49, 113, 37));
		home.add(painelHistoricoBtn);

		JButton painelFecharBtn = new JButton("Fechar");
		painelFecharBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelFecharBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		painelFecharBtn.setBounds(1481, 13, 97, 25);
		home.add(painelFecharBtn);

		JButton painelNovaMovimentacaoBtn = new JButton("Nova Movimenta\u00E7\u00E3o");
		painelNovaMovimentacaoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeLogado.setVisible(true);
				home.setVisible(true);
				homeInicio.setVisible(false);
				painelVoltarBtn.setVisible(true);
				homeNovaMovimentacao.setVisible(true);
			}
		});
		painelNovaMovimentacaoBtn.setBounds(0, 79, 330, 37);
		painelNovaMovimentacaoBtn.setForeground(Color.WHITE);
		painelNovaMovimentacaoBtn.setFont(new Font("Dialog", Font.PLAIN, 15));
		painelNovaMovimentacaoBtn.setBackground(new Color(49, 113, 37));
		home.add(painelNovaMovimentacaoBtn);

		JButton painelRelatoriosBtn = new JButton("Relat\u00F3rios");
		painelRelatoriosBtn.setBounds(0, 263, 330, 37);
		painelRelatoriosBtn.setForeground(Color.WHITE);
		painelRelatoriosBtn.setFont(new Font("Dialog", Font.PLAIN, 15));
		painelRelatoriosBtn.setBackground(new Color(49, 113, 37));
		home.add(painelRelatoriosBtn);

		JButton painelSairBtn = new JButton("Sair");
		painelSairBtn.setBounds(0, 532, 330, 37);
		painelSairBtn.setForeground(Color.WHITE);
		painelSairBtn.setFont(new Font("Dialog", Font.PLAIN, 15));
		painelSairBtn.setBackground(new Color(47, 79, 79));
		home.add(painelSairBtn);

		JLabel painelImagemLabel = new JLabel("");
		painelImagemLabel.setBounds(0, 0, 330, 590);
		painelImagemLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/essa - Copia.jpg")));
		home.add(painelImagemLabel);

		String[] homeInicioColunaTable = { "Tipo", "Valor" };

		JPanel homeHistorico = new JPanel();
		homeHistorico.setBackground(Color.WHITE);
		homeHistorico.setBounds(330, 0, 1145, 590);
		home.add(homeHistorico);

		JLabel loginLOGINLabel = new JLabel("LOGIN");
		loginLOGINLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLOGINLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginLOGINLabel.setEnabled(false);
		loginLOGINLabel.setBounds(920, 57, 137, 75);
		login.add(loginLOGINLabel);

		JLabel loginEmailLabel = new JLabel("Email:");
		loginEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginEmailLabel.setBounds(790, 166, 84, 33);
		login.add(loginEmailLabel);

		loginEmailTexto = new JTextField();
		loginEmailTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginEmailTexto.setColumns(10);
		loginEmailTexto.setBounds(790, 211, 383, 33);
		login.add(loginEmailTexto);

		JLabel loginSenhaLabel = new JLabel("Senha:");
		loginSenhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginSenhaLabel.setBounds(790, 290, 77, 27);
		login.add(loginSenhaLabel);

		loginSenhaTexto = new JPasswordField();
		loginSenhaTexto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginSenhaTexto.setBounds(790, 330, 383, 33);
		login.add(loginSenhaTexto);

		JButton loginLOGINBtn = new JButton("Login");
		loginLOGINBtn.setVisible(true);
		loginLOGINBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!bd.isConectado())
						bd.conectar();
					if (bd.isConectado()) {
						if (bd.acesso(loginEmailTexto.getText(), loginSenhaTexto.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Seja bem vindo!", "",
									JOptionPane.INFORMATION_MESSAGE);
							_idUsuario = bd.idUsuario(loginEmailTexto.getText());

							// iniciar valor total
							homeInicioSaldoText.setText("R$ " + bd.getValorTotal(_idUsuario));
							if (bd.getValorTotal(_idUsuario) < 0) {
								homeInicioSaldoText.setForeground(Color.RED);
							} else {
								homeInicioSaldoText.setForeground(new Color(0, 100, 0));
							}

							// iniciar gráficos
							homeInicioGraficoGeralDataSet.setValue("Despesa",
									new Double(bd.getPorcentagemDespesa(_idUsuario)));
							homeInicioGraficoGeralDataSet.setValue("Receita",
									new Double(bd.getPorcentagemReceita(_idUsuario)));

							// iniciar tabelas
							JTable homeInicioReceitaMesTable = new JTable(bd.getMatrizDados("RECEITA", _idUsuario),
									homeInicioColunaTable);
							homeInicioReceitaMesTable.setBackground(new Color(255, 255, 255));
							homeInicioReceitaMesTable.setBounds(216, 282, 266, 258);
							JScrollPane homeInicioReceitaMesTablePane = new JScrollPane(homeInicioReceitaMesTable);
							homeInicioReceitaMesTablePane.setLocation(12, 35);
							homeInicioReceitaMesTablePane.setSize(750, 260);
							homeInicio.add(homeInicioReceitaMesTablePane);

							JTable homeInicioDespesaMesTable = new JTable(bd.getMatrizDados("DESPESA", _idUsuario),
									homeInicioColunaTable);
							homeInicioDespesaMesTable.setBackground(new Color(255, 255, 255));
							homeInicioDespesaMesTable.setBounds(216, 282, 266, 258);
							JScrollPane homeInicioDespesaMesTablePane = new JScrollPane(homeInicioDespesaMesTable);
							homeInicioDespesaMesTablePane.setLocation(12, 330);
							homeInicioDespesaMesTablePane.setSize(750, 260);
							homeInicio.add(homeInicioDespesaMesTablePane);

							// mudar views
							login.setVisible(false);
							homeLogado.setVisible(true);
							home.setVisible(true);
							painelVoltarBtn.setVisible(false);
							homeNovaMovimentacao.setVisible(false);

							homeInicio.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(contentPane, "Login ou Senha inválido.",
									"Erro ao fazer login", JOptionPane.ERROR_MESSAGE);
							loginSenhaTexto.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Não foi possivel conectar ao banco de dados",
								"Erro ao logar", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		loginLOGINBtn.setForeground(Color.WHITE);
		loginLOGINBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
		loginLOGINBtn.setBackground(new Color(47, 79, 79));
		loginLOGINBtn.setBounds(897, 416, 177, 33);
		login.add(loginLOGINBtn);

		JButton loginCadastrarUsuarioBtn = new JButton("Cadastre-se");
		loginCadastrarUsuarioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.setVisible(false);
				cadastroUsuario.setVisible(true);
			}
		});
		loginCadastrarUsuarioBtn.setVisible(true);
		loginCadastrarUsuarioBtn.setForeground(Color.BLACK);
		loginCadastrarUsuarioBtn.setFont(new Font("Dialog", Font.ITALIC, 17));
		loginCadastrarUsuarioBtn.setBackground(Color.WHITE);
		loginCadastrarUsuarioBtn.setBounds(857, 501, 261, 33);
		login.add(loginCadastrarUsuarioBtn);

		JLabel loginImagemLabel = new JLabel("");
		loginImagemLabel.setBounds(0, 0, 448, 590);
		login.add(loginImagemLabel);
		loginImagemLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/essa - Copia.jpg")));

		JButton loginFecharBtn = new JButton("Fechar");
		loginFecharBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		loginFecharBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginFecharBtn.setBounds(1481, 13, 97, 25);
		login.add(loginFecharBtn);
	}
}
