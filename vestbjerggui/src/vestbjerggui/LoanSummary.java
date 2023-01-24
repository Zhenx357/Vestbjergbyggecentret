package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buildingmerchant.controller.LoanController;
import buildingmerchant.model.Loan;
import buildingmerchant.model.LoanLine;
import buildingmerchant.model.Tool;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoanSummary extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DefaultListModel<Tool>dlm;
	DefaultListModel<LoanLine> loanLineDlm;
	private JList<LoanLine> loanLineList;
	private Loan currentLoan;
	private LoanController loanController;
	private JLabel totalPriceLabel;
	private JLabel customerLabel;
	private JDialog parent;
	
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public LoanSummary(JDialog parent, Loan loan ) {
		this.parent = parent;
		currentLoan = loan;
		loanController = new LoanController();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{68, 48, 0};
			gbl_panel.rowHeights = new int[]{14, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Customer: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				customerLabel = new JLabel(" ");
				GridBagConstraints gbc_customerLabel = new GridBagConstraints();
				gbc_customerLabel.gridx = 1;
				gbc_customerLabel.gridy = 0;
				panel.add(customerLabel, gbc_customerLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{107, 0, 173, 0, 43, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 41, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel_1 = new JLabel("Tools Loaned:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 0;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 7;
				gbc_scrollPane.gridwidth = 5;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 1;
				panel.add(scrollPane, gbc_scrollPane);
				{
					loanLineList = new JList<>();
					scrollPane.setViewportView(loanLineList);
				}
				loanLineList.setCellRenderer(new LoanLineCellRenderer());
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Price per day:");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_2.gridx = 3;
				gbc_lblNewLabel_2.gridy = 8;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				totalPriceLabel = new JLabel("0.0 kr.");
				GridBagConstraints gbc_totalPriceLabel = new GridBagConstraints();
				gbc_totalPriceLabel.anchor = GridBagConstraints.NORTH;
				gbc_totalPriceLabel.gridx = 4;
				gbc_totalPriceLabel.gridy = 8;
				panel.add(totalPriceLabel, gbc_totalPriceLabel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton confirmButton = new JButton("Confirm");
				confirmButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						confirmOrder();
					}
				});
				confirmButton.setActionCommand("OK");
				buttonPane.add(confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelClicked();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		displayLoanLines();
		displayCustomer();
	}



	private void cancelClicked() {
		this.parent.setVisible(true);
		this.dispose();
		// TODO Auto-generated method stub
		
	}



	protected void confirmOrder() {
		loanController.createLoan(currentLoan);		
		this.dispose();
		this.parent.dispose();
		
		// TODO Auto-generated method stub
	}
	
	public void displayLoanSummary() {
		loanLineDlm = new DefaultListModel<>();
		loanLineDlm.addAll (currentLoan.getLines());
		loanLineList.setModel(loanLineDlm);
	}
	public void displayLoanLines() {
		loanLineDlm = new DefaultListModel<>();
		loanLineDlm.addAll (currentLoan.getLines());
		loanLineList.setModel(loanLineDlm);
		totalPriceLabel.setText(currentLoan.getTotalPricePerDay() + " kr.");
		
	}
	
	private void displayCustomer() {
		customerLabel.setText(currentLoan.getCustomer().getName());
	}
	
	

}
