package com.dtxmaker.jdtxcreator.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.ui.ToolbarButton;
import com.dtxmaker.jdtxcreator.ui.ToolbarToggleButton;
import com.dtxmaker.jdtxcreator.ui.chart.ChartFrameManager;
import com.dtxmaker.jdtxcreator.ui.player.Player;

public class Toolbar extends JToolBar implements ActionListener
{
	private static final long serialVersionUID = 7186848358364007630L;

	private static Toolbar instance;
	
	JButton btnNew, btnOpen, btnSave, btnSaveAll;
	JButton btnCut, btnCopy, btnPaste, btnDelete;
	JButton btnUndo, btnRedo;
	JButton btnZoomIn, btnZoomOut;
	JToggleButton btnSelectMode, btnEditMode;
	JButton btnPlay, btnPlayCurrent, btnStop;
	JToggleButton btnBgmSound, btnNoteSound, btnVideoPlayback;
	JComboBox cbxMargin, cbxPlaySpeed;
	ButtonGroup grpMode;

	public static Toolbar getInstance()
	{
		if (instance == null) instance = new Toolbar();
		return instance;
	}
	
	private Toolbar()
	{
		setFloatable(false);
		setRollover(true);
		
		btnNew = new ToolbarButton("images/new.png", Language.get("toolbar.tooltip.new"));
		btnOpen = new ToolbarButton("images/open.png", Language.get("toolbar.tooltip.open"));
		btnSave = new ToolbarButton("images/save.png", Language.get("toolbar.tooltip.save"));
		btnSaveAll = new ToolbarButton("images/save_all.png", Language.get("toolbar.tooltip.save_all"));
		
		btnNew.addActionListener(this);
		btnOpen.addActionListener(this);
		btnSave.addActionListener(this);
		btnSaveAll.addActionListener(this);
		
		btnCut = new ToolbarButton("images/cut.png", Language.get("toolbar.tooltip.cut"));
		btnCopy = new ToolbarButton("images/copy.png", Language.get("toolbar.tooltip.copy"));
		btnPaste = new ToolbarButton("images/paste.png", Language.get("toolbar.tooltip.paste"));
		btnDelete = new ToolbarButton("images/delete.png", Language.get("toolbar.tooltip.delete"));
		
		btnCut.addActionListener(this);
		btnCopy.addActionListener(this);
		btnPaste.addActionListener(this);
		btnDelete.addActionListener(this);

		btnUndo = new ToolbarButton("images/undo.png", Language.get("toolbar.tooltip.undo"));
		btnRedo = new ToolbarButton("images/redo.png", Language.get("toolbar.tooltip.redo"));

		btnUndo.addActionListener(this);
		btnRedo.addActionListener(this);

		btnZoomIn = new ToolbarButton("images/zoom_in.png", Language.get("toolbar.tooltip.zoom_in"));
		btnZoomOut = new ToolbarButton("images/zoom_out.png", Language.get("toolbar.tooltip.zoom_out"));

		btnZoomIn.addActionListener(this);
		btnZoomOut.addActionListener(this);
		
		btnSelectMode = new ToolbarToggleButton("images/select.png", Language.get("toolbar.tooltip.select_mode"));
		btnEditMode = new ToolbarToggleButton("images/edit.png", Language.get("toolbar.tooltip.edit_mode"));
//		btnEditMode.setSelected(true);

		btnSelectMode.addActionListener(this);
		btnEditMode.addActionListener(this);

		btnPlay = new ToolbarButton("images/play_start.png", Language.get("toolbar.tooltip.play_from_start"));
		btnPlayCurrent = new ToolbarButton("images/play.png", Language.get("toolbar.tooltip.play"));
		btnStop = new ToolbarButton("images/stop.png", Language.get("toolbar.tooltip.stop"));
		btnBgmSound = new ToolbarToggleButton("images/bgm_sound.png", Language.get("toolbar.tooltip.bgm_sound"));
		btnBgmSound.setSelected(true);
		btnNoteSound = new ToolbarToggleButton("images/note_sound.png", Language.get("toolbar.tooltip.note_sound"));
		btnNoteSound.setSelected(true);
		btnVideoPlayback = new ToolbarToggleButton("images/video.png", Language.get("toolbar.tooltip.video_playback"));
		btnVideoPlayback.setSelected(true);

		btnPlay.addActionListener(this);
		btnPlayCurrent.addActionListener(this);
		btnStop.addActionListener(this);
		btnBgmSound.addActionListener(this);
		btnNoteSound.addActionListener(this);
		btnVideoPlayback.addActionListener(this);
		
		String[] margin = { "1/4", "1/8", "1/16", "1/24", "1/32", "1/48", "1/64", "free" };
		cbxMargin = new JComboBox(margin);
		cbxMargin.setToolTipText(Language.get("toolbar.tooltip.margin"));
		
		Double[] speed = { 1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3 };
		cbxPlaySpeed = new JComboBox(speed);
		cbxPlaySpeed.setToolTipText(Language.get("toolbar.tooltip.play_speed"));
		
		grpMode = new ButtonGroup();
		grpMode.add(btnEditMode);
		grpMode.add(btnSelectMode);

		add(btnNew);
		add(btnOpen);
		add(btnSave);
		add(btnSaveAll);
		addSeparator();
		add(btnCut);
		add(btnCopy);
		add(btnPaste);
		add(btnDelete);
		addSeparator();
		add(btnUndo);
		add(btnRedo);
		addSeparator();
//		add(btnZoomIn);
//		add(btnZoomOut);
//		addSeparator();
//		add(cbxMargin);
//		addSeparator();
		add(btnSelectMode);
		add(btnEditMode);
		addSeparator();
		add(btnPlay);
		add(btnPlayCurrent);
		add(btnStop);
		addSeparator();
		add(btnBgmSound);
		add(btnNoteSound);
		add(btnVideoPlayback);
//		addSeparator();
//		add(cbxPlaySpeed);
	}
	
	public void setMode(boolean mode)
	{
		if (mode) btnEditMode.setSelected(true);
		else btnSelectMode.setSelected(true);
	}
	
	/**
	 * Play bgm during preview.
	 * @param b set <code>true</code> to play bgm.
	 */
	public void setBgmSound(boolean b)
	{
		btnBgmSound.setSelected(b);
	}
	
	/**
	 * Play note sound during preview.
	 * @param b set <code>true</code> to play note sound.
	 */
	public void setNoteSound(boolean b)
	{
		btnNoteSound.setSelected(b);
	}
	
	/**
	 * Play video during preview.
	 * @param b set <code>true</code> to play video.
	 */
	public void setVideoPlayback(boolean b)
	{
		btnVideoPlayback.setSelected(b);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		if (obj == btnNew) ChartFrameManager.getInstance().newChart();
		else if (obj == btnOpen) ChartFrameManager.getInstance().open();
		else if (obj == btnSave) ChartFrameManager.getInstance().save();
		else if (obj == btnSaveAll) ChartFrameManager.getInstance().saveAll();
		
		else if (obj == btnCut) ;
		else if (obj == btnCopy) ;
		else if (obj == btnPaste) ;
		else if (obj == btnDelete) ;

		else if (obj == btnUndo) ;
		else if (obj == btnRedo) ;

		else if (obj == btnZoomIn) ;
		else if (obj == btnZoomOut) ;

		else if (obj == btnSelectMode) ChartFrameManager.getInstance().setMode(false);
		else if (obj == btnEditMode) ChartFrameManager.getInstance().setMode(true);

		else if (obj == btnPlay) ;
		else if (obj == btnPlayCurrent) ;
		else if (obj == btnStop) ;
		else if (obj == btnBgmSound) Player.getInstance().setBgmSound(btnBgmSound.isSelected());
		else if (obj == btnNoteSound) Player.getInstance().setNoteSound(btnNoteSound.isSelected());
		else if (obj == btnVideoPlayback) Player.getInstance().setVideoPlayback(btnVideoPlayback.isSelected());
	}
}
