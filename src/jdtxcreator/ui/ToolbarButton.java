package jdtxcreator.ui;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolbarButton extends JButton
{
	private static final long serialVersionUID = 3795898624029328627L;

	public ToolbarButton(Action action)
    {
	    super(action);
        init();
    }

	public ToolbarButton(String filename)
	{
		this(filename, null);
	}

	public ToolbarButton(String filename, String toolTipText)
	{
		super(new ImageIcon(filename));
		setToolTipText(toolTipText);
		init();
	}

	private void init()
	{
        setFocusable(false);
	}
}
