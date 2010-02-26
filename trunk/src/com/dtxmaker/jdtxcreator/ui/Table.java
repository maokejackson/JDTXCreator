package com.dtxmaker.jdtxcreator.ui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.dtxmaker.jdtxcreator.Settings;

public class Table extends JXTable
{
	private static final long serialVersionUID = -7068369945098872249L;
	
	DefaultTableModel model;
	
	public Table()
	{
		setModel(model = new DefaultTableModel());
		setFont(Settings.TABLE_FONT);
		setRowHeight(14);
		setEditable(false);
//		setColumnControlVisible(true);
		setHorizontalScrollEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				// Select a row when right click
				if (SwingUtilities.isRightMouseButton(e))
				{
					Point p = e.getPoint();
					int rowNumber = rowAtPoint(p);
					ListSelectionModel model = getSelectionModel();
					model.setSelectionInterval(rowNumber, rowNumber);
				}
			}
		});
	}

	/**
	 * Adds a column to the model. The new column will have the identifier
	 * <code>columnName</code>, which may be <code>null</code>. This method is a
	 * cover for <code>addColumn(String, Vector)</code> which uses
	 * <code>null</code> as the data vector.
	 * 
	 * @param columnName the identifier of the column being added
	 */
	public void addColumn(String columnName)
	{
		model.addColumn(columnName);
	}
	
	/**
	 * Adds several columns to the model. The new column will have the
	 * identifier <code>columnName</code>, which may be <code>null</code>.
	 * 
	 * @param columnNames the identifier of the column being added
	 */
	public void addColumn(String[] columnNames)
	{
		for (int i = 0; i < columnNames.length; i++)
		{
			model.addColumn(columnNames[i]);
		}
	}

	/**
	 * Adds a column to the table. The new column will have the identifier
	 * <code>columnName</code>, which may be <code>null</code>.
	 * <code>columnData</code> is the optional vector of data for the column. If
	 * it is <code>null</code> the column is filled with <code>null</code>
	 * values. Otherwise, the new data will be added to all rows that already
	 * exist.
	 * 
	 * @param columnName the identifier of the column being added
	 * @param columnData optional data of the column being added
	 */
	public void addColumn(String columnName, Object columnData)
	{
		int size = getRowCount();
		Vector<Object> vector = new Vector<Object>(size);
		
		for (int i = 0; i < size; i++)
		{
			vector.add(columnData);
		}
		model.addColumn(columnName, vector);
	}
	
	/**
	 * Adds a column to the table. The new column will have the identifier
	 * <code>columnName</code>, which may be <code>null</code>.
	 * <code>columnData</code> is the optional vector of data for the column. If
	 * it is <code>null</code> the column is filled with <code>null</code>
	 * values. Otherwise, the new data will be added to table starting with the
	 * first element going to row 0, etc.
	 * 
	 * @param columnName the identifier of the column being added
	 * @param columnData optional data of the column being added
	 */
	public void addColumn(String columnName, Vector<?> columnData)
	{
		model.addColumn(columnName, columnData);
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
	
	public boolean isColumnVisible(int columnIndex)
	{
		int index = convertColumnIndexToView(columnIndex);
		if (index == -1) return false;		// column not found
		TableColumnExt column = getColumnExt(index);
		if (column == null) return false;	// column not found
		return column.isVisible();
	}
	
	public void setColumnVisible(int columnIndex, boolean visible)
	{
		int index = convertColumnIndexToView(columnIndex);
		if (index == -1) return;		// column not found
		TableColumnExt column = getColumnExt(index);
		if (column == null) return;		// column not found
		column.setVisible(visible);
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
		return getModel().getValueAt(row, column);
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
		getModel().setValueAt(aValue, row, column);
	}
}
