package jdtxcreator.ui.sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;

import jdtxcreator.Language;
import jdtxcreator.data.Audio;
import jdtxcreator.ui.BooleanCellRenderer;
import jdtxcreator.ui.FileChooserFactory;
import jdtxcreator.ui.ToolbarButton;
import jdtxcreator.ui.ToolbarToggleButton;
import jdtxcreator.ui.chart.ChartFrameManager;
import jdtxcreator.util.Util;

public class AudioTab extends AbstractTableTab<Audio> implements ActionListener
{
	private static final long serialVersionUID = 5882072010719396627L;

	private static AudioTab instance;

	private static final String[] header =
	{
		Language.get("audio.table.number"),
		Language.get("audio.table.label"),
		Language.get("audio.table.file"),
		Language.get("audio.table.volume"),
		Language.get("audio.table.position"),
		Language.get("audio.table.size"),
		Language.get("audio.table.bgm"),
	};

	private static final int COLUMN_NUMBER = 0;
	private static final int COLUMN_LABEL = 1;
	private static final int COLUMN_PATH = 2;
	private static final int COLUMN_VOLUME = 3;
	private static final int COLUMN_POSITION = 4;
	private static final int COLUMN_SIZE = 5;
	private static final int COLUMN_BGM = 6;

	JButton btnPlay, btnStop;
	JToggleButton btnSpeaker;

	public static AudioTab getInstance()
	{
		if (instance == null) instance = new AudioTab();
		return instance;
	}

	private AudioTab()
	{
		super(header, "sound_list");
//		table.addColumn(Language.get("audio.table.number"), RowNumber.getVector());
//		table.addColumn(Language.get("audio.table.label"), "");
//		table.addColumn(Language.get("audio.table.file"), "");
//		table.addColumn(Language.get("audio.table.volume"), 100);
//		table.addColumn(Language.get("audio.table.position"), 0);
//		table.addColumn(Language.get("audio.table.size"), 100);
//		table.addColumn(Language.get("audio.table.bgm"), false);

		table.setCellRendererAt(new BooleanCellRenderer(), COLUMN_BGM);
//		table.setRowFilter(RowFilter.regexFilter("([^0]0|[^0])", COLUMN_NUMBER));

		btnPlay = new ToolbarButton("images/play.png");
		btnPlay.setToolTipText(Language.get("audio.toolbar.tooltip.play"));
		btnPlay.addActionListener(this);
		btnStop = new ToolbarButton("images/stop.png");
		btnStop.setToolTipText(Language.get("audio.toolbar.tooltip.stop"));
		btnStop.addActionListener(this);
		btnSpeaker = new ToolbarToggleButton("images/speaker.png");
		btnSpeaker.setToolTipText(Language.get("audio.toolbar.tooltip.sound"));
		btnSpeaker.setSelected(true);

		toolbar.addSeparator();
		toolbar.add(btnPlay);
		toolbar.add(btnStop);
		toolbar.addSeparator();
		toolbar.add(btnSpeaker);
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		btnPlay.setEnabled(enabled);
		btnStop.setEnabled(enabled);
		btnSpeaker.setEnabled(enabled);
	}

	@Override
	public void captureData()
	{
		int size = table.getRowCount();
		Vector<Audio> vector = new Vector<Audio>(size);

		for (int row = 0; row < size; row++)
		{
			Audio audio = getDataAt(row);
			String path = audio.getPath();
			if (path == null || path.length() == 0) continue;
			vector.add(audio);
		}

		dtx.setAudios(vector);
	}

	@Override
	protected Vector<Audio> getData()
	{
		return dtx.getAudios();
	}

	@Override
	protected void fillDataAt(Audio audio, int row)
	{
		table.setModelValueAt(audio.getNumber(), row, COLUMN_NUMBER);
		table.setModelValueAt(audio.getPath(), row, COLUMN_PATH);
		table.setModelValueAt(audio.getLabel(), row, COLUMN_LABEL);
		table.setModelValueAt(audio.getVolume(), row, COLUMN_VOLUME);
		table.setModelValueAt(audio.getPosition(), row, COLUMN_POSITION);
		table.setModelValueAt(audio.getSize(), row, COLUMN_SIZE);
		table.setModelValueAt(audio.isBgm(), row, COLUMN_BGM);
	}

	@Override
	protected Audio getDataAt(int row)
	{
		String number = (String) table.getModelValueAt(row, COLUMN_NUMBER);
		String path = (String) table.getModelValueAt(row, COLUMN_PATH);
		String label = (String) table.getModelValueAt(row, COLUMN_LABEL);
		int volume = (Integer) table.getModelValueAt(row, COLUMN_VOLUME);
		int position = (Integer) table.getModelValueAt(row, COLUMN_POSITION);
		int size = (Integer) table.getModelValueAt(row, COLUMN_SIZE);
		boolean bgm = (Boolean) table.getModelValueAt(row, COLUMN_BGM);

		Audio audio = new Audio();
		audio.setNumber(number);
		audio.setPath(path);
		audio.setLabel(label);
		audio.setVolume(volume);
		audio.setPosition(position);
		audio.setSize(size);
		audio.setBgm(bgm);

		return audio;
	}

	@Override
	protected Object[] convertToArray(Audio data)
	{
		Object[] array = new Object[header.length];

		array[COLUMN_NUMBER] = data.getNumber();
		array[COLUMN_PATH] = data.getPath();
		array[COLUMN_LABEL] = data.getLabel();
		array[COLUMN_VOLUME] = data.getVolume();
		array[COLUMN_POSITION] = data.getPosition();
		array[COLUMN_SIZE] = data.getSize();
		array[COLUMN_BGM] = data.isBgm();

		return array;
	}

	@Override
	protected void mouseClickedEvent()
	{
		stop();
		play();
	}

	@Override
	protected void edit(Audio data)
	{
		int row = table.getSelectedRow();
		AudioPropertyDialog dialog = AudioPropertyDialog.getInstance();

		dialog.showDialog(data);
		if (!dialog.isApprove()) return;

		fillDataAt(dialog.getData(), row);
	}

	@Override
	protected void add()
	{
		int answer = FileChooserFactory.openAudio(true);
		if (answer != JFileChooser.APPROVE_OPTION) return;

		File[] files = FileChooserFactory.getSelectedFiles();

		for (File file : files)
		{
			String path = Util.getRelativePath(file, ChartFrameManager.getInstance().getWorkingDir());
			Audio data = new Audio();
			data.setPath(path);
			addData(data);
		}
	}

	protected void play()
	{
		// TODO Auto-generated method stub

	}

	protected void stop()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		if (obj == btnPlay) play();
		else if (obj == btnStop) stop();
	}
}
