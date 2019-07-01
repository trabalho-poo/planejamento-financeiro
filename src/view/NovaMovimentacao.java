package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
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
import implementacoes.TipoDespesa;
import implementacoes.TipoReceita;
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

public class NovaMovimentacao extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaMovimentacao frame = new NovaMovimentacao();
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
	public NovaMovimentacao() {
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
		
		textField = new JTextField();
		textField.setBounds(61, 181, 326, 30);
		Movimentacao.add(textField);
		textField.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValor.setBounds(61, 261, 116, 16);
		Movimentacao.add(lblValor);
		
		textField_1 = new JTextField();
		textField_1.setBounds(61, 290, 116, 25);
		Movimentacao.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTpo = new JLabel("Tpo:");
		lblTpo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTpo.setBounds(436, 261, 116, 16);
		Movimentacao.add(lblTpo);
		
		JCheckBox chckb_Receita = new JCheckBox("Receita");
		chckb_Receita.setToolTipText("");
		chckb_Receita.setBounds(215, 290, 81, 25);
		Movimentacao.add(chckb_Receita);
		
		JCheckBox chckb_Despesa = new JCheckBox("Despesa");
		chckb_Despesa.setBounds(300, 290, 87, 25);
		Movimentacao.add(chckb_Despesa);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategoria.setBounds(254, 261, 116, 16);
		Movimentacao.add(lblCategoria);
		
		JComboBox<?> comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBox.setSelectedIndex(0);
		
		//o que vai aparecer no comboBox
		
		//caso seja selecionado receita
		chckb_Receita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckb_Despesa.isSelected()== false) {
					if( chckb_Receita.isSelected()== true){
						comboBox.setModel(new DefaultComboBoxModel(new String[] {"SALARIO", "OUTROS"}));
					}else {
						comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Você já seleconou DESPESA", "Erro", JOptionPane.ERROR_MESSAGE);
					chckb_Receita.setSelected(false);
				}
			}
		});
		//caso seja selecionado DESPESA
		chckb_Despesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckb_Receita.isSelected()== false) {
					if( chckb_Despesa.isSelected()== true){
						comboBox.setModel(new DefaultComboBoxModel(new String[] {"ACADEMIA", "AGUA","ALUGUEL","CLUBE","INTERNET", "TELEFONE",  "LUZ",   "SUPERMERCADO","OUTROS"}));
					}else {
						comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Você já seleconou RECEITA", "Erro", JOptionPane.ERROR_MESSAGE);
					chckb_Despesa.setSelected(false);
				}
					
			
			}
		});
			
	
		
		comboBox.setBounds(436, 291, 163, 22);
		Movimentacao.add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(436, 181, 163, 30);
		Movimentacao.add(textField_2);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblData.setBounds(436, 154, 116, 16);
		Movimentacao.add(lblData);
		
		Button button_4 = new Button("Salvar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		button_4.setBackground(new Color(47, 79, 79));
		button_4.setBounds(231, 426, 177, 33);
		Movimentacao.add(button_4);
		
		Button button_5 = new Button("X");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true); 
			}
		});
		button_5.setForeground(Color.RED);
		button_5.setFont(new Font("Dialog", Font.PLAIN, 20));
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(578, 13, 57, 30);
		Movimentacao.add(button_5);

	}
}
