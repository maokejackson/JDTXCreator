package com.dtxmaker.jdtxcreator.data;

import java.util.Vector;

public class RowNumber
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

	public static int getRowIndex(String number)
	{
		return vector.indexOf(number);
	}
	
	public static String getNumber(int rowIndex)
	{
		return vector.get(rowIndex);
	}
	
	public static Vector<String> getVector()
	{
		return vector;
	}
}
