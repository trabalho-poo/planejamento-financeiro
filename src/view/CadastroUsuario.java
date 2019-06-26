package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import implementacoes.Sexo;
import com.toedter.calendar.JCalendarBeanInfo;

public class CadastroUsuario {

	private JFrame frame;
	private JTextField textField;
	/**
	 * @wbp.nonvisual location=327,131
	 */
	private final JCalendarBeanInfo calendarBeanInfo = new JCalendarBeanInfo();

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(6, 52, 61, 16);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(6, 80, 152, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sexo:");
		lblNewLabel.setBounds(170, 52, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Sexo.values()));
		comboBox.setBounds(170, 81, 130, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(306, 52, 138, 16);
		frame.getContentPane().add(lblDataDeNascimento);
	}
}
