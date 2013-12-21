package jdtxcreator.ui.chart;

import java.awt.Font;

public class Note
{
	public static final int WIDTH = 28;
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
