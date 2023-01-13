package vestbjerggui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import buildingmerchant.model.Tool;

public class CreateToolCellRenderer implements ListCellRenderer<Tool> {
	@Override
	public Component getListCellRendererComponent(JList<? extends Tool> list, Tool value, int index, boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value.getInfo(), index, isSelected, cellHasFocus);
	}
}
