package jdtxcreator.ui.sidebar;

import java.util.Vector;

import jdtxcreator.data.Video;

public class VideoTab extends AbstractTableTab<Video>
{
	private static final long serialVersionUID = 2722260797018554993L;

	private static VideoTab instance;

	private static final String[] header =
	{
		"No",
		"Label",
		"File",
	};

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
		super(header, "video_list");
//		table.addColumn("No", RowNumber.getVector());
//		table.addColumn("Label", "");
//		table.addColumn("File", "");
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
	protected Vector<Video> getData()
	{
		return dtx.getVideos();
	}

	@Override
	protected void fillDataAt(Video video, int row)
	{
		table.setModelValueAt(video.getPath(), row, COLUMN_PATH);
		table.setModelValueAt(video.getLabel(), row, COLUMN_LABEL);
	}

	@Override
	protected Video getDataAt(int row)
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
	protected Object[] convertToArray(Video data)
	{
		Object[] array = new Object[header.length];

		array[COLUMN_NUMBER] = data.getNumber();
		array[COLUMN_PATH] = data.getPath();
		array[COLUMN_LABEL] = data.getLabel();

		return array;
	}

	@Override
	protected void edit(Video data)
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

	@Override
	protected void add()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void remove()
	{
		// TODO Auto-generated method stub

	}
}
