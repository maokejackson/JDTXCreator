package com.dtxmaker.jdtxcreator.ui.main;

import info.clearthought.layout.TableLayout;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dtxmaker.jdtxcreator.ui.SlightBevelBorder;

public class StatusBar extends JPanel
{
	private static final long serialVersionUID = -1498488069152843354L;

	private static StatusBar instance;
	
	JLabel lblChannel, lblPart, lblSelected;
	JLabel lblLocation;

	public static StatusBar getInstance()
	{
		if (instance == null) instance = new StatusBar();
		return instance;
	}
	
	private StatusBar()
	{
		lblChannel = new JLabel("Channel");
		lblChannel.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblPart = new JLabel("Part");
		lblPart.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblLocation = new JLabel();
		lblLocation.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblSelected = new JLabel();
		lblSelected.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		
		int height = lblChannel.getPreferredSize().height;
		lblChannel.setPreferredSize(new Dimension(150, height));
		lblPart.setPreferredSize(new Dimension(150, height));
		lblLocation.setPreferredSize(new Dimension(150, height));
		lblSelected.setPreferredSize(new Dimension(150, height));
		
		double p = TableLayout.PREFERRED;
		double g = 1;
		double b = 2;
		double[][] size = {
				{ b, 120, b, 120, b, 120, b, 120, b },
				{ g, p, g }
		};
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(lblChannel, "1, 1");
		add(lblPart, "3, 1");
		add(lblLocation, "5, 1");
		add(lblSelected, "7, 1");
	}
	
	public void setPoint(Point point)
	{
		String text = "(x, y) = (" + point.x + ", " + point.y + ")";
		lblLocation.setText(text);
	}
	
	public void setSelectedCount(int count)
	{
		String text = count + " note(s) selected.";
		lblSelected.setText(text);
	}
}
