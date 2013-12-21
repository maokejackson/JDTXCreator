package jdtxcreator.ui.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import jdtxcreator.data.DTX;
import jdtxcreator.io.DTXReader;
import jdtxcreator.ui.FileChooserFactory;
import jdtxcreator.ui.main.Main;
import jdtxcreator.ui.main.Menu;
import jdtxcreator.ui.main.Toolbar;
import jdtxcreator.ui.sidebar.SideBar;

public class ChartFrameManager extends JDesktopPane
{
	private static final long serialVersionUID = 898342948470015613L;

	private static ChartFrameManager instance;

	private Point lastLocation;

	/** <code>true</code> for edit mode, <code>false</code> for select mode. */
	boolean mode = true;

	public static ChartFrameManager getInstance()
	{
		if (instance == null) instance = new ChartFrameManager();
		return instance;
	}

	private ChartFrameManager()
	{
		setBackground(Color.GRAY);
		setDragMode(OUTLINE_DRAG_MODE);
		setMode(mode);

		lastLocation = new Point();
	}

	public File getWorkingDir()
	{
		ChartFrame chart = getSelectedChart();
		File programDir = new File("").getAbsoluteFile();

		if (chart == null) return programDir;
		if (chart.getFile() == null) return programDir;
		return chart.getFile().getParentFile();
	}

	public boolean isEditMode()
	{
		return mode;
	}

	public void switchMode()
	{
		setMode(!mode);
	}

	public void setMode(boolean mode)
	{
		this.mode = mode;
		Toolbar.getInstance().setMode(mode);
		Menu.getInstance().setMode(mode);
	}

	/**
	 * Pop a file chooser dialog. User can select multiple files.
	 */
	public void open()
	{
		int answer = FileChooserFactory.openDTXs();

		if (answer == JFileChooser.CANCEL_OPTION) return;
		File[] files = FileChooserFactory.getSelectedFiles();
		open(files);
	}

	/**
	 * Open specified file.
	 * @param file the file to be opened.
	 */
	public void open(File file)
	{
		open(new File[] { file });
	}

	/**
	 * Open multiple files.
	 * @param files an array of files.
	 */
	public void open(File[] files)
	{
		if (files == null) return;

		for (int i = 0; i < files.length; i++)
		{
			DTX dtx = DTXReader.read(files[i]);

			if (dtx == null) continue;

			ChartFrame chart = new ChartFrame(dtx);
			chart.setFile(files[i]);
			addChart(chart);
		}
	}

	public void save()
	{
		ChartFrame chart = getSelectedChart();

		if (chart == null) return;

		chart.save();
	}

	public void saveAs()
	{
		ChartFrame chart = getSelectedChart();

		if (chart == null) return;

		chart.saveAs(null);
	}

	public void saveAll()
	{
		ChartFrame[] charts = getAllCharts();

		if (charts == null) return;

		for (int i = 0; i < charts.length; i++)
		{
			charts[i].save();
		}
	}

	/**
	 * Close selected chart.
	 */
	public void close()
	{
		close(getSelectedChart());
	}

	/**
	 * Close specified chart
	 *
	 * @param chart the chart to be closed.
	 * @return <code>true</code> if chart is closed.
	 */
	public boolean close(ChartFrame chart)
	{
		if (chart == null) return true;

		if (chart.isModified())
		{
			String title = "Confirm";
			String message = "Save changes to file?\n\n" + chart.getTitle();
			int answer = JOptionPane.showConfirmDialog(Main.getInstance(), message, title, JOptionPane.YES_NO_CANCEL_OPTION);

			if (answer == JOptionPane.CANCEL_OPTION) return false;
			else if (answer == JOptionPane.CLOSED_OPTION) return false;
			else if (answer == JOptionPane.YES_OPTION) chart.save();
		}

		Menu.getInstance().removeFrame(chart);
		chart.dispose();
		SideBar.getInstance().resetData();
		selectLastChart();
		Menu.getInstance().addFile(chart.file);

		if (isEmpty()) SideBar.getInstance().setEnabled(false);

		return true;
	}

	/**
	 * Close all charts. If content is modified, will ask user whether to save
	 * it.
	 *
	 * @return <code>false</code> if cancel is clicked when a confirm to close
	 *         dialog show.
	 */
	public boolean closeAll()
	{
		ChartFrame[] charts = getAllCharts();

		if (charts != null)
		{
			for (int i = 0; i < charts.length; i++)
			{
				if (!close(charts[i])) return false; // if user click cancel
			}
		}

		return true;
	}

	public ChartFrame getSelectedChart()
	{
		JInternalFrame frame = getSelectedFrame();
		if (frame == null) return null;
		return (ChartFrame) frame;
	}

	public ChartFrame[] getAllCharts()
	{
		JInternalFrame[] frames = getAllFrames();
		if (frames == null || frames.length == 0) return null;
		ChartFrame[] charts = new ChartFrame[frames.length];

		for (int i = 0; i < charts.length; i++)
		{
			charts[i] = (ChartFrame) frames[i];
		}

		return charts;
	}

	public void newChart()
	{
		addChart(new ChartFrame());
	}

	public void addChart(ChartFrame chart)
	{
		add(chart);
		putChart(chart, lastLocation);
		Menu.getInstance().addFrame(chart);
		SideBar.getInstance().setEnabled(true);
	}

	/**
	 * Returns <code>true</code> if this manager contains no internal frames.
	 *
	 * @return <code>true</code> if this manager contains no internal frames.
	 */
	public boolean isEmpty()
	{
		JInternalFrame[] frames = getAllFrames();
		return (frames == null) || (frames.length == 0);
	}

	private void putChart(JInternalFrame frame, Point location)
	{
		putChart(frame, location, true);
	}

	private void putChart(JInternalFrame frame, Point location, boolean select)
	{
		Dimension availableSize = getSize();
		frame.setLocation(location);
		frame.setSize((int) (availableSize.width * 0.7), (int) (availableSize.height * 0.7));

		try
		{
			if (select) frame.setSelected(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		location.x += 20;
		location.y += 20;

		Dimension size = frame.getSize();

		if (location.x + size.width >= availableSize.width || location.y + size.height >= availableSize.height)
		{
			location.x = 0;
			location.y = 0;
		}
	}

	void selectLastChart()
	{
		JInternalFrame[] frames = getAllFrames();

		if (frames == null || frames.length == 0) return;

		try
		{
			frames[0].setSelected(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Cascade windows.
	 */
	public void cascade()
	{
		JInternalFrame[] frames = getAllFrames();

		if (frames == null) return;

		Point location = new Point();

		for (int i = frames.length - 1; i >= 0; i--)
		{
			JInternalFrame frame = frames[i];

			try
			{
				if (frame.isClosed()) continue;
				if (frame.isIcon()) frame.setIcon(false);
				if (frame.isMaximum()) frame.setMaximum(false);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			putChart(frame, location, false);
		}
	}

	/**
	 * Tile windows horizontally.
	 */
	public void tileHorizontally()
	{
		JInternalFrame[] frames = getAllFrames();

		if (frames == null) return;

		int count = frames.length;
		if (count == 0) return;
		if (count > 8)
		{
			tile();
			return;
		}

		int cols = 2;
		int rows = count / 2;
		if (rows * cols < count)
		{
			rows++;
		}

		// Define some initial values for size & location.
		Dimension size = getSize();

		int w = size.width / cols;
		int h = size.height / rows;
		int x = 0;
		int y = 0;

		// Iterate over the frames, deiconifying any iconified frames and then
		// relocating & resizing each.
		for (int i = 0; i < cols; i++)
		{
			if (i == 1 && count % 2 == 1)
			{
				// If number of frames is odd, recalculate height.
				h = size.height / (count - rows);
			}
			for (int j = 0; j < rows && ((i * rows) + j < count); j++)
			{
				JInternalFrame frame = frames[(i * rows) + j];

				try
				{
					if (frame.isClosed()) continue;
					if (frame.isIcon()) frame.setIcon(false);
					if (frame.isMaximum()) frame.setMaximum(false);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				frame.setSize(w, h);
				frame.setLocation(x, y);
				y += h; // start the next row
			}
			x += w;
			y = 0;
		}
	}

	/**
	 * Tile windows vertically.
	 */
	public void tileVertically()
	{
		JInternalFrame[] frames = getAllFrames();

		if (frames == null) return;

		int count = frames.length;
		if (count == 0) return;
		if (count > 8)
		{
			tile();
			return;
		}

		int rows = 2;
		int cols = count / 2;
		if (rows * cols < count)
		{
			cols++;
		}

		// Define some initial values for size & location.
		Dimension size = getSize();

		int w = size.width / cols;
		int h = size.height / rows;
		int x = 0;
		int y = 0;

		// Iterate over the frames, deiconifying any iconified frames and then
		// relocating & resizing each.
		for (int i = 0; i < rows; i++)
		{
			if (i == 1 && count % 2 == 1)
			{
				// If number of frames is odd, recalculate width.
				w = size.width / (count - cols);
			}
			for (int j = 0; j < cols && ((i * cols) + j < count); j++)
			{
				JInternalFrame frame = frames[(i * cols) + j];

				try
				{
					if (frame.isClosed()) continue;
					if (frame.isIcon()) frame.setIcon(false);
					if (frame.isMaximum()) frame.setMaximum(false);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				frame.setSize(w, h);
				frame.setLocation(x, y);
				x += w;
			}
			y += h; // start the next row
			x = 0;
		}
	}

	public void tile()
	{
		JInternalFrame[] frames = getAllFrames();

		if (frames == null) return;

		int count = frames.length;
		if (count == 0) return;

		// Determine the necessary grid size
		int sqrt = (int) Math.sqrt(count);
		int rows = sqrt;
		int cols = sqrt;
		if (rows * cols < count)
		{
			cols++;
			if (rows * cols < count)
			{
				rows++;
			}
		}

		// Define some initial values for size & location.
		Dimension size = getSize();

		int w = size.width / cols;
		int h = size.height / rows;
		int x = 0;
		int y = 0;

		// Iterate over the frames, deiconifying any iconified frames and then
		// relocating & resizing each.
		for (int i = 0; i < cols; i++)
		{
			if (i == cols - 1)
			{
				// Recalculate height at last column.
				h = size.height / (count - rows * (cols - 1));
			}
			for (int j = 0; j < rows && ((i * rows) + j < count); j++)
			{
				JInternalFrame frame = frames[(i * rows) + j];

				try
				{
					if (frame.isClosed()) continue;
					if (frame.isIcon()) frame.setIcon(false);
					if (frame.isMaximum()) frame.setMaximum(false);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				frame.setSize(w, h);
				frame.setLocation(x, y);
				y += h; // start the next row
			}
			x += w;
			y = 0;
		}
	}

	@Override
	public Dimension getPreferredSize()
	{
		JInternalFrame[] frames = getAllFrames();

		if (frames == null) super.getPreferredSize();

		int x1 = 0;		// the left most x point
		int y1 = 0;		// the top most y point
		int x2 = 0;		// the right most x point
		int y2 = 0;		// the lowest y point

		for (int i = 0; i < frames.length; i++)
		{
			Point point1 = frames[i].getLocation();
			Dimension size = frames[i].getSize();
			Point point2 = new Point(point1.x + size.width, point1.y + size.height);

			if (point1.x < x1) x1 = point1.x;
			if (point1.y < y1) y1 = point1.y;
			if (point2.x > x2) x2 = point2.x;
			if (point2.y > y2) y2 = point2.y;
		}

		return new Dimension(x2 - x1, y2 - y1);
	}
}
