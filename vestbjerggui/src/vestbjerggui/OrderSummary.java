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
import buildingmerchant.model.Loan;
import buildingmerchant.model.Order;
import buildingmerchant.model.OrderLine;
import buildingmerchant.model.Product;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Component;


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
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{70, 51, 52, 0};
			gbl_panel.rowHeights = new int[]{13, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel customerTextLabel = new JLabel("Customer: ");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
				gbc_lblSearch.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblSearch.gridx = 1;
				gbc_lblSearch.gridy = 0;
				panel.add(customerTextLabel);
			}
			{
				customerLabel = new JLabel("");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.insets = new Insets(0, 0, 5, 0);
				gbc_lblSearch.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblSearch.gridx = 2;
				gbc_lblSearch.gridy = 0;
				panel.add(customerLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{-110, 130, 0};
			gbl_panel.rowHeights = new int[]{24, 38, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{Double.MIN_VALUE, 1.0, 0.0};
			gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("OrderLines:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 5;
				gbc_scrollPane.gridwidth = 5;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 1;
				panel.add(scrollPane, gbc_scrollPane);
				{
					orderLineList = new JList<>();
					scrollPane.setViewportView(orderLineList);
				}
				orderLineList.setCellRenderer(new OrderLineCellRenderer());
			}
			{
				JLabel totalPriceLbl = new JLabel("Total price: ");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.fill = GridBagConstraints.VERTICAL;
				gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 3;
				gbc_lblSearch.gridy = 6;
				panel.add(totalPriceLbl, gbc_lblSearch);
			}
			{
				totalPriceLabel = new JLabel("0");
				GridBagConstraints gbc_lblSearch = new GridBagConstraints();
				gbc_lblSearch.anchor = GridBagConstraints.EAST;
				gbc_lblSearch.gridx = 4;
				gbc_lblSearch.gridy = 6;
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
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
