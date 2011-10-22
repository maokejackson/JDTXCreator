package jdtxcreator.ui;

import java.io.File;

import javax.swing.JFileChooser;

import jdtxcreator.ui.chart.ChartFrameManager;
import jdtxcreator.util.Util;


public class VideoFileChooser extends AbstractFileChooser
{
	private static final long serialVersionUID = 6838356009883654687L;

	@Override
	public void openFileChooser()
	{
		int state = FileChooserFactory.openVideo();
		
		if (state != JFileChooser.APPROVE_OPTION) return;
		File file = FileChooserFactory.getSelectedFile();
		
		try
		{
			setPath(Util.getRelativePath(file, ChartFrameManager.getInstance().getWorkingDir()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
