package vestbjerggui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buildingmerchant.controller.CustomerController;
import buildingmerchant.model.Customer;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class CreateOrder extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<MainMenu> createOrders;
	private CustomerController customerController;
	private JTextField textTlf;
	private JLabel lblTlf;
	private JButton okSearch;
	private JButton cancelButton;
	private JLabel lblName;
	private JTextField textName;
	private JButton btnNext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			CreateOrder dialog = new CreateOrder();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateOrder() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblCreateOrder = new JLabel("Create Order");
			lblCreateOrder.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblCreateOrder, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				lblTlf = new JLabel("Telephonenumber:");
				GridBagConstraints gbc_lblTlf = new GridBagConstraints();
				gbc_lblTlf.insets = new Insets(0, 0, 5, 5);
				gbc_lblTlf.gridx = 1;
				gbc_lblTlf.gridy = 1;
				panel.add(lblTlf, gbc_lblTlf);
			}
			{
				textTlf = new JTextField();
				GridBagConstraints gbc_textTlf = new GridBagConstraints();
				gbc_textTlf.insets = new Insets(0, 0, 5, 0);
				gbc_textTlf.fill = GridBagConstraints.HORIZONTAL;
				gbc_textTlf.gridx = 3;
				gbc_textTlf.gridy = 1;
				panel.add(textTlf, gbc_textTlf);
				textTlf.setColumns(10);
			}
			{
				lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblName.insets = new Insets(0, 0, 0, 5);
				gbc_lblName.gridx = 1;
				gbc_lblName.gridy = 2;
				panel.add(lblName, gbc_lblName);
			}
			{
				textName = new JTextField();
				textName.setEditable(false);
				GridBagConstraints gbc_textName = new GridBagConstraints();
				gbc_textName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textName.gridx = 3;
				gbc_textName.gridy = 2;
				panel.add(textName, gbc_textName);
				textName.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okSearch = new JButton("Search");
				okSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						searchOrder();
					}
				});
				okSearch.setActionCommand("OK");
				buttonPane.add(okSearch);
				getRootPane().setDefaultButton(okSearch);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelClicked();
					}
				});
				{
					btnNext = new JButton("Next");
					btnNext.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							nextOrder();
						}
					});
					buttonPane.add(btnNext);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				
			}
			
		}
		customerController = new CustomerController ();
		customerController.generateTestCustomers();
	}

	private void nextOrder() {
		// TODO Auto-generated method stub
		if(textName.getText().isEmpty()) {
			errorCodeName();
		
			
		}
		else {
			NextOrder n = new NextOrder();
			n.setVisible(true);
		}
		
	}

	private void errorCodeName() {
		ErrorCodeName e = new ErrorCodeName();
		e.setVisible(true);
		
		// TODO Auto-generated method stub
		
	}

	private void cancelClicked() {
		// TODO Auto-generated method stub
		this.dispose();
		this.setVisible(false);
	}
	
	private void errorCodeTlf() {
		ErrorCodeTlf e = new ErrorCodeTlf();
		e.setVisible(true);

		
	}

	private void searchOrder() {
		// TODO Auto-generated method stub
		if(textTlf.getText().length()==(8)) {
			
			Customer customer = customerController.findCustomer(textTlf.getText()); 
		
				
		if(customer!= null) {
			textName.setText(customer.getName());
			
		}
			
		}
		else { errorCodeTlf();
			
			
			
		}
	}

}
