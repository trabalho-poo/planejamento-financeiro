package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
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
				setVisible(false);
				new NovaMovimentacao().setVisible(true); 
				
			}
		});
		
		panel.add(button);
		
		JLabel lblFundo = new JLabel("Nova Movimenta\u00E7\u00E3o");
		lblFundo.setIcon(new ImageIcon(Home.class.getResource("/img/essa - Copia.jpg")));
		lblFundo.setBounds(0, 0, 188, 553);
		panel.add(lblFundo);
		
		JPanel Home = new JPanel();
		Home.setBackground(Color.WHITE);
		Home.setBounds(187, 0, 645, 553);
		contentPane.add(Home);

	}
}
