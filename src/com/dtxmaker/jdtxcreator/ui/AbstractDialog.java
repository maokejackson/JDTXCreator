package com.dtxmaker.jdtxcreator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public abstract class AbstractDialog extends JDialog
{
	private static final long serialVersionUID = 7278660229257190666L;

	public AbstractDialog()
	{
		setCloseOnEscape(true);
	}
	
	public void setCloseOnEscape(boolean yes)
	{
		ActionListener actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		};
		
		JRootPane rootPane = getRootPane();
		KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		if (yes)
		{
			rootPane.registerKeyboardAction(actionListener, keyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		}
		else
		{
			rootPane.unregisterKeyboardAction(keyStroke);
		}
	}
}
