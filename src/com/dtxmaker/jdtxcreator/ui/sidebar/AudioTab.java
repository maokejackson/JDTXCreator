package com.dtxmaker.jdtxcreator.ui.sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.data.Audio;
import com.dtxmaker.jdtxcreator.data.RowNumber;
import com.dtxmaker.jdtxcreator.ui.BooleanCellRenderer;
import com.dtxmaker.jdtxcreator.ui.ToolbarButton;
import com.dtxmaker.jdtxcreator.ui.ToolbarToggleButton;

public class AudioTab extends AbstractTableTab<Audio> implements ActionListener
{
	private static final long serialVersionUID = 5882072010719396627L;

	private static AudioTab instance;
	
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
		table.addColumn(Language.get("audio.table.number"), RowNumber.getVector());
		table.addColumn(Language.get("audio.table.label"), "");
		table.addColumn(Language.get("audio.table.file"), "");
		table.addColumn(Language.get("audio.table.volume"), 100);
		table.addColumn(Language.get("audio.table.position"), 0);
		table.addColumn(Language.get("audio.table.size"), 100);
		table.addColumn(Language.get("audio.table.bgm"), false);
		
		table.getColumnExt(COLUMN_BGM).setCellRenderer(new BooleanCellRenderer());
//		table.setRowFilter(RowFilter.regexFilter("([^0]0|[^0])", COLUMN_NUMBER));
		
		// set default column index
		int[] index = Settings.getIntegerArray(Settings.SOUND_LIST + Settings.COLUMN_INDEX);
		for (int i = 0; i < index.length; i++)
		{
			table.setColumnViewIndex(i, index[i]);
		}
		
		// set default column width
		int[] width = Settings.getIntegerArray(Settings.SOUND_LIST + Settings.COLUMN_WIDTH, 75);
		for (int i = 0; i < width.length; i++)
		{
			table.setColumnWidth(i, width[i]);
		}
		
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
		Vector<Audio> vector = new Vector<Audio>();
		
		for (int row = 0; row < size; row++)
		{
			Audio audio = getDataAt(row);
			String path = audio.getPath();
			if (path == null || path.length() == 0) continue;
			vector.add(audio);
		}
		
		dtx.setAudios(null);	// gc
		dtx.setAudios(vector);
	}

	@Override
	protected void fillData()
	{
		Vector<Audio> vector = dtx.getAudios();
		
		if (vector == null) return;
		
		for (int i = 0; i < vector.size(); i++)
		{
			Audio audio = vector.get(i);
			int row = RowNumber.getRowIndex(audio.getNumber());
			if (row != -1) fillDataAt(audio, row);
		}
	}
	
	@Override
	protected void fillDataAt(Audio audio, int row)
	{
		table.setModelValueAt(audio.getPath(), row, COLUMN_PATH);
		table.setModelValueAt(audio.getLabel(), row, COLUMN_LABEL);
		table.setModelValueAt(audio.getVolume(), row, COLUMN_VOLUME);
		table.setModelValueAt(audio.getPosition(), row, COLUMN_POSITION);
		table.setModelValueAt(audio.getSize(), row, COLUMN_SIZE);
		table.setModelValueAt(audio.isBgm(), row, COLUMN_BGM);
	}

	@Override
	public Audio getDataAt(int row)
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
	protected void resetDataAt(int row)
	{
		table.setModelValueAt("", row, COLUMN_PATH);
		table.setModelValueAt("", row, COLUMN_LABEL);
		table.setModelValueAt(100, row, COLUMN_VOLUME);
		table.setModelValueAt(0, row, COLUMN_POSITION);
		table.setModelValueAt(100, row, COLUMN_SIZE);
		table.setModelValueAt(false, row, COLUMN_BGM);
	}
	
	@Override
	protected void openDialog(Audio data)
	{
		int row = table.getSelectedRow();
		AudioPropertyDialog dialog = AudioPropertyDialog.getInstance();
		
		dialog.showDialog(data);
		if (!dialog.isApprove()) return;
		
		fillDataAt(dialog.getData(), row);
	}

	@Override
	protected void mouseClickedEvent()
	{
		stop();
		play();
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
