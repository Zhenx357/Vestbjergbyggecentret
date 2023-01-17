package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import buildingmerchant.controller.ProductController;
import buildingmerchant.model.Product;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class NextOrder extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearchordertextfield;
	private ProductController productController;
	private JList<Product> list;
	DefaultListModel<Product> dlm;
	private Product selectedProduct;

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
				JLabel lblSearch = new JLabel("Search:");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 0;
				gbc_lblSearch.gridy = 1;
				panel.add(lblSearch, gbc_lblSearch);
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
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 2;
				gbc_scrollPane.gridy = 2;
				panel.add(scrollPane, gbc_scrollPane);
				{
					list = new JList<>();
					scrollPane.setViewportView(list);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Name                                           Barcode                                                          Price");
					scrollPane.setColumnHeaderView(lblNewLabel_1);
				}
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
				JButton searchButton = new JButton("Search");
				searchButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						searchProduct();
					}
				});
				searchButton.setActionCommand("OK");
				buttonPane.add(searchButton);
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
		productController = new ProductController();
		productController.generateTestProducts();
		CreateOrderCellRenderer cocr = new CreateOrderCellRenderer();
		list.setCellRenderer(cocr);
		displayProducts();
	}

	private void cancelClicked() {
		// TODO Auto-generated method stub
		this.dispose();
		this.setVisible(false);
	}
	
	private void searchProduct() {
		String barcode = txtSearchordertextfield.getText();
		for (int i = 0; i < dlm.size(); i++) {
			Product product = dlm.get(i);
			if (product.getBarcode().equals(barcode)) {
				list.setSelectedIndex(i);
				return;
			}
		}
	}
	
	private void displayProducts() {
		List<Product> products = productController.getAllProducts();
		dlm = new DefaultListModel<>();
		dlm.addAll(products);
		list.setModel(dlm);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedProduct = dlm.elementAt(list.getSelectedIndex());
				txtSearchordertextfield.setText(selectedProduct.getBarcode());
			}
		});
	}

}
