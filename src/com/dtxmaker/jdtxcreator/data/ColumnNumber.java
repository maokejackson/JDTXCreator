package com.dtxmaker.jdtxcreator.data;

import java.util.Vector;

public class ColumnNumber
{
	private static final Vector<String> vector = new Vector<String>();
	
	static
	{
		String text = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for (int i = 0; i < text.length(); i++)
		{
			for (int j = 0; j < text.length(); j++)
			{
				String no = "" + text.charAt(i) + text.charAt(j);
				vector.add(no);
			}
		}
		vector.remove(0);	// remove 00
	}

	public static int getColumn(String number)
	{
		return vector.indexOf(number);
	}
	
	public static String getNumber(int column)
	{
		return vector.get(column);
	}
	
	public static Vector<String> getVector()
	{
		return vector;
	}
}
