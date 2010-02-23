package com.dtxmaker.jdtxcreator.ui;

import java.io.File;

import javax.swing.JFileChooser;

import com.dtxmaker.jdtxcreator.ui.chart.InternalChartManager;

public class ImageFileChooser extends AbstractFileChooser
{
	private static final long serialVersionUID = -9049015254714131999L;

	@Override
	public void openFileChooser()
	{
		int state = FileChooserFactory.openImage();
		
		if (state != JFileChooser.APPROVE_OPTION) return;
		File file = FileChooserFactory.getSelectedFile();
		
		try
		{
			setPath(getRelativePath(file, InternalChartManager.getInstance().getWorkingDir()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
