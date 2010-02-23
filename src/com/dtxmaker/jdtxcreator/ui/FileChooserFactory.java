package com.dtxmaker.jdtxcreator.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.dtxmaker.jdtxcreator.ui.chart.InternalChartManager;
import com.dtxmaker.jdtxcreator.ui.main.Main;

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
		fileChooser = new JFileChooser(InternalChartManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("DTX file (*.dtx)", "dtx"));
		
		return fileChooser.showOpenDialog(Main.getInstance());
	}
	
	public static int openAudio()
	{
		fileChooser = new JFileChooser(InternalChartManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Audio file (*.wav;*.mp3;*.ogg;*.xa)", "wav", "mp3", "ogg", "xa"));
		
		return fileChooser.showOpenDialog(Main.getInstance());
	}
	
	public static int openImage()
	{
		fileChooser = new JFileChooser(InternalChartManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image file (*.bmp;*.jpg;*.jpeg;*.png)", "bmp", "jpg", "jpeg", "png"));
		
		return fileChooser.showOpenDialog(Main.getInstance());
	}
	
	public static int openVideo()
	{
		fileChooser = new JFileChooser(InternalChartManager.getInstance().getWorkingDir());
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Video file (*.avi)", "avi"));
		
		return fileChooser.showOpenDialog(Main.getInstance());
	}
	
	public static int saveAs()
	{
		fileChooser = new JFileChooser(InternalChartManager.getInstance().getWorkingDir())
		{
			private static final long serialVersionUID = -4206064499743840510L;

			@Override
			public void approveSelection()
			{
				File file = getSelectedFile();
				if (file.exists())
				{
					int answer = JOptionPane.showConfirmDialog(
							this, file + " exists. Overwrite?", "Overwrite?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
					);
					if (answer != JOptionPane.OK_OPTION) return;
				}
				super.approveSelection();
			}
		};
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("DTX file (*.dtx)", "dtx"));
		
		return fileChooser.showSaveDialog(Main.getInstance());
	}
}
