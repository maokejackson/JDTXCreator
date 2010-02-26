package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.Color;

public class Lane
{
	public static enum TYPE {
		BPM, AUDIO, IMAGE, VIDEO, FI,
		GtV, GtR, GtG, GtB, GtW,
		BsV, BsR, BsG, BsB, BsW,
	};
	
	TYPE type;					// type of lane
	String name;				// lane name like BGM, SD, etc.
	Color background;			// background color
	boolean rightSeparatorBold;	// separator on left is bold
	int frontChannel;			// no. of front channel(left mouse click)
	int backChannel;			// no. of back channel(Ctrl + left mouse click)
	int defaultFrontNote;		// 
	int defaultBackNote;		// 
	
	public Lane(String name, Color color, boolean rightSeparatorBold, int frontChannel, int backChannel)
	{
		this.name = name;
		this.background = color;
		this.rightSeparatorBold = rightSeparatorBold;
		this.frontChannel = frontChannel;
		this.backChannel = backChannel;
	}

	public int getDefaultFrontNote()
	{
		return defaultFrontNote;
	}

	public void setDefaultFrontNote(int defaultFrontNote)
	{
		this.defaultFrontNote = defaultFrontNote;
	}

	public int getDefaultBackNote()
	{
		return defaultBackNote;
	}

	public void setDefaultBackNote(int defaultBackNote)
	{
		this.defaultBackNote = defaultBackNote;
	}
}
