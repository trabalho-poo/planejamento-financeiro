package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import implementacoes.BancoDeDados;
import implementacoes.Data;
import implementacoes.Sexo;
import implementacoes.Usuario;

public class CadastroUsuario1 extends JFrame {
	
	private JPanel contentPane;
	private JTextField nome;
	private JTextField dataNascimento;
	private JTextField email;
	private JPasswordField senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario1 frame = new CadastroUsuario1();
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
	public CadastroUsuario1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 827, 553);
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 266, 560);
		panel.add(label);
		
		JComboBox sexo = new JComboBox();
		sexo.setModel(new DefaultComboBoxModel(Sexo.values()));
		sexo.setBounds(634, 267, 140, 27);
		panel.add(sexo);
		
		nome = new JTextField();
		nome.setColumns(10);
		nome.setBounds(313, 184, 286, 22);
		panel.add(nome);
		label.setIcon(new ImageIcon(Login.class.getResource("/img/essa - Copia.jpg")));
		JSeparator separator = new JSeparator();
		separator.setBounds(313, 204, 251, 2);
		panel.add(separator);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(313, 155, 56, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("CADASTRO DE USU\u00C1RIO");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_2.setEnabled(false);
		label_2.setBounds(381, 13, 317, 75);
		panel.add(label_2);
		
		dataNascimento = new JTextField();
		dataNascimento.setColumns(10);
		dataNascimento.setBounds(634, 182, 140, 22);
		panel.add(dataNascimento);
		
		JLabel label_3 = new JLabel("Data Nascimento:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_3.setBounds(634, 155, 140, 16);
		panel.add(label_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(634, 202, 140, 2);
		panel.add(separator_1);
		
		JLabel label_4 = new JLabel("CPF:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(313, 237, 73, 16);
		panel.add(label_4);
		
		JFormattedTextField cpf = new JFormattedTextField();
		cpf.setBounds(313, 269, 155, 22);
		panel.add(cpf);
		
		JLabel label_5 = new JLabel("RG:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_5.setBounds(480, 237, 73, 16);
		panel.add(label_5);
		
		JFormattedTextField rg = new JFormattedTextField();
		rg.setBounds(480, 269, 119, 22);
		panel.add(rg);
		
		JLabel label_6 = new JLabel("Sexo:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_6.setBounds(634, 238, 73, 16);
		panel.add(label_6);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(313, 420, 286, 22);
		panel.add(email);
		
		JLabel label_7 = new JLabel("Senha:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(634, 391, 140, 16);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("E-mai:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_8.setBounds(313, 391, 56, 16);
		panel.add(label_8);
		
		senha = new JPasswordField();
		senha.setBounds(634, 420, 140, 22);
		panel.add(senha);
		
		Button button = new Button("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BancoDeDados bd = new BancoDeDados();
				try {
					bd.conectar();
					if(bd.isConectado()) {
						if(sexo.getSelectedItem() == Sexo.MASCULINO) {
							bd.inserirContato(new Usuario(nome.getText(), Sexo.MASCULINO, new Data(dataNascimento.getText()), rg.getText(), cpf.getText(), email.getText(), senha.getText()));
						} else if(sexo.getSelectedItem() == Sexo.FEMININO) {
							bd.inserirContato(new Usuario(nome.getText(), Sexo.FEMININO, new Data(dataNascimento.getText()), rg.getText(), cpf.getText(), email.getText(), senha.getText()));
						}
						JOptionPane.showMessageDialog(contentPane, "Usuário cadastrado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						new Login().setVisible(true); 
					}else {
						Component contentPane = null;
						JOptionPane.showMessageDialog(contentPane, "Não foi possivel conectar ao banco de dados", "Erro ao cadastrar usuário", JOptionPane.ERROR_MESSAGE);

					}
				} catch (Exception e) {
					System.out.println("Erro: "+e.getMessage());
					//e.printStackTrace();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 18));
		button.setBackground(new Color(47, 79, 79));
		button.setBounds(472, 490, 177, 33);
		panel.add(button);
		
		JLabel label_9 = new JLabel("Dados Pessoais");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.DARK_GRAY);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_9.setBackground(Color.WHITE);
		label_9.setBounds(351, 126, 384, 16);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("Informa\u00E7\u00E3o da Conta");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_10.setBounds(351, 362, 384, 16);
		panel.add(label_10);
		
		JButton button_1 = new JButton("voltar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true); 
			}
		});
		button_1.setBounds(710, 24, 97, 25);
		panel.add(button_1);
	}

}
