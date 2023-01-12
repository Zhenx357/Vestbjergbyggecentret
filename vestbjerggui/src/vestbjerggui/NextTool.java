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
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class NextTool extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textBarcode;
	private JButton completeButton;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NextTool dialog = new NextTool();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NextTool() {
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
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblBarcode = new JLabel("Barcode:");
				GridBagConstraints gbc_lblBarcode = new GridBagConstraints();
				gbc_lblBarcode.insets = new Insets(0, 0, 5, 5);
				gbc_lblBarcode.gridx = 1;
				gbc_lblBarcode.gridy = 1;
				panel.add(lblBarcode, gbc_lblBarcode);
			}
			{
				textBarcode = new JTextField();
				textBarcode.setText("");
				GridBagConstraints gbc_textBarcode = new GridBagConstraints();
				gbc_textBarcode.gridwidth = 3;
				gbc_textBarcode.insets = new Insets(0, 0, 5, 0);
				gbc_textBarcode.fill = GridBagConstraints.HORIZONTAL;
				gbc_textBarcode.gridx = 2;
				gbc_textBarcode.gridy = 1;
				panel.add(textBarcode, gbc_textBarcode);
				textBarcode.setColumns(10);
			}
			{
				Object[][] data=
			          {
			              {"1","Man Utd","Luis Van gaal","86"},
			                  {"2","Chelsea","Jose Mourinho","84"},
			                  {"3","Man City","Manuele Pelegrini","82"},
			                  {"4","Arsenal","Arsene Wenger","80"},
			                  {"5","Liverpool","Brendan Rodgers","78"},
			          };
				String[] columnHeaders={"Position","Team","manager","Points"};
				table = new JTable(data, columnHeaders);
				table.setRowSelectionAllowed(true);
				GridBagConstraints gbc_table = new GridBagConstraints();
				gbc_table.gridwidth = 3;
				gbc_table.insets = new Insets(0, 0, 0, 5);
				gbc_table.fill = GridBagConstraints.BOTH;
				gbc_table.gridx = 2;
				gbc_table.gridy = 2;
				panel.add(table, gbc_table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				completeButton = new JButton("Complete");
				completeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						AddClicked();
					}
				});
				completeButton.setActionCommand("OK");
				buttonPane.add(completeButton);
				getRootPane().setDefaultButton(completeButton);
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

	private void cancelClicked() {
		// TODO Auto-generated method stub
		this.dispose();
		this.setVisible(false);
	}

	private void AddClicked() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		table.getModel().getValueAt(row, 0);
	}

}
