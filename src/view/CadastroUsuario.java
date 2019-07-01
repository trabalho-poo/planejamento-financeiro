package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import implementacoes.BancoDeDados;
import implementacoes.Data;
import implementacoes.Sexo;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.JButton;


public class CadastroUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JTextField textDataN;
	private JTextField textField_2;
	private JPasswordField passwordField;
	

	/**
	 * @wbp.nonvisual location=327,131
	 */
//	private final JCalendarBeanInfo calendarBeanInfo = new JCalendarBeanInfo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario window = new CadastroUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 832, 552);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(634, 267, 140, 27);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(Sexo.values()));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/img/essa - Copia.jpg")));
		label.setBounds(0, 0, 280, 555);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(313, 184, 286, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(313, 204, 251, 2);
		panel.add(separator);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(313, 155, 56, 16);
		panel.add(lblNome);
		
		JLabel lblCadastroDeUsurio = new JLabel("CADASTRO DE USU\u00C1RIO");
		lblCadastroDeUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeUsurio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCadastroDeUsurio.setEnabled(false);
		lblCadastroDeUsurio.setBounds(381, 13, 317, 75);
		panel.add(lblCadastroDeUsurio);
		
		textDataN = new JTextField();
		textDataN.setBounds(634, 182, 140, 22);
		panel.add(textDataN);
		textDataN.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento:");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDataNascimento.setBounds(634, 155, 140, 16);
		panel.add(lblDataNascimento);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(634, 202, 140, 2);
		panel.add(separator_1);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCpf.setBounds(313, 237, 73, 16);
		panel.add(lblCpf);
		
		JFormattedTextField formattedTextCPF = new JFormattedTextField();
		formattedTextCPF.setBounds(313, 269, 155, 22);
		panel.add(formattedTextCPF);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRg.setBounds(480, 237, 73, 16);
		panel.add(lblRg);
		
		JFormattedTextField formattedTextRG = new JFormattedTextField();
		formattedTextRG.setBounds(480, 269, 119, 22);
		panel.add(formattedTextRG);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSexo.setBounds(634, 238, 73, 16);
		panel.add(lblSexo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(313, 420, 286, 22);
		panel.add(textField_2);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(634, 391, 140, 16);
		panel.add(lblSenha);
		
		JLabel lblEmai = new JLabel("E-mai:");
		lblEmai.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmai.setBounds(313, 391, 56, 16);
		panel.add(lblEmai);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(634, 420, 140, 22);
		panel.add(passwordField);
		
		Button button = new Button("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 18));
		button.setBackground(new Color(47, 79, 79));
		button.setBounds(472, 490, 177, 33);
		panel.add(button);
		
		JLabel lblDadosPessoais = new JLabel("Dados Pessoais");
		lblDadosPessoais.setBackground(Color.WHITE);
		lblDadosPessoais.setForeground(Color.DARK_GRAY);
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDadosPessoais.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosPessoais.setBounds(351, 126, 384, 16);
		panel.add(lblDadosPessoais);
		
		JLabel lblInformaoDaConta = new JLabel("Informa\u00E7\u00E3o da Conta");
		lblInformaoDaConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformaoDaConta.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInformaoDaConta.setBounds(351, 362, 384, 16);
		panel.add(lblInformaoDaConta);
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new Login().setVisible(true); 
				
			}
		});
		btnVoltar.setBounds(710, 24, 97, 25);
		panel.add(btnVoltar);
	}
}
