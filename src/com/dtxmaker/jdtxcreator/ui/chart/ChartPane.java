package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ChartPane extends JPanel
{
	private static final long serialVersionUID = 3008335903707360858L;
	
	private static final int COLUMN_WIDTH = 40;
	
	private static final int NARROW = 1;
	private static final int NORMAL = 2;
	private static final int WIDE = 4;
	
	private static final Color HORIZONTAL_COLOR = Color.LIGHT_GRAY;
	private static final Color VERTICAL_COLOR = Color.WHITE;
	private static final Color HEADER_COLOR = Color.BLUE;
	
	// 192 notes when part length = 1
	
	// BPM #585858
	// LC front #008C8C
	// LC back #006161
	// HH front #008C8C
	// HH back #006161
	// SN front #8C8C00
	// SN back #616100
	// BD front #8C4646
	// BD back #613030
	// HT front #008C00
	// HT back #006100
	// FT front #8C0000
	// FT back #610000
	// LT front #8C008C
	// LT back #610061
	// CY front #
	// CY back #
	// FI front #
	// FI back #
	// BLUE #00468C

	public ChartPane()
	{
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g)
	{
		// TODO Auto-generated method stub
		super.paint(g);
	}
	
	public void zoomIn()
	{
		// TODO: zoom in
	}
	
	public void zoomOut()
	{
		// TODO: zoom out
	}
}
