package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.Font;

public class Note
{
	private static final long serialVersionUID = 5225025019176086205L;
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 9;
	
	protected static final Font font = new Font("MS Gothic", Font.BOLD, 8);
	
	int channel;
	int lane;
	int grid;
	int number;
	boolean drag;
	boolean move;
	boolean selected;
	boolean back;
	
	public Note()
	{
		
	}
	
}
