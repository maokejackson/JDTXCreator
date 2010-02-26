package com.dtxmaker.jdtxcreator.ui;

import info.clearthought.layout.TableLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dtxmaker.jdtxcreator.Language;

public abstract class AbstractFileChooser extends JPanel
{
	private static final long serialVersionUID = -8647764400859557480L;
	
	JTextField textField;
	JButton button;
	
	public AbstractFileChooser()
	{
		textField = new JTextField();
		button = new JButton("...");
		button.setToolTipText(Language.get("filechooser.select_file"));
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				openFileChooser();
			}
		});
		
		double[][] size = {
				{ TableLayout.FILL, 5, button.getPreferredSize().height },
				{ TableLayout.PREFERRED }
		};
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(textField, "0, 0");
		add(button, "2, 0");
	}
	
	public void setToolTipText(String text)
	{
		textField.setToolTipText(text);
	}
	
	public String getPath()
	{
		return textField.getText();
	}
	
	public void setPath(String text)
	{
		textField.setText(text);
	}
	
	/**
	 * Get relative path.
	 * @param file target file.
	 * @param dir a dir.
	 * @return The file path relative to dir.
	 * @throws IOException
	 */
	protected String getRelativePath(File file, File dir) throws IOException
	{
		File origFile = file;
		File origDir = dir;
		ArrayList<File> filePathStack = new ArrayList<File>();
		ArrayList<File> dirPathStack = new ArrayList<File>();
		
		// build the path stack info to compare it afterwards
		file = file.getCanonicalFile();
		
		while (file != null)
		{
			filePathStack.add(0, file);
			file = file.getParentFile();
		}
		
		dir = dir.getCanonicalFile();
		
		while (dir != null)
		{
			dirPathStack.add(0, dir);
			dir = dir.getParentFile();
		}
		
		// compare as long it goes
		int count = 0;
		file = filePathStack.get(count);
		dir = dirPathStack.get(count);
		
		System.out.println("File path size: " + filePathStack.size());
		System.out.println("Dir path size: " + dirPathStack.size());
		System.out.println();
		
		while ((count < filePathStack.size() - 1)
				&& (count < dirPathStack.size() - 1)
				&& file.equals(dir))
		{
			count++;
			file = filePathStack.get(count);
			dir = dirPathStack.get(count);
			
			System.out.println("Count: " + count);
			System.out.println("File absolute path: " + file.getAbsolutePath());
			System.out.println("Dir absolute path: " + dir.getAbsolutePath());
		}
		
		if (file.equals(dir)) count++;
		
		// up as far as necessary
		StringBuffer relString = new StringBuffer();
		for (int i = count; i < dirPathStack.size(); i++)
		{
			relString.append(".." + File.separator);
		}
		
		System.out.println("Up as far as necessary: " + relString.toString());
		
		// now back down to the file
		for (int i = count; i < filePathStack.size() - 1; i++)
		{
			relString.append(filePathStack.get(i).getName() + File.separator);
		}
		
		System.out.println("Back down to the file: " + relString.toString());
		
		relString.append(filePathStack.get(filePathStack.size() - 1).getName());
		
		// just to test
		File relFile = new File(origDir.getAbsolutePath() + File.separator + relString.toString());
		if (!relFile.getCanonicalFile().equals(origFile.getCanonicalFile()))
		{
			System.out.println("File: " + origFile.getCanonicalPath());
			System.out.println("Dir: " + origDir.getAbsolutePath());
			System.out.println("Relative string: " + relString.toString());
			System.out.println("Result: " + relFile.getCanonicalPath());
			throw new IOException("Failed to find relative path.");
		}
		
		return relString.toString();
	}
	
	public abstract void openFileChooser();
}
