package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.dtxmaker.jdtxcreator.data.DTX;
import com.dtxmaker.jdtxcreator.io.DTXReader;
import com.dtxmaker.jdtxcreator.ui.FileChooserFactory;
import com.dtxmaker.jdtxcreator.ui.main.Main;
import com.dtxmaker.jdtxcreator.ui.main.Menu;
import com.dtxmaker.jdtxcreator.ui.main.Toolbar;
import com.dtxmaker.jdtxcreator.ui.sidebar.SideBar;

public class InternalChartManager extends JDesktopPane
{
	private static final long serialVersionUID = 898342948470015613L;
	
	private static InternalChartManager instance;
	
	private Point lastLocation;
	
	/** <code>true</code> for edit mode, <code>false</code> for select mode. */
	boolean mode = true;

	public static InternalChartManager getInstance()
	{
		if (instance == null) instance = new InternalChartManager();
		return instance;
	}
	
	private InternalChartManager()
	{
		setBackground(Color.GRAY);
		setDragMode(OUTLINE_DRAG_MODE);
		setMode(mode);
		
		lastLocation = new Point();
	}
	
	public File getWorkingDir()
	{
		InternalChart chart = getSelectedChart();
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
	 * Open a chart.
	 */
	public void open()
	{
		int answer = FileChooserFactory.openDTXs();
		
		if (answer == JFileChooser.CANCEL_OPTION) return;
		File[] files = FileChooserFactory.getSelectedFiles();
		
		if (files == null) return;
		
		for (int i = 0; i < files.length; i++)
		{
			DTX dtx = DTXReader.read(files[i]);
			
			if (dtx == null) continue;
			
			InternalChart chart = new InternalChart(dtx);
			chart.setFile(files[i]);
			addChart(chart);
		}
	}
	
	public void save()
	{
		InternalChart chart = getSelectedChart();
		
		if (chart == null) return;
		
		chart.save();
	}
	
	public void saveAs()
	{
		InternalChart chart = getSelectedChart();
		
		if (chart == null) return;
		
		chart.saveAs(null);
	}
	
	public void saveAll()
	{
		InternalChart[] charts = InternalChartManager.getInstance().getAllCharts();
		
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
	 * @param chart the chart to be closed.
	 * @return <code>true</code> if chart is closed.
	 */
	public boolean close(InternalChart chart)
	{
		if (chart == null) return true;
		
		if (chart.isDirty())
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
		
		return true;
	}
	
	/**
	 * Close all charts. If content is modified, will ask user whether to save it.
	 * @return <code>false</code> if cancel is clicked when a confirm to close dialog show. 
	 */
	public boolean closeAll()
	{
		InternalChart[] charts = getAllCharts();
		
		if (charts != null)
		{
			for (int i = 0; i < charts.length; i++)
			{
				if (!close(charts[i])) return false;	// if user click cancel
			}
		}
		
		return true;
	}
	
	public InternalChart getSelectedChart()
	{
		JInternalFrame frame = getSelectedFrame();
		if (frame == null) return null;
		return (InternalChart) frame;
	}
	
	public InternalChart[] getAllCharts()
	{
		JInternalFrame[] frames = getAllFrames();
		if (frames == null || frames.length == 0) return null;
		InternalChart[] charts = new InternalChart[frames.length];
		
		for (int i = 0; i < charts.length; i++)
		{
			charts[i] = (InternalChart) frames[i];
		}
		
		return charts;
	}
	
	public void newChart()
	{
		addChart(new InternalChart());
	}
	
	public void addChart(InternalChart chart)
	{
		add(chart);
		putChart(chart, lastLocation);
		Menu.getInstance().addFrame(chart);
	}
	
	private void putChart(JInternalFrame frame, Point location)
	{
		Dimension availableSize = getSize();
//		Dimension frameSize = new Dimension((int) (size.width * 0.6), (int) (size.height * 0.6));
		frame.setLocation(location);
		frame.setSize((int) (availableSize.width * 0.6), (int) (availableSize.height * 0.6));
		
		try
		{
			frame.setSelected(true);
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
	
	public void selectLastChart()
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
			putChart(frame, location);
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
		for (int i = 0; i < rows; i++)
		{
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

	/**
	 * Tile windows vertically.
	 */
	public void tileVertically()
	{
		
	}
}
