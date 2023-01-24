package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buildingmerchant.model.Loan;
import buildingmerchant.model.Tool;

public class AddClickedLoan extends JDialog {
	private Loan currentLoan;
	private NextLoan parentWindow;

	private final JPanel contentPanel = new JPanel();

	private Tool selectedTool;

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
	public AddClickedLoan(Tool selectedTool, Loan currentLoan, NextLoan nextLoan) {
		this.selectedTool = selectedTool;
		this.currentLoan = currentLoan;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	

}
