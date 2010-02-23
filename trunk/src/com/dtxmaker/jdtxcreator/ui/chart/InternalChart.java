package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.dtxmaker.jdtxcreator.data.DTX;
import com.dtxmaker.jdtxcreator.io.DTXWriter;
import com.dtxmaker.jdtxcreator.ui.FileChooserFactory;
import com.dtxmaker.jdtxcreator.ui.main.Menu;
import com.dtxmaker.jdtxcreator.ui.sidebar.SideBar;

public class InternalChart extends JInternalFrame
{
	private static final long serialVersionUID = 3008335903707360858L;
	
	private static int counter = 1;
	
	ChartPane chart;
	ChartToolbar toolbar;
	
	DTX dtx;
	File file;
	boolean dirty = false;

	public InternalChart()
	{
		this(null);
		dirty = true;	// XXX for test
	}
	
	public InternalChart(DTX dtx)
	{
		super("Untitled " + counter, true, true, true, true);
		
		if (dtx == null)
		{
			counter++;
			dtx = new DTX();
		}
		this.dtx = dtx;
		
		chart = new ChartPane();
		toolbar = new ChartToolbar(chart);
		
		setLayout(new BorderLayout());
		setDoubleBuffered(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(frameAdapter);
		
		add(toolbar, BorderLayout.NORTH);
		add(chart, BorderLayout.CENTER);
		
		SideBar.getInstance().load(dtx);
		setVisible(true);
	}
	
	public DTX getDTX()
	{
		return dtx;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
		
		if (file != null)
		{
			setTitle(file.getName());
		}
	}

	public void undo()
	{
		// TODO: undo
	}
	
	public void redo()
	{
		// TODO: redo
	}
	
	public void zoomIn()
	{
		chart.zoomIn();
	}
	
	public void zoomOut()
	{
		chart.zoomOut();
	}
	
	public void save()
	{
		SideBar.getInstance().captureData();
		saveAs(file);
	}
	
	public void saveAs(File file)
	{
		if (file == null)
		{
			int answer = FileChooserFactory.saveAs();
			
			if (answer == JFileChooser.CANCEL_OPTION) return;
			file = FileChooserFactory.getSelectedFile();
			
			if (!file.getName().endsWith(".dtx"))
			{
				file = new File(file.getAbsolutePath() + ".dtx");
			}
		}
		
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
		
		DTXWriter.write(file, dtx);
		setFile(file);
		Menu.getInstance().renameFrame(this);
		dirty = false;
	}
	
	InternalFrameAdapter frameAdapter = new InternalFrameAdapter()
	{
		@Override
		public void internalFrameActivated(InternalFrameEvent e)
		{
			SideBar.getInstance().load(dtx);
			Menu.getInstance().setSelectedFrame(e.getInternalFrame());
		}
		
		@Override
		public void internalFrameDeactivated(InternalFrameEvent e)
		{
			SideBar.getInstance().captureData();
			super.internalFrameDeactivated(e);
		}
		
		@Override
		public void internalFrameClosing(InternalFrameEvent e)
		{
			JInternalFrame frame = e.getInternalFrame();
			InternalChartManager.getInstance().close((InternalChart) frame);
		}
	};
}
