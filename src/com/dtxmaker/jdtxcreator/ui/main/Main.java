package com.dtxmaker.jdtxcreator.ui.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.ui.chart.ChartFrameManager;
import com.dtxmaker.jdtxcreator.ui.sidebar.SideBar;

public class Main extends JPanel
{
	private static final long serialVersionUID = 9087102910187363259L;
	
	private static Main instance;
	
	Toolbar toolbar;
	StatusBar statusBar;
	SideBar sideBar;
	ChartFrameManager charts;
	JScrollPane scrollPane;
	
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
		charts = ChartFrameManager.getInstance();
		scrollPane = new JScrollPane(charts);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		
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
		
		/**
		 *  remove F6 and F8 keystroke mappings
		 *  @see http://java.sun.com/j2se/1.4.2/docs/api/javax/swing/doc-files/Key-Windows.html#JSplitPane
		 */
		InputMap im = splitPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).getParent();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), null);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), null);
		
		add(toolbar, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
	}
	
	public void setSideBar(boolean onLeft, int location)
	{
		if (onLeft)
		{
			splitPane.setLeftComponent(sideBar);
			splitPane.setRightComponent(scrollPane);
		}
		else
		{
			splitPane.setLeftComponent(scrollPane);
			splitPane.setRightComponent(sideBar);
		}
		
		splitPane.setDividerLocation(location);
		
		Settings.setDividerLocation(location);
		Settings.setSidebarPosition(onLeft);
	}
	
	public void switchSideBar()
	{
		int location = splitPane.getDividerLocation();
		int dividerSize = splitPane.getDividerSize();
		int newLocation = getSize().width - location - dividerSize;
		
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
		if (!ChartFrameManager.getInstance().closeAll()) return;
		Settings.store();
		System.exit(0);
	}
}
