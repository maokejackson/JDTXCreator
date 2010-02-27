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
	
	@Override
	public void setEnabled(boolean enabled)
	{
		textField.setEnabled(enabled);
		button.setEnabled(enabled);
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
	 * Get path of a file relative to a directory.
	 * @param file target file.
	 * @param dir a directory relative to.
	 * @return The path of a file relative to a directory.
	 * @throws IOException Failed to find relative path.
	 */
	protected String getRelativePath(File file, File dir) throws IOException
	{
		/*
		 * Windows seems in some cases not to stop getParent() at 'c:\', which I
		 * considered to be root. For that reason I had to tweak in the
		 * following to 'ugly' lines:
		 */
		file = new File(file + File.separator + "89243jmsjigs45u9w43545lkhj7").getParentFile();
		dir = new File(dir + File.separator + "984mvcxbsfgqoykj30487df556").getParentFile();

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
		
		while ((count < filePathStack.size() - 1)
				&& (count < dirPathStack.size() - 1)
				&& file.equals(dir))
		{
			count++;
			file = filePathStack.get(count);
			dir = dirPathStack.get(count);
		}
		
		if (file.equals(dir)) count++;
		
		// up as far as necessary
		StringBuffer relString = new StringBuffer();
		for (int i = count; i < dirPathStack.size(); i++)
		{
			relString.append(".." + File.separator);
		}
		
		// now back down to the file
		for (int i = count; i < filePathStack.size() - 1; i++)
		{
			relString.append(filePathStack.get(i).getName() + File.separator);
		}
		
		relString.append(filePathStack.get(filePathStack.size() - 1).getName());
		
		// just to test
		File relFile = new File(origDir.getAbsolutePath() + File.separator + relString.toString());
		if (!relFile.getCanonicalFile().equals(origFile.getCanonicalFile()))
		{
			throw new IOException("Failed to find relative path.");
		}
		
		return relString.toString();
	}
	
	public abstract void openFileChooser();
}
