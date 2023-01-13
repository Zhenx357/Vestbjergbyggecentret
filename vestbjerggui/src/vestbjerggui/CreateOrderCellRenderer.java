package vestbjerggui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import buildingmerchant.model.Product;

public class CreateOrderCellRenderer implements ListCellRenderer<Product> {
	@Override
	public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index, boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value.getInfo(), index, isSelected, cellHasFocus);
	}
}
