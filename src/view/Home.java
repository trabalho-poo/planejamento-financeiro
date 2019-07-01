package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.CardLayout;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	int xx,xy;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					Home frame = new Home();
	//					frame.setUndecorated(true);
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}


	// going to borrow code from a gist to move frame.


	/**
	 * Create the frame.
	 */
	public Home(int _idUsuario) {
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
				new NovaMovimentacao(_idUsuario).setVisible(true);

			}
		});

		panel.add(button);

		JLabel lblFundo = new JLabel("Nova Movimenta\u00E7\u00E3o");
		lblFundo.setIcon(new ImageIcon(Home.class.getResource("/img/essa - Copia.jpg")));
		lblFundo.setBounds(0, 0, 188, 553);
		panel.add(lblFundo);


		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Salário", new Integer(10));
		pieDataset.setValue("Telefone", new Integer(20));
		pieDataset.setValue("Outros", new Integer(30));
		JFreeChart chart = ChartFactory.createPieChart("Movimentações", pieDataset, true, true, true);
		
		DefaultPieDataset pie2 = new DefaultPieDataset();
		pie2.setValue("Salário", new Integer(10));
		pie2.setValue("Telefone", new Integer(20));
		pie2.setValue("Outros", new Integer(30));
		JFreeChart chart2 = ChartFactory.createPieChart("Movimentações", pie2, true, true, true);
		//		P.setForegroundAlpha(TOP_ALIGNMENT);
		ChartPanel grafico = new ChartPanel(chart);
		grafico.setBounds(187, 0, 291, 277);
		contentPane.add(grafico);
		grafico.setDisplayToolTips(true);
		grafico.setMouseWheelEnabled(true);
		grafico.setLayout(null);
		
		ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setLayout(null);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setDisplayToolTips(true);
		chartPanel.setBounds(541, 0, 291, 277);
		contentPane.add(chartPanel);
		//		P.setForegroundAlpha(TOP_ALIGNMENT);
		ChartPanel grafico2 = new ChartPanel(chart2);
		grafico2.setBounds(0, 0, 291, 277);
		chartPanel.add(grafico2);
		grafico2.setDisplayToolTips(true);
		grafico2.setMouseWheelEnabled(true);
		grafico2.setLayout(null);

		//		JButton Chart = new JButton("BOTAOGRAFICO");
		//		Chart.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				DefaultPieDataset pieDataset = new DefaultPieDataset();
		//				pieDataset.setValue("One", new Integer(10));
		//				pieDataset.setValue("Two", new Integer(20));
		//				pieDataset.setValue("Three", new Integer(30));
		//				JFreeChart chart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, true);
		//				PiePlot P = (PiePlot)chart.getPlot();
		//				P.setForegroundAlpha(TOP_ALIGNMENT);
		//				ChartFrame frame = new ChartFrame("Pie Chart",chart);
		//				frame.setVisible(true);
		//				frame.setSize(450,500);
		//			}
		//		});
		//		grafico.add(Chart);




		//		
		//		DefaultPieDataset pieDataset = new DefaultPieDataset();
		//		pieDataset.setValue("One", new Integer(10));
		//		pieDataset.setValue("Two", new Integer(20));
		//		pieDataset.setValue("Three", new Integer(30));
		//		JFreeChart chart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, true);
		//		PiePlot P = (PiePlot)chart.getPlot();
		//		
		//		ChartFrame frame = new ChartFrame("Pie Chart",chart);
		//		frame.setVisible(true);
		//		frame.setSize(450,500);

	}
}
