package vestbjerggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import buildingmerchant.controller.ToolController;
import buildingmerchant.model.Tool;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import buildingmerchant.model.Customer;
import buildingmerchant.model.Order;
import buildingmerchant.model.OrderLine;
import buildingmerchant.model.Product;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NextLoan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textBarcode;
	private JButton addButton;
	private JLabel totalPriceLabel;
	private ToolController toolController;
	private JList<Tool> listTool;
	private JList<OrderLine> orderLineList;
	DefaultListModel<Tool> dlm;
	DefaultListModel<OrderLine> orderLineDlm;
	Customer selectedCustomer;
	private Tool selectedTool;
	private Loan currentLoan;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			NextLoan dialog = new NextLoan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 * @param selectedCustomer2 
	 */
	public NextLoan() {
		
		setBounds(100, 100, 669, 567);
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
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblBarcode = new JLabel("Search:");
				GridBagConstraints gbc_lblBarcode = new GridBagConstraints();
				gbc_lblBarcode.insets = new Insets(0, 0, 5, 5);
				gbc_lblBarcode.gridx = 0;
				gbc_lblBarcode.gridy = 1;
				panel.add(lblBarcode, gbc_lblBarcode);
			}
			{
				textBarcode = new JTextField();
				textBarcode.setText("");
				GridBagConstraints gbc_textBarcode = new GridBagConstraints();
				gbc_textBarcode.gridwidth = 4;
				gbc_textBarcode.insets = new Insets(0, 0, 5, 0);
				gbc_textBarcode.fill = GridBagConstraints.HORIZONTAL;
				gbc_textBarcode.gridx = 1;
				gbc_textBarcode.gridy = 1;
				panel.add(textBarcode, gbc_textBarcode);
				textBarcode.setColumns(10);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 3;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 2;
				gbc_scrollPane.gridy = 2;
				panel.add(scrollPane, gbc_scrollPane);
				{
					listTool = new JList<>();
					scrollPane.setViewportView(listTool);
				}
				{
					JLabel lblNewLabel = new JLabel("Name, Barcode, Price per day");
					scrollPane.setColumnHeaderView(lblNewLabel);
				}
			}
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
				JLabel totalPriceLbl = new JLabel("Total price per day: ");
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
				addButton = new JButton("Add");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						AddClicked();
					}
				});
				{
					JButton btnComplete = new JButton("Complete");
					btnComplete.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							completeLoan();
						}
					});
					buttonPane.add(btnComplete);
				}
				addButton.setActionCommand("OK");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			}
			{
				JButton searchButton = new JButton("Search");
				searchButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						searchTool();
					}
				});
				searchButton.setActionCommand("OK");
				buttonPane.add(searchButton);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelClicked();
					}
				});
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				buttonPane.add(btnCancel);
			}
		}
		toolController = new ToolController();
		toolController.generateTestTools();
		CreateToolCellRenderer ctcr = new CreateToolCellRenderer();
		listTool.setCellRenderer(ctcr);
		displayTools();
		orderLineList.setCellRenderer(new OrderLineCellRenderer());
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 2;
			gbc_scrollPane.gridy = 2;
			
		}
		displayTools();
		
	}

	private void completeLoan() {
		// TODO Auto-generated method stub
		LoanSummary l = new LoanSummary(this, currentLoan);
		l.setVisible(true);
		this.setVisible(false);
	}


	private void cancelClicked() {
		this.dispose();
		this.setVisible(false);
		// TODO Auto-generated method stub
		
	}

	private void displayTools() {
		List<Tool> tools = toolController.getAllTools();
		dlm = new DefaultListModel<>();
		dlm.addAll(tools);
		listTool.setModel(dlm);
		listTool.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedTool = dlm.elementAt(listTool.getSelectedIndex());
				textBarcode.setText(selectedTool.getBarcode());
			}
		});
		// TODO Auto-generated method stub
		
	}
	
	private void searchTool() {
		String barcode = textBarcode.getText();
		for (int i = 0; i < dlm.size(); i++) {
			Tool tool = dlm.get(i);
			if (tool.getBarcode().equals(barcode)) {
				listTool.setSelectedIndex(i);
				return;
			}
		}
	}

	private void AddClicked() {
		
	}
	
	public void setLoan(Loan loan) {
		this.currentLoan = loan;
	}

}