package vestbjerggui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import buildingmerchant.model.LoanLine;

public class LoanLineCellRenderer implements ListCellRenderer<LoanLine> {
	@Override
	public Component getListCellRendererComponent(JList<? extends LoanLine> list, LoanLine value, int index, boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value.getInfo(), index, isSelected, cellHasFocus);
	}
}
