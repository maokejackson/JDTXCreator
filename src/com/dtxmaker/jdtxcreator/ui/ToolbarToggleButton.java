package com.dtxmaker.jdtxcreator.ui;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class ToolbarToggleButton extends JToggleButton
{
	private static final long serialVersionUID = 3795898624029328627L;

	public ToolbarToggleButton(String filename)
	{
		this(filename, null);
	}
	
	public ToolbarToggleButton(String filename, String toolTipText)
	{
		super(new ImageIcon(filename));
		
		setFocusable(false);
		setToolTipText(toolTipText);
	}
}
