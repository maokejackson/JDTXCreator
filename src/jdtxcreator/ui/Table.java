package jdtxcreator.ui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import jdtxcreator.Settings;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.DefaultTableColumnModelExt;
import org.jdesktop.swingx.table.TableColumnExt;


public class Table extends JXTable
{
	private static final long serialVersionUID = -7068369945098872249L;
	
	private static final String WIDTH_SUFFIX = "_width";
	private static final String INDEX_SUFFIX = "_index";
	private static final String VISIBILITY_SUFFIX = "_visibility";
	
	DefaultTableModel model;
	TableColumnModel columnModel;
	
	String key;
	
	public Table(Object[] columnNames)
	{
		this(columnNames, null);
	}
	
	public Table(Object[] columnNames, String key)
	{
		this.key = key;
		setModel(model = new DefaultTableModel());
		setColumnModel(columnModel = new TableColumnModel());
		createDefaultColumnsFromModel();
		setColumnNames(columnNames);
		setFont(Settings.TABLE_FONT);
		setRowHeight(14);
		setEditable(false);
		setColumnControlVisible(true);
		setHorizontalScrollEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				// Select a row on right click
				if (SwingUtilities.isRightMouseButton(e))
				{
					Point p = e.getPoint();
					int rowIndex = rowAtPoint(p);
					setSelectedRow(rowIndex);
				}
			}
		});
		loadSettings();
	}

	/**
	 * Adds a row to the end of the model. The new row will contain
	 * <code>null</code> values unless rowData is specified. Notification of the
	 * row being added will be generated.
	 * 
	 * @param rowData optional data of the row being added
	 */
	public void addRow(Object[] rowData)
	{
		model.addRow(rowData);
	}

	/**
	 * Adds a row to the end of the model. The new row will contain
	 * <code>null</code> values unless rowData is specified. Notification of the
	 * row being added will be generated.
	 * 
	 * @param rowData optional data of the row being added
	 */
	public void addRow(Vector<?> rowData)
	{
		model.addRow(rowData);
	}

	/**
	 * Removes the row at <code>rowIndex</code> from the model.
	 * 
	 * @param rowIndex row index in model.
	 */
	public void removeRow(int rowIndex)
	{
		model.removeRow(rowIndex);
	}

	/**
	 * The selected row will go upwards.
	 */
	public void moveSelectedRowUp()
	{
		int index = getSelectedModelRow();
		if (index <= 0) return;
		model.moveRow(index, index, index - 1);
		setSelectedRow(index - 1);
	}

	/**
	 * The selected row will go downwards.
	 */
	public void moveSelectedRowDown()
	{
		int index = getSelectedModelRow();
		if (index < 0) return;
		if (index == getRowCount() - 1) return;
		model.moveRow(index, index, index + 1);
		setSelectedRow(index + 1);
	}

	/**
	 * Remove all elements from data vector in the model.
	 */
	public void removeAllElements()
	{
		model.getDataVector().removeAllElements();
	}

	/**
	 * Replaces the column identifiers in the model. If the number of
	 * <code>newIdentifiers</code> is greater than the current number of
	 * columns, new columns are added to the end of each row in the model. If
	 * the number of <code>newIdentifiers</code> is less than the current number
	 * of columns, all the extra columns at the end of a row are discarded.
	 * 
	 * @param columnNames array of column identifiers. If <code>null</code>,
	 *            set the model to zero columns
	 */
	public void setColumnNames(Object[] columnNames)
	{
		model.setColumnIdentifiers(columnNames);
	}
	/**
	 * Returns the width of the specified column.
	 * 
	 * @param columnIndex the column whose width to get
	 * @return The specified column width.
	 */
	public int getColumnWidht(int columnIndex)
	{
		int index = convertColumnIndexToView(columnIndex);
		if (index == -1) return -1;		// column not found
		TableColumnExt column = getColumnExt(index);
		if (column == null) return -1;	// column not found
		return column.getWidth();
	}

	/**
	 * Set specified column width.
	 * 
	 * @param columnIndex the column whose width is to be set
	 * @param width new width
	 */
	public void setColumnWidth(int columnIndex, int width)
	{
		int index = convertColumnIndexToView(columnIndex);
		if (index == -1) return;		// column not found
		TableColumnExt column = getColumnExt(index);
		if (column == null) return;		// column not found
		column.setPreferredWidth(width);
	}

	/**
	 * Moves the column <code>columnIndex</code> to the position currently
	 * occupied by the column <code>newIndex</code> in the view. The old column
	 * at <code>newIndex</code> is shifted left or right to make room.
	 * 
	 * @param columnIndex the index of column to be moved
	 * @param newIndex the new index of the column
	 */
	public void setColumnViewIndex(int columnIndex, int newIndex)
	{
		moveColumn(convertColumnIndexToView(columnIndex), newIndex);
	}

	/**
	 * Returns the visible property of a column at <code>columnIndex</code>.
	 * 
	 * @param columnIndex the index of the column in the model
	 * @return boolean indicating whether or not this column is visible in the
	 *         table
	 */
	public boolean isColumnVisible(int columnIndex)
	{
		int index = convertColumnIndexToView(columnIndex);
		if (index == -1) return false;		// column not found
		TableColumnExt column = getColumnExt(index);
		if (column == null) return false;	// column not found
		return column.isVisible();
	}

	/**
	 * Set the column visibility at <code>columnIndex</code>.
	 * 
	 * @param visible boolean indicating whether or not this column is visible
	 *            in the table
	 * @param columnIndex the index of the column in the model
	 */
	public void setColumnVisibleAt(boolean visible, int columnIndex)
	{
		int index = convertColumnIndexToView(columnIndex);
		if (index == -1) return;		// column not found
		TableColumnExt column = getColumnExt(index);
		if (column == null) return;		// column not found
		column.setVisible(visible);
	}

	/**
	 * Returns the index of the first selected row, -1 if no row is selected.
	 * 
	 * @return the index of the first selected row in the model
	 */
	public int getSelectedModelRow()
	{
		int index = getSelectedRow();
		if (index < 0) return -1;
		return convertRowIndexToModel(index);
	}

	/**
	 * Select specified row.
	 * 
	 * @param rowIndex the index of the row
	 */
	private void setSelectedRow(int rowIndex)
	{
		getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
	}

	/**
	 * Select specified row.
	 * 
	 * @param rowIndex the index of the row in the model
	 */
	public void setSelectedModelRow(int rowIndex)
	{
		setSelectedRow(convertRowIndexToView(rowIndex));
	}

	/**
	 * Returns the value for the cell at <code>columnIndex</code> and
	 * <code>rowIndex</code>.
	 * 
	 * @param row the row whose value is to be queried
	 * @param column the column whose value is to be queried
	 * @return the value Object at the specified cell
	 */
	public Object getModelValueAt(int row, int column)
	{
		return model.getValueAt(row, column);
	}

	/**
	 * Sets the value in the cell at <code>columnIndex</code> and
	 * <code>rowIndex</code> to <code>aValue</code>.
	 * 
	 * @param aValue the new value
	 * @param row the row whose value is to be changed
	 * @param column the column whose value is to be changed
	 */
	public void setModelValueAt(Object aValue, int row, int column)
	{
		model.setValueAt(aValue, row, column);
	}

	/**
	 * Sets the <code>TableCellRenderer</code> used by <code>JTable</code> to
	 * draw individual values for this column.
	 * 
	 * @param cellRenderer the new cellRenderer
	 * @param columnIndex column index in model
	 */
	public void setCellRendererAt(TableCellRenderer cellRenderer, int columnIndex)
	{
		columnIndex = convertColumnIndexToView(columnIndex);
		if (columnIndex < 0) return;
		getColumnExt(columnIndex).setCellRenderer(cellRenderer);
	}

	private void loadSettings()
	{
		if (key == null) return;
		columnModel.removeColumnModelListener(this);
		loadIndex();
		loadWidth();
		loadVisibility();
		columnModel.addColumnModelListener(this);
	}

	private void loadIndex()
	{
		Vector<Integer> data = Settings.getIntegerVector(key + INDEX_SUFFIX);

		for (int i = 0; i < data.size(); i++)
		{
			if (data.elementAt(i) == null) continue;
			int from = convertColumnIndexToView(i);
			int to = data.elementAt(i);
			if (from < 0) continue;
			moveColumn(from, to);
		}
	}

	private void loadWidth()
	{
		Vector<Integer> data = Settings.getIntegerVector(key + WIDTH_SUFFIX);

		for (int i = 0; i < data.size(); i++)
		{
			if (data.elementAt(i) == null) continue;
			int width = data.elementAt(i);
			int index = convertColumnIndexToView(i);
			if (index < 0) continue;
			columnModel.getColumn(index).setPreferredWidth(width);
		}
	}

	private void loadVisibility()
	{
		Vector<Boolean> data = Settings.getBooleanVector(key + VISIBILITY_SUFFIX);

		for (int i = 0; i < data.size(); i++)
		{
			if (data.elementAt(i) == null) continue;
			boolean visible = data.elementAt(i);
			int index = convertColumnIndexToView(i);
			if (index < 0) continue;
			columnModel.getColumnExt(index).setVisible(visible);
		}
	}

	private void saveIndex()
	{
		if (key == null) return;

		int count = columnModel.getColumnCount(true);
		Vector<Integer> data = new Vector<Integer>(count);

		for (int i = 0; i < count; i++)
		{
			int index = convertColumnIndexToView(i);
			if (index < 0) data.add(null);
			else
				data.add(index);
		}

		Settings.setIntegerVector(key + INDEX_SUFFIX, data);
	}

	private void saveWidth()
	{
		if (key == null) return;

		int count = columnModel.getColumnCount(true);
		Vector<Integer> data = new Vector<Integer>(count);
		List<TableColumn> columns = columnModel.getColumns(true);

		for (int i = 0; i < count; i++)
		{
			int width = columns.get(i).getPreferredWidth();
			if (width < 0) data.add(null);
			else
				data.add(width);
		}

		Settings.setIntegerVector(key + WIDTH_SUFFIX, data);
	}

	private void saveVisibility()
	{
		if (key == null) return;

		int count = columnModel.getColumnCount(true);
		Vector<Boolean> data = new Vector<Boolean>(count);
		List<TableColumn> columns = columnModel.getColumns(true);

		for (int i = 0; i < count; i++)
		{
			if (columns.get(i) instanceof TableColumnExt)
			{
				boolean visible = ((TableColumnExt) columns.get(i)).isVisible();
				data.add(visible);
			}
		}

		Settings.setBooleanVector(key + VISIBILITY_SUFFIX, data);
	}

	@Override
	public void columnMarginChanged(ChangeEvent e)
	{
		super.columnMarginChanged(e);
		saveWidth();
	}

	@Override
	public void columnMoved(TableColumnModelEvent e)
	{
		super.columnMoved(e);
		saveIndex();
	}

	private class TableColumnModel extends DefaultTableColumnModelExt
	{
		private static final long serialVersionUID = -7528222334429131473L;

		@Override
		public boolean isAddedFromInvisibleEvent(int newIndex)
		{
			saveVisibility();
			return super.isAddedFromInvisibleEvent(newIndex);
		}

		@Override
		public boolean isRemovedToInvisibleEvent(int oldIndex)
		{
			saveVisibility();
			return super.isRemovedToInvisibleEvent(oldIndex);
		}
	}

	public static void main(String[] args)
	{
		Table table = new Table(new String[] { "A", "B", "C", "D", "E" }, "test");
		table.addRow(new Object[] { 1, "A", "Z" });
		table.addRow(new Object[] { 99, "B", "Y" });
		table.addRow(new Object[] { 5, "C", "X" });

		JScrollPane scrollPane = new JScrollPane(table);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				Settings.store();
				System.exit(0);
			}
		});
		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
