package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buildingmerchant.model.Loan;
import buildingmerchant.model.LoanLine;
import buildingmerchant.model.OrderLine;
import buildingmerchant.model.Tool;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddClickedLoan extends JDialog {
	private Loan currentLoan;
	private NextLoan parentWindow;
	private LoanLine loanLine;

	private final JPanel contentPanel = new JPanel();

	private Tool selectedTool;
	private JTextField textFieldQuantity;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddClickedLoan dialog = new AddClickedLoan(null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param nextLoan 
	 * @param string 
	 * @param selectedTool 
	 */
	public AddClickedLoan(Tool selectedTool, Loan currentLoan, NextLoan parent) {
		this.selectedTool = selectedTool;
		this.currentLoan = currentLoan;
		this.parentWindow = parent;
		
		setBounds(100, 100, 220, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Quantity:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				textFieldQuantity = new JTextField();
				GridBagConstraints gbc_textFieldQuantity = new GridBagConstraints();
				gbc_textFieldQuantity.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldQuantity.gridx = 1;
				gbc_textFieldQuantity.gridy = 0;
				panel.add(textFieldQuantity, gbc_textFieldQuantity);
				textFieldQuantity.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addToolButton = new JButton("Add tool(s)");
				addToolButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						addToolQuantity();
					}
				});
				addToolButton.setActionCommand("OK");
				buttonPane.add(addToolButton);
				getRootPane().setDefaultButton(addToolButton);
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
	}

	protected void cancelClicked() {
		this.dispose();
		this.setVisible(false);
		// TODO Auto-generated method stub
		
	}

	protected void addToolQuantity() {
		String quantityString = textFieldQuantity.getText();
		Integer quantity = Integer.parseInt(quantityString);
		loanLine = new LoanLine(selectedTool, quantity);
		currentLoan.addLoanLine(loanLine);
		parentWindow.setLoan(currentLoan);
		parentWindow.displayLoanLines();
		this.setVisible(false);
		this.dispose();
		// TODO Auto-generated method stub
		
	}

	

}
