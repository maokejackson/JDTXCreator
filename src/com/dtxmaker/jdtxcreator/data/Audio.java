package com.dtxmaker.jdtxcreator.data;

public class Audio
{
	public static final String WAV = "#WAV";
	public static final String BGMWAV = "#BGMWAV";
	public static final String VOLUME = "#VOLUME";
	public static final String POSITION = "#PAN";
	public static final String SIZE = "#SIZE";
	
	private String number;		// #WAVzz <WAV(, MP3, XA or OGG) filename>
	private String path;
	private String label;
	private boolean bgm;		// #BGMWAV ON
	private int volume = 100;	// #VOLUMEzz <volume percentage>
	private int position = 0;	// #PANzz <panning position parameter>
	private int size = 100;		// #SIZEzz <chip display size percentage> (DTXMania Release 066b071015 or later)

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public boolean isBgm()
	{
		return bgm;
	}

	public void setBgm(boolean bgm)
	{
		this.bgm = bgm;
	}

	public int getVolume()
	{
		return volume;
	}

	public void setVolume(int volume)
	{
		this.volume = volume;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}
}
