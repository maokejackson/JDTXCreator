package com.dtxmaker.jdtxcreator.ui.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.ui.chart.InternalChartManager;
import com.dtxmaker.jdtxcreator.ui.sidebar.SideBar;

public class Main extends JPanel
{
	private static final long serialVersionUID = 9087102910187363259L;
	
	private static Main instance;
	
	Toolbar toolbar;
	StatusBar statusBar;
	SideBar sideBar;
	InternalChartManager charts;
	
	JSplitPane splitPane;

	public static Main getInstance()
	{
		if (instance == null) instance = new Main();
		return instance;
	}
	
	private Main()
	{
		super(new BorderLayout());
		
		toolbar = Toolbar.getInstance();
		statusBar = StatusBar.getInstance();
		sideBar = SideBar.getInstance();
		charts = InternalChartManager.getInstance();
		
		splitPane = new JSplitPane();
		splitPane.addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				Settings.setDividerLocation(splitPane.getDividerLocation());
			}
		});
		setSideBar(Settings.getSidebarPosition(), Settings.getDividerLocation());
		
		add(toolbar, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
	}
	
	public void setSideBar(boolean onLeft, int location)
	{
		if (onLeft)
		{
			splitPane.setLeftComponent(sideBar);
			splitPane.setRightComponent(charts);
		}
		else
		{
			splitPane.setLeftComponent(charts);
			splitPane.setRightComponent(sideBar);
		}
		
		splitPane.setDividerLocation(location);
		
		Settings.setDividerLocation(location);
		Settings.setSidebarPosition(onLeft);
	}
	
	public void switchSideBar()
	{
		int location = splitPane.getDividerLocation();
		int newLocation = getSize().width - location;
		
		Component leftCom = splitPane.getLeftComponent();
		Component rightCom = splitPane.getRightComponent();
		
		splitPane.setLeftComponent(null);
		splitPane.setRightComponent(null);
		
		splitPane.setLeftComponent(rightCom);
		splitPane.setRightComponent(leftCom);
		splitPane.setDividerLocation(newLocation);
		
		Settings.setDividerLocation(newLocation);
		Settings.setSidebarPosition(rightCom instanceof SideBar);
	}
	
	public void exit()
	{
		if (!InternalChartManager.getInstance().closeAll()) return;
		Settings.store();
		System.exit(0);
	}
}
