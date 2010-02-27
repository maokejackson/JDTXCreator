package com.dtxmaker.jdtxcreator.ui.sidebar;

import java.util.Vector;

import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.data.RowNumber;
import com.dtxmaker.jdtxcreator.data.Video;

public class VideoTab extends AbstractTableTab<Video>
{
	private static final long serialVersionUID = 2722260797018554993L;
	
	private static VideoTab instance;

	private static final int COLUMN_NUMBER = 0;
	private static final int COLUMN_LABEL = 1;
	private static final int COLUMN_PATH = 2;

	public static VideoTab getInstance()
	{
		if (instance == null) instance = new VideoTab();
		return instance;
	}
	
	private VideoTab()
	{
		table.addColumn("No", RowNumber.getVector());
		table.addColumn("Label", "");
		table.addColumn("File", "");
		
		// set default column index
		int[] index = Settings.getIntegerArray(Settings.VIDEO_LIST + Settings.COLUMN_INDEX);
		for (int i = 0; i < index.length; i++)
		{
			table.setColumnViewIndex(i, index[i]);
		}
		
		// set default column width
		int[] width = Settings.getIntegerArray(Settings.VIDEO_LIST + Settings.COLUMN_WIDTH, 75);
		for (int i = 0; i < width.length; i++)
		{
			table.setColumnWidth(i, width[i]);
		}
	}

	@Override
	public void captureData()
	{
		int size = table.getRowCount();
		Vector<Video> vector = new Vector<Video>();
		
		for (int row = 0; row < size; row++)
		{
			Video video = getDataAt(row);
			String path = video.getPath();
			if (path == null || path.length() == 0) continue;
			vector.add(video);
		}
		
		dtx.setVideos(null);	// gc
		dtx.setVideos(vector);
	}

	@Override
	protected void fillData()
	{
		Vector<Video> vector = dtx.getVideos();
		
		if (vector == null) return;
		
		for (int i = 0; i < vector.size(); i++)
		{
			Video video = vector.get(i);
			int row = RowNumber.getRowIndex(video.getNumber());
			if (row != -1) fillDataAt(video, row);
		}
	}

	@Override
	protected void fillDataAt(Video video, int row)
	{
		table.setModelValueAt(video.getPath(), row, COLUMN_PATH);
		table.setModelValueAt(video.getLabel(), row, COLUMN_LABEL);
	}

	@Override
	public Video getDataAt(int row)
	{
		String number = (String) table.getModelValueAt(row, COLUMN_NUMBER);
		String path = (String) table.getModelValueAt(row, COLUMN_PATH);
		String label = (String) table.getModelValueAt(row, COLUMN_LABEL);
		
		Video video = new Video();
		video.setNumber(number);
		video.setPath(path);
		video.setLabel(label);
		
		return video;
	}

	@Override
	protected void resetDataAt(int row)
	{
		table.setModelValueAt("", row, COLUMN_PATH);
		table.setModelValueAt("", row, COLUMN_LABEL);
	}

	@Override
	protected void openDialog(Video data)
	{
		int row = table.getSelectedRow();
		VideoPropertyDialog dialog = VideoPropertyDialog.getInstance();
		
		dialog.showDialog(data);
		if (!dialog.isApprove()) return;
		
		fillDataAt(dialog.getData(), row);
	}

	@Override
	protected void mouseClickedEvent()
	{
		// do nothing
	}
}
