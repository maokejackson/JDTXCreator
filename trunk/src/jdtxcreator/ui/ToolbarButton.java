package jdtxcreator.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolbarButton extends JButton
{
	private static final long serialVersionUID = 3795898624029328627L;

	public ToolbarButton(String filename)
	{
		this(filename, null);
	}
	
	public ToolbarButton(String filename, String toolTipText)
	{
		super(new ImageIcon(filename));
		
		setFocusable(false);
		setToolTipText(toolTipText);
	}
}
