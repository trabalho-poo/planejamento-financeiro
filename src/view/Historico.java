package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import implementacoes.BancoDeDados;

public class Historico extends JFrame {

	private JPanel contentPane;
	private JTable tableHistorico;
	private JTable table;

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
	public Historico(int _idUsuario, BancoDeDados bd) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(188, 33, 644, 482);
		contentPane.add(scrollPane);
		String[] colunas = { "Descrição", "Data", "Tipo", "Classificacao", "Valor", "Excluir" };

		Object[][] dados = bd.getHistorico(_idUsuario);

//		for(int i=0; i< dados.length; i++) {
//			dados[i][4]
//		}

//		table = new JTable();
		table = new JTable(dados, colunas);
		scrollPane.setViewportView(table);
		table.getColumn("Excluir").setCellRenderer((TableCellRenderer) new ButtonRenderer());
		table.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));

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
		btnVoltar.setBounds(765, 0, 57, 30);
		contentPane.add(btnVoltar);

//		JScrollPane paneTableReceita = new JScrollPane();
//		paneTableReceita.setLocation(216, 13);
//		paneTableReceita.setSize(604, 535);
//		contentPane.add(paneTableReceita);
//
//		tableHistorico = new JTable(bd.getMatrizDados("RECEITA", _idUsuario), colunas);
//		paneTableReceita.setViewportView(tableHistorico);

	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
	protected JButton button;

	private String label;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		JButton button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {

			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {

			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			JOptionPane.showMessageDialog(button, label + ": Ouch!");
			}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
