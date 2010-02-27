package com.dtxmaker.jdtxcreator.ui;

import java.io.File;

import javax.swing.JMenuItem;

public class MRUMenuItem extends JMenuItem
{
	private static final long serialVersionUID = -5674146340777371619L;

	private File file;
	
	public MRUMenuItem(File file)
	{
		if (file == null)
		{
			String message = "File cannot be null.";
			throw new IllegalArgumentException(message);
		}
		else if (file.isDirectory())
		{
			String message = "The file cannot be a directory.";
			throw new IllegalArgumentException(message);
		}
		
		this.file = file;
		setText(file.getAbsolutePath());
		setEnabled(file.exists());
	}
	
	public File getFile()
	{
		return file;
	}
}
