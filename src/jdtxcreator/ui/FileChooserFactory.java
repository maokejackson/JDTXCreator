package jdtxcreator.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import jdtxcreator.Language;
import jdtxcreator.ui.chart.ChartFrameManager;
import jdtxcreator.ui.main.Main;

public abstract class FileChooserFactory
{
	private static JFileChooser fileChooser;

	public static File getSelectedFile()
	{
		if (fileChooser == null) return null;
		return fileChooser.getSelectedFile();
	}

	public static File[] getSelectedFiles()
	{
		if (fileChooser == null) return null;
		return fileChooser.getSelectedFiles();
	}

	public static int openDTXs()
	{
		fileChooser = new JFileChooser(ChartFrameManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("DTX file (*.dtx)", "dtx"));

		return fileChooser.showOpenDialog(Main.getInstance());
	}

	public static int openAudio()
	{
		return openAudio(false);
	}

	public static int openAudio(boolean multiple)
	{
		fileChooser = new JFileChooser(ChartFrameManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(multiple);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Audio file (*.wav;*.mp3;*.ogg;*.xa)", "wav", "mp3", "ogg", "xa"));

		return fileChooser.showOpenDialog(Main.getInstance());
	}

	public static int openImage()
	{
		return openImage(false);
	}

	public static int openImage(boolean multiple)
	{
		fileChooser = new JFileChooser(ChartFrameManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(multiple);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image file (*.bmp;*.jpg;*.jpeg;*.png)", "bmp", "jpg", "jpeg", "png"));

		return fileChooser.showOpenDialog(Main.getInstance());
	}

	public static int openVideo()
	{
		return openVideo(false);
	}

	public static int openVideo(boolean multiple)
	{
		fileChooser = new JFileChooser(ChartFrameManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(multiple);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Video file (*.avi)", "avi"));

		return fileChooser.showOpenDialog(Main.getInstance());
	}

	public static int saveAs(String defaultFilename)
	{
		fileChooser = new JFileChooser(ChartFrameManager.getInstance().getWorkingDir())
		{
			private static final long serialVersionUID = -4206064499743840510L;

			@Override
			public void approveSelection()
			{
				File file = getSelectedFile();
				if (file.exists())
				{
					String title = Language.get("overwrite.title");
					String message = Language.get("overwrite.message", file.getName());
					int answer = JOptionPane.showConfirmDialog(this, message, title,
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
					);
					if (answer != JOptionPane.OK_OPTION) return;
				}
				super.approveSelection();
			}
		};
		fileChooser.setSelectedFile(new File(defaultFilename));
		fileChooser.setDialogTitle(Language.get("filechooser.title.save_as"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("DTX file (*.dtx)", "dtx"));

		return fileChooser.showSaveDialog(Main.getInstance());
	}
}
