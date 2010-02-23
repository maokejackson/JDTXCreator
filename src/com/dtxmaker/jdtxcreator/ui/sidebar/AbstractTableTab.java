package com.dtxmaker.jdtxcreator.ui.sidebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.dtxmaker.jdtxcreator.ui.Table;
import com.dtxmaker.jdtxcreator.ui.ToolbarButton;

public abstract class AbstractTableTab<E> extends AbstractTab
{
	private static final long serialVersionUID = -3662888592737448796L;
	
	Table table;
	JToolBar toolbar;
	JButton btnUp, btnDown;
	
	public AbstractTableTab()
	{
		this(null);
	}
	
	public AbstractTableTab(String[] header)
	{
		setLayout(new BorderLayout());
		
		btnUp = new ToolbarButton("images/up.png");
		btnUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				moveUp();
			}
		});
		
		btnDown = new ToolbarButton("images/down.png");
		btnDown.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				moveDown();
			}
		});
		
		toolbar = new JToolBar();
		toolbar.setRollover(true);
		toolbar.setFloatable(false);
		toolbar.add(btnUp);
		toolbar.add(btnDown);
		
//		table = new Table(header);
		table = new Table();
		table.setSortable(false);
		table.setShowGrid(true);
		table.setGridColor(Color.LIGHT_GRAY);
//		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
					if (e.getClickCount() == 1)
					{
						mouseClickedEvent();
					}
					else if (e.getClickCount() >= 2)
					{
						openDialog();
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					// TODO: table context menu
				}
			}
		});
		table.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					openDialog();
				}
				else
				{
					super.keyPressed(e);
				}
			}
		});
		
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(toolbar, BorderLayout.SOUTH);
	}
	
	public int[] getColumnsWidth()
	{
		int size = table.getColumnCount();
		int[] data = new int[size];
		
		for (int i = 0; i < data.length; i++)
		{
			int width = table.getColumnWidht(i);
			if (width != -1) data[i] = width;
		}
		
		return data;
	}
	
	public int[] getColumnsViewIndex()
	{
		int size = table.getColumnCount();
		int[] data = new int[size];
		
		for (int i = 0; i < data.length; i++)
		{
			int index = table.convertColumnIndexToView(i);
			if (index != -1) data[i] = index;
		}
		
		return data;
	}
	
	public boolean[] getColumnsVisible()
	{
		int size = table.getColumnCount();
		boolean[] data = new boolean[size];
		
		for (int i = 0; i < data.length; i++)
		{
			data[i] = table.isColumnVisible(i);
		}
		
		return data;
	}
	
	private void openDialog()
	{
		E data = getSelectedData();
		if (data != null) openDialog(data);
	}
	
	@Override
	public void resetData()
	{
		int size = table.getRowCount();
		
		for (int i = 0; i < size; i++)
		{
			resetDataAt(i);
		}
	}
	
	/** Move row up. */
	private void moveUp()
	{
		int src = table.getSelectedRow();
		int target = src - 1;
		swapRow(src, target);
	}
	
	/** Move row down. */
	private void moveDown()
	{
		int src = table.getSelectedRow();
		int target = src + 1;
		swapRow(src, target);
	}
	
	/**
	 * Swap data between two rows.
	 * @param src index of source row.
	 * @param target index of target row.
	 */
	private void swapRow(int src, int target)
	{
		if (src == target) return;
		if (src < 0 || target < 0) return;
		int size = table.getRowCount() - 1;	// last index
		if (src > size || target > size) return;
		
		E dataSrc = getDataAt(src);
		fillDataAt(getDataAt(target), src);
		fillDataAt(dataSrc, target);
		
		table.setRowSelectionInterval(target, target);
	}
	
	/**
	 * Reset data at specified row.
	 * @param row row index.
	 */
	protected abstract void resetDataAt(int row);
	
	/**
	 * Get data at specified row.
	 * @param row row index.
	 * @return Data capture from specified row.
	 */
	protected abstract E getDataAt(int row);
	
	/**
	 * Get selected row data.
	 * @param row selected row index.
	 * @return Data capture from selected row.
	 */
	public E getSelectedData()
	{
		int row = table.getSelectedRow();
		if (row == -1) return null;
		return getDataAt(row);
	}
	
	/**
	 * Fill data at specified row.
	 * @param data data to be filled.
	 * @param row row index.
	 */
	protected abstract void fillDataAt(E data, int row);
	
	/** Mouse click event. */
	protected abstract void mouseClickedEvent();

	/**
	 * Open properties dialog.
	 * @param data data capture from selected row.
	 */
	protected abstract void openDialog(E data);
}
