package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnCreateOrder;
	private JButton btnLendTools;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMainMenu = new JLabel("Main menu");
		lblMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblMainMenu, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -41, 0};
		gbl_panel.rowHeights = new int[]{31, 0, 0, 24, 0, 21, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnCreateOrder = new JButton("Create order");
		btnCreateOrder.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createOrder();
			}
		});
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateOrder.gridx = 1;
		gbc_btnCreateOrder.gridy = 1;
		panel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		btnLendTools = new JButton("Lend tools");
		btnLendTools.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lendTool();
			}
		});
		btnLendTools.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnLendTools = new GridBagConstraints();
		gbc_btnLendTools.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLendTools.insets = new Insets(0, 0, 5, 5);
		gbc_btnLendTools.gridx = 1;
		gbc_btnLendTools.gridy = 4;
		panel.add(btnLendTools, gbc_btnLendTools);
		
		JButton btnCreateCus = new JButton("Create customer");
		btnCreateCus.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnCreateCus = new GridBagConstraints();
		gbc_btnCreateCus.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateCus.gridx = 1;
		gbc_btnCreateCus.gridy = 6;
		panel.add(btnCreateCus, gbc_btnCreateCus);
	}

	private void lendTool() {
		// TODO Auto-generated method stub
		LendTool t = new LendTool();
		t.setVisible(true);
		
	}

	private void createOrder() {
		CreateOrder o = new CreateOrder();
		o.setVisible(true);
		
		// TODO Auto-generated method stub
		
	}

}
