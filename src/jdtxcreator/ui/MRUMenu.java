package jdtxcreator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedHashMap;

import javax.swing.JMenu;
import javax.swing.JPopupMenu.Separator;

import jdtxcreator.ui.chart.ChartFrameManager;

public class MRUMenu extends JMenu implements ActionListener
{
	private static final long serialVersionUID = -1788333841735011940L;

	/** The size of most recently used files can be hold. */
	public static final int MAX = 5;

	LinkedHashMap<File, MRUMenuItem> list;
	int index = - 1;
	Separator separator;

	public MRUMenu(String text)
	{
		super(text);

		list = new LinkedHashMap<File, MRUMenuItem>(MAX);
		separator = new Separator();
	}

	/**
	 * Get most recently used files.
	 * @return most recently used files.
	 */
	public File[] getMRUs()
	{
		File[] files = new File[list.size()];
		return list.keySet().toArray(files);
	}

	/**
	 * Add MRU to the end of the menu.
	 */
	public void addMRU()
	{
		index = getItemCount();
	}

	/**
	 * Add MRU <code>file</code> to this menu.
	 *
	 * @param file the MRU to be added.
	 */
	public void addMRU(File file)
	{
		if (index == -1) addMRU();
		if (file == null) return;

		MRUMenuItem item = null;

		if (list.containsKey(file))
		{
			item = list.remove(file);
		}
		else
		{
			item = new MRUMenuItem(file);
			item.addActionListener(this);
		}

		if (list.size() >= MAX) removeLastMRU();

		list.put(file, item);
		clearItems();
		addItems();
	}

	/**
	 * Add several MRU <code>paths</code> to this menu.
	 *
	 * @param paths the MRU to be added.
	 */
	public void addMRUs(String[] paths)
	{
		if (index == -1) addMRU();
		if (paths == null) return;

		for (int i = 0; i < paths.length; i++)
		{
			if (paths[i] == null) continue;
			addMRU(new File(paths[i]));
		}
	}

	/**
	 * Add several MRU <code>files</code> to this menu.
	 *
	 * @param files the MRU to be added.
	 */
	public void addMRU(File[] files)
	{
		if (index == -1) addMRU();
		if (files == null) return;

		for (int i = 0; i < files.length; i++)
		{
			addMRU(files[i]);
		}
	}

	/**
	 * Removes the MRU <code>item</code> from this menu.
	 *
	 * @param item the MRU to be removed
	 */
	public void removeMRU(MRUMenuItem item)
	{
		clearItems();
		list.remove(item.getFile());
		addItems();
	}

	/**
	 * Remove all MRU from this menu.
	 */
	public void clearMRU()
	{
		clearItems();
		list.clear();
	}

	/**
	 * Remove last MRU from this menu.
	 */
	private void removeLastMRU()
	{
		if (list.isEmpty()) return;

		File[] files = getMRUs();

		removeMRU(list.get(files[0]));
	}

	/**
	 * Add all files in <code>list</code> to this menu.
	 */
	private void addItems()
	{
		if (list.isEmpty()) return;

		File[] files = getMRUs();

		for (int i = 0, n = files.length; i < files.length; i++, n--)
		{
			File file = files[i];
			MRUMenuItem item = list.get(file);
			item.setText(n + ": " + file);
			item.setMnemonic('0' + n);
			add(item, index);
		}

		add(separator, index);
	}

	/**
	 * Remove all files in <code>list</code> from this menu.
	 */
	private void clearItems()
	{
		if (list.isEmpty()) return;

		File[] files = getMRUs();

		for (int i = 0; i < files.length; i++)
		{
			MRUMenuItem item = list.get(files[i]);
			remove(item);
		}
		remove(separator);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		if (obj instanceof MRUMenuItem)
		{
			MRUMenuItem item = (MRUMenuItem) obj;
			ChartFrameManager.getInstance().open(item.getFile());
			removeMRU(item);
		}
	}
}
