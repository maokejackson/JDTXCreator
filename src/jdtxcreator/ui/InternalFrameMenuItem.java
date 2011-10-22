package jdtxcreator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JRadioButtonMenuItem;

public class InternalFrameMenuItem extends JRadioButtonMenuItem implements ActionListener
{
	private static final long serialVersionUID = -4707071549239773536L;
	
	JInternalFrame frame;
	
	public InternalFrameMenuItem(JInternalFrame frame)
	{
		this.frame = frame;
		
		setText(frame.getTitle());
		addActionListener(this);
	}
	
	public JInternalFrame getWindow()
	{
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		setSelected(true);
		
		try
		{
			frame.setSelected(true);
		}
		catch (Exception ex)
		{
			// ignore
		}
	}
}
