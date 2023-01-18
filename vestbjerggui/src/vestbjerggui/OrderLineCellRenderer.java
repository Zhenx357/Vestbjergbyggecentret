package vestbjerggui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import buildingmerchant.model.OrderLine;

public class OrderLineCellRenderer implements ListCellRenderer<OrderLine> {
	@Override
	public Component getListCellRendererComponent(JList<? extends OrderLine> list, OrderLine value, int index, boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value.getInfo(), index, isSelected, cellHasFocus);
	}
}
