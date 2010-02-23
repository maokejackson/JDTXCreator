package com.dtxmaker.jdtxcreator.data;

public class Image
{
	public static final String BMP = "#BMP";
	public static final String BGA = "#BGA";
	public static final String TEX = "TEX";
	
	private String number;	// #BMPzz <image filename>
	private String path;
	private String label;
	private boolean texture;
	
	private int bgaX1;		// #BGAzz <image-chip No.> <x1> <y1> <x2> <y2> <ox> <oy>
	private int bgaY1;
	private int bgaX2;
	private int bgaY2;
	private int bgaOX;
	private int bgaOY;

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

	public boolean isTexture()
	{
		return texture;
	}

	public void setTexture(boolean texture)
	{
		this.texture = texture;
	}
}
