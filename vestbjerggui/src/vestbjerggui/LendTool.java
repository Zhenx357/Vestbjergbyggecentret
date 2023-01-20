package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buildingmerchant.controller.CustomerController;
import buildingmerchant.model.Customer;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LendTool extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<MainMenu> lendTool;
	private CustomerController customerController;
	private JTextField textTlf;
	private JTextField textName;
	private Customer selectedCustomer;
	private JButton btnNextTool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LendTool dialog = new LendTool();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LendTool() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblLendTool = new JLabel("Lend tool");
			lblLendTool.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblLendTool, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblTlf = new JLabel("Telephonenumber:");
				GridBagConstraints gbc_lblTlf = new GridBagConstraints();
				gbc_lblTlf.fill = GridBagConstraints.HORIZONTAL;
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
				JLabel lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.insets = new Insets(0, 0, 0, 5);
				gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblName.gridx = 1;
				gbc_lblName.gridy = 3;
				panel.add(lblName, gbc_lblName);
			}
			{
				textName = new JTextField();
				textName.setEditable(false);
				GridBagConstraints gbc_textName = new GridBagConstraints();
				gbc_textName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textName.gridx = 3;
				gbc_textName.gridy = 3;
				panel.add(textName, gbc_textName);
				textName.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSearch = new JButton("Search");
				btnSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						searchTool();
					}
				});
				buttonPane.add(btnSearch);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelClicked();
					}
				});
				{
					JButton nextButton = new JButton("Next");
					nextButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							nextTool();
						}
					});
					nextButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
						}
					});
					buttonPane.add(nextButton);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		customerController = new CustomerController ();
		customerController.generateTestCustomers();
	}
	
	

	private void nextTool() {
		// TODO Auto-generated method stub
		if(textName.getText().isEmpty()) {
			errorCodeName();
		
			
		}
		else {
			NextLoan n = new NextLoan();
			n.setVisible(true);
		}
		
	}

	private void errorCodeName() {
		ErrorCodeName e = new ErrorCodeName();
		e.setVisible(true);
		// TODO Auto-generated method stub
		
	}

	private void searchTool() {
		// TODO Auto-generated method stub
		if(textTlf.getText().length()==(8)) {
			
			Customer customer = customerController.findCustomer(textTlf.getText()); 
			
				
		if(customer!= null) {
			textName.setText(customer.getName());
			selectedCustomer = customer;
		}
			
		}
		else { errorCodeTlf();
			
			
			
		}
	}

	private void errorCodeTlf() {
		// TODO Auto-generated method stub
		ErrorCodeTlf e = new ErrorCodeTlf();
		e.setVisible(true);
	}

	private void cancelClicked() {
		// TODO Auto-generated method stub
		this.dispose();
		this.setVisible(false);
		
	}

}
