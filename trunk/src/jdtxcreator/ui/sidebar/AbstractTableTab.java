package jdtxcreator.ui.sidebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import jdtxcreator.Language;
import jdtxcreator.ui.JExtTable;
import jdtxcreator.ui.ToolbarButton;

public abstract class AbstractTableTab<E> extends AbstractTab
{
	private static final long serialVersionUID = -3662888592737448796L;

	JExtTable table;
	JToolBar toolbar;
	JButton btnAdd, btnRemove;
	JButton btnUp, btnDown;

	public AbstractTableTab(String[] header, String key)
	{
		setLayout(new BorderLayout());

		btnAdd = new ToolbarButton(new AddAction());
		btnRemove = new ToolbarButton(new RemoveAction());
		btnUp = new ToolbarButton(new MoveUpAction());
		btnDown = new ToolbarButton(new MoveDownAction());

		toolbar = new JToolBar();
		toolbar.setRollover(true);
		toolbar.setFloatable(false);
		toolbar.add(btnAdd);
		toolbar.add(btnRemove);
		toolbar.addSeparator();
		toolbar.add(btnUp);
		toolbar.add(btnDown);

		table = new JExtTable(header, key);
//		table.setSortable(false);
		table.setRowHeight(16);
		table.setShowGrid(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.addMouseListener(new TableMouseListener());
		table.setEnterAction(new EditAction());

		add(new JScrollPane(table), BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
	}

	@Override
	public void setEnabled(boolean enabled)
	{
//		table.setEnabled(enabled);
		btnAdd.setEnabled(enabled);
		btnRemove.setEnabled(enabled && getSelectedRow() >= 0);
		btnUp.setEnabled(enabled);
		btnDown.setEnabled(enabled);
	}

	/**
	 * Edit selected item.
	 */
	private void edit()
	{
		E data = getSelectedData();
		if (data != null) edit(data);
	}

	@Override
	public void clearData()
	{
		table.removeAllElements();
		table.repaint();
		table.resetSortOrder();
		btnRemove.setEnabled(false);
	}

	/**
	 * Returns the model index of the first selected row, -1 if no row is
	 * selected.
	 *
	 * @return the model index of the first selected row
	 */
	public int getSelectedRow()
	{
		return table.getSelectedModelRow();
	}

	/**
	 * Add new item.
	 */
	protected abstract void add();

	/**
	 * Edit an item.
	 *
	 * @param data data of selected row.
	 */
	protected abstract void edit(E data);

	/**
	 * Remove selected items..
	 */
	protected void remove()
	{
		int index = getSelectedRow();
		if (index < 0) return;
		table.removeRow(index);
	}

	/**
	 * Get dtx data.
	 *
	 * @return dtx data.
	 */
	protected abstract Vector<E> getData();

	@Override
	protected void fillData()
	{
		Vector<E> vector = getData();

		if (vector == null) return;

		for (int i = 0; i < vector.size(); i++)
		{
			addData(vector.elementAt(i));
		}
	}

	/**
	 * Add new data in table.
	 *
	 * @param data the data to be added.
	 */
	protected void addData(E data)
	{
		table.addRow(convertToArray(data));
//		table.addRow(new Object[0]);
//		fillDataAt(data, table.getRowCount() - 1);
	}

	/**
	 * Fill data at specified row.
	 *
	 * @param data data to be filled.
	 * @param row row index.
	 */
	protected abstract void fillDataAt(E data, int row);

	/**
	 * Get data at specified row.
	 *
	 * @param row row index.
	 * @return Data capture from specified row.
	 */
	protected abstract E getDataAt(int row);

	/**
	 * Get selected row data.
	 *
	 * @param row selected row index.
	 * @return Data capture from selected row.
	 */
	public E getSelectedData()
	{
		int row = getSelectedRow();
		if (row == -1) return null;
		return getDataAt(row);
	}

	/**
	 * Convert data to fetch table row.
	 *
	 * @param data the data to be converted.
	 * @return an <code>Object</code> array.
	 */
	protected abstract Object[] convertToArray(E data);

	/** Mouse click event. */
	protected abstract void mouseClickedEvent();

	class AddAction extends AbstractAction
	{
        private static final long serialVersionUID = -8652338815891420501L;

        public AddAction()
        {
	        putValue(Action.SMALL_ICON, new ImageIcon("images/add.png"));
            setToolTipText(Language.get("table.toolbar.tooltip.add"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            add();
        }
	}

    class EditAction extends AbstractAction
    {
        private static final long serialVersionUID = 319099964302181363L;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            edit();
        }
    }

    class RemoveAction extends AbstractAction
    {
        private static final long serialVersionUID = -3615466039740332707L;

        public RemoveAction()
        {
            putValue(Action.SMALL_ICON, new ImageIcon("images/remove.png"));
            setToolTipText(Language.get("table.toolbar.tooltip.remove"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            remove();
        }
    }

    class MoveUpAction extends AbstractAction
    {
        private static final long serialVersionUID = -5024528207267594577L;

        public MoveUpAction()
        {
            putValue(Action.SMALL_ICON, new ImageIcon("images/up.png"));
            setToolTipText(Language.get("table.toolbar.tooltip.up"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            table.moveSelectedRowUp();
        }
    }

    class MoveDownAction extends AbstractAction
    {
        private static final long serialVersionUID = -7625131093116222778L;

        public MoveDownAction()
        {
            putValue(Action.SMALL_ICON, new ImageIcon("images/down.png"));
            setToolTipText(Language.get("table.toolbar.tooltip.down"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            table.moveSelectedRowDown();
        }
    }

    class TableMouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            btnRemove.setEnabled(table.getSelectedRow() >= 0);

            if (SwingUtilities.isLeftMouseButton(e))
            {
                if (e.getClickCount() == 1)
                {
                    mouseClickedEvent();
                }
                else if (e.getClickCount() >= 2)
                {
                    edit();
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
    }
}
