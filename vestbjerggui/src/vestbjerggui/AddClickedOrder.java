package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buildingmerchant.model.OrderLine;


import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddClickedOrder extends JDialog {
	private JTextField textFieldQuantity;
	private OrderLine orderLine;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			AddClickedOrder dialog = new AddClickedOrder();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddClickedOrder() {
		setMinimumSize(new Dimension(100, 100));
		setMaximumSize(new Dimension(100, 100));
		setBounds(100, 100, 220, 150);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addProductButton = new JButton("Add product(s)");
				addProductButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						addProductQuantity();
					}
				});
				addProductButton.setActionCommand("OK");
				buttonPane.add(addProductButton);
				getRootPane().setDefaultButton(addProductButton);
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
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
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
	}

	private void addProductQuantity() {
		
	//	OrderLine orderLine = orderLine.setQuantity(textFieldQuantity.getText());
		// TODO Auto-generated method stub
		
	}

	private void cancelClicked() {
		this.dispose();
		this.setVisible(false);
		// TODO Auto-generated method stub
		
	}

}
