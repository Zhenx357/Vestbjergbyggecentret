package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JTable;

public class NextOrder extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearchordertextfield;
	private JTable productTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NextOrder dialog = new NextOrder();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NextOrder() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Create Order");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel_1 = new JLabel("Search:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				txtSearchordertextfield = new JTextField();
				GridBagConstraints gbc_txtSearchordertextfield = new GridBagConstraints();
				gbc_txtSearchordertextfield.insets = new Insets(0, 0, 5, 0);
				gbc_txtSearchordertextfield.gridwidth = 2;
				gbc_txtSearchordertextfield.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtSearchordertextfield.gridx = 1;
				gbc_txtSearchordertextfield.gridy = 1;
				panel.add(txtSearchordertextfield, gbc_txtSearchordertextfield);
				txtSearchordertextfield.setColumns(10);
			}
			{
				productTable = new JTable();
				GridBagConstraints gbc_productTable = new GridBagConstraints();
				gbc_productTable.gridwidth = 3;
				gbc_productTable.fill = GridBagConstraints.BOTH;
				gbc_productTable.gridx = 0;
				gbc_productTable.gridy = 2;
				panel.add(productTable, gbc_productTable);
			}
		}
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
