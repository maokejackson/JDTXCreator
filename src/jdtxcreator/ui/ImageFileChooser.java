package jdtxcreator.ui;

import java.io.File;

import javax.swing.JFileChooser;

import jdtxcreator.ui.chart.ChartFrameManager;
import jdtxcreator.util.Util;


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
			setPath(Util.getRelativePath(file, ChartFrameManager.getInstance().getWorkingDir()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
