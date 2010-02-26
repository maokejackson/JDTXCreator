package com.dtxmaker.jdtxcreator.ui;

import java.io.File;

import javax.swing.JFileChooser;

import com.dtxmaker.jdtxcreator.ui.chart.ChartFrameManager;

public class AudioFileChooser extends AbstractFileChooser
{
	private static final long serialVersionUID = -377786583318009257L;

	@Override
	public void openFileChooser()
	{
		int state = FileChooserFactory.openAudio();
		
		if (state != JFileChooser.APPROVE_OPTION) return;
		File file = FileChooserFactory.getSelectedFile();
		
		try
		{
			setPath(getRelativePath(file, ChartFrameManager.getInstance().getWorkingDir()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
