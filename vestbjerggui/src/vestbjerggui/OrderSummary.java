package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import buildingmerchant.controller.OrderController;
import buildingmerchant.model.Order;
import buildingmerchant.model.OrderLine;
import buildingmerchant.model.Product;


public class OrderSummary extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DefaultListModel<Product> dlm;
	DefaultListModel<OrderLine> orderLineDlm;
	private JList<OrderLine> orderLineList;
	private Order currentOrder;
	private OrderController orderController;
	private JLabel totalPriceLabel;
	private JLabel customerLabel;
	private JDialog parent;

	/**
	 * Create the dialog.
	 */
	public OrderSummary(JDialog parent, Order order) {
		this.parent = parent;
		currentOrder = order;
		orderController = new OrderController();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel customerTextLabel = new JLabel("Customer: ");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 0;
				gbc_lblSearch.gridy = 0;
				panel.add(customerTextLabel, gbc_lblSearch);
			}
			{
				customerLabel = new JLabel("");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 0);
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 1;
				gbc_lblSearch.gridy = 0;
				panel.add(customerLabel, gbc_lblSearch);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblSearch = new JLabel("Order lines: ");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
				gbc_lblSearch.anchor = GridBagConstraints.WEST;
				gbc_lblSearch.gridx = 2;
				gbc_lblSearch.gridy = 3;
				panel.add(lblSearch, gbc_lblSearch);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 2;
				gbc_scrollPane.gridy = 4;
				panel.add(scrollPane, gbc_scrollPane);
				{
					orderLineList = new JList<>();
					scrollPane.setViewportView(orderLineList);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Orderlines");
					scrollPane.setColumnHeaderView(lblNewLabel_1);
				}
			}
			{
				JLabel totalPriceLbl = new JLabel("Total price: ");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 2;
				gbc_lblSearch.gridy = 5;
				panel.add(totalPriceLbl, gbc_lblSearch);
			}
			{
				totalPriceLabel = new JLabel("0");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 0);
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 3;
				gbc_lblSearch.gridy = 5;
				panel.add(totalPriceLabel, gbc_lblSearch);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						confirmOrder();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						closeDialog();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		orderLineList.setCellRenderer(new OrderLineCellRenderer());
		displayOrderLines();
		displayCustomer();
	}
	

	private void confirmOrder() {
		orderController.createOrder(currentOrder);		
		this.dispose();
		this.parent.dispose();
		// TODO Auto-generated method stub
		
	}
	
	public void displayOrderSummary() {
		orderLineDlm = new DefaultListModel<>();
		orderLineDlm.addAll(currentOrder.getLines());
		orderLineList.setModel(orderLineDlm);
	}
	
	public void displayOrderLines() {
		orderLineDlm = new DefaultListModel<>();
		orderLineDlm.addAll(currentOrder.getLines());
		orderLineList.setModel(orderLineDlm);
		totalPriceLabel.setText(currentOrder.getTotalPrice() + " kr.");
		
	}
	
	private void displayCustomer() {
		customerLabel.setText(currentOrder.getCustomer().getName());
	}
	private void closeDialog() {
		this.parent.setVisible(true);
		this.dispose();
	}

}
