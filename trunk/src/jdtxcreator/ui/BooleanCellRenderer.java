package jdtxcreator.ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BooleanCellRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 3777731749404780629L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component com =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (com instanceof JLabel)
		{
			// Display 'o' when value is true. Otherwise, leave it blank.
			JLabel label = (JLabel) com;
			Boolean bgm = (Boolean) value;
			String text = (bgm == null || !bgm.booleanValue()) ? "" : "o";
			label.setText(text);
		}
		
		return com;
	}
}
