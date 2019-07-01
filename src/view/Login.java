package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import implementacoes.BancoDeDados;

import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;


import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JPasswordField senha;
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
	public Login() {
		setAlwaysOnTop(true);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 350, 553);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");

		/*
		 * 	label.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				  xx = e.getX();
			      xy = e.getY();

			}


		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getXOnScreen();
		        int y = arg0.getYOnScreen();
		        Login.this.setLocation(x - xx, y - xy); 
			}
		});
		 */
		label.setIcon(new ImageIcon(Login.class.getResource("/img/essa - Copia.jpg")));
		label.setBounds(0, 0, 350, 555);
		panel.add(label);

		Button button = new Button("Login");
		button.setFont(new Font("Dialog", Font.PLAIN, 18));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(47, 79, 79));
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {


				BancoDeDados bd = new BancoDeDados();
				try {
					bd.conectar();
					if(bd.isConectado()) {
						if( bd.acesso(email.getText(), senha.getText()) )
						{
							JOptionPane.showMessageDialog(contentPane, "Seja Bem vindo!", "", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							//new Home().setVisible(true);
							new Home(bd.idUsuario(email.getText()),bd).setVisible(true); 
						}else {		        	 
							JOptionPane.showMessageDialog(contentPane, "Login ou Senha inválido.", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE);
							senha.setText("");
						}					
					}else {
						JOptionPane.showMessageDialog(contentPane, "Não foi possivel conectar ao banco de dados", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE);

					}
				} catch (Exception e) {
					System.out.println("Erro: "+e.getMessage());
					//e.printStackTrace();
				}
				
			}
		});

		button.setBounds(508, 391, 177, 33);
		contentPane.add(button);

		email = new JTextField();
		email.setBounds(401, 186, 383, 33);
		contentPane.add(email);
		email.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(401, 216, 382, 2);
		contentPane.add(separator);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(401, 141, 84, 33);
		contentPane.add(lblEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(401, 265, 77, 27);
		contentPane.add(lblSenha);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(401, 336, 382, 2);
		contentPane.add(separator_1);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setEnabled(false);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(531, 32, 137, 75);
		contentPane.add(lblLogin);

		senha = new JPasswordField();
		senha.setBounds(401, 305, 383, 33);
		contentPane.add(senha);

		Button button_1 = new Button("Cadastre-se");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//new CadastroUsuario().setVisible(true); 
				//CadastroUsuario TelaUsu = new CadastroUsuario();
				//TelaUsu.setVisible(true);
				//telaUsu.panel.setVisible(true);
				//TelaPrincipal telaP = new TelaPrincipal(); telaP.frmFrenteDeCaixa.setVisible(true);

				new CadastroUsuario1().setVisible(true); 
			}
		});
		button_1.setFont(new Font("Dialog", Font.ITALIC, 17));
		button_1.setForeground(Color.BLACK);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(468, 476, 261, 33);
		contentPane.add(button_1);
	}
}
