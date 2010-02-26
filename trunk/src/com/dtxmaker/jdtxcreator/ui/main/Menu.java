package com.dtxmaker.jdtxcreator.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.ui.InternalFrameMenuItem;
import com.dtxmaker.jdtxcreator.ui.chart.ChartFrameManager;

public class Menu extends JMenuBar implements ActionListener
{
	private static final long serialVersionUID = 4750630756610283215L;
	
	private static Menu instance;
	
	JMenu mnuFile, mnuEdit, mnuView, mnuPlay, mnuWindow, mnuHelp;
	JMenuItem itmNew, itmOpen, itmSave, itmSaveAll, itmSaveAs, itmClose, itmCloseAll, itmExit;
	JMenuItem itmUndo, itmRedo;
	JMenuItem itmCut, itmCopy, itmPaste, itmDelete;
	JMenuItem itmSelectAll;
	JRadioButtonMenuItem itmSelectMode, itmEditMode;
	JMenuItem itmChangeMode;
	JMenuItem itmFind, itmReplace;
	JCheckBoxMenuItem itmSideBarOnLeft;
	JMenuItem itmZoomIn, itmZoomOut;
	JMenuItem itmPlay, itmPlayCurrent, itmStop;
	JCheckBoxMenuItem itmBgmSound, itmNoteSound;
	JMenuItem itmCascade, itmTileH, itmTileV;
	JMenuItem itmAbout;
	
	ButtonGroup grpWindows;
	Hashtable<JInternalFrame, InternalFrameMenuItem> charts;
	
	public static Menu getInstance()
	{
		if (instance == null) instance = new Menu();
		return instance;
	}

	private Menu()
	{
		mnuFile = new JMenu(Language.get("menu.file"));
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuEdit = new JMenu(Language.get("menu.edit"));
		mnuEdit.setMnemonic(KeyEvent.VK_E);
		mnuView = new JMenu(Language.get("menu.view"));
		mnuView.setMnemonic(KeyEvent.VK_V);
		mnuPlay = new JMenu(Language.get("menu.play"));
		mnuPlay.setMnemonic(KeyEvent.VK_P);
		mnuWindow = new JMenu(Language.get("menu.window"));
		mnuWindow.setMnemonic(KeyEvent.VK_W);
		mnuHelp = new JMenu(Language.get("menu.help"));
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		
		itmNew = new JMenuItem(Language.get("menu.file.new"), KeyEvent.VK_N);
		itmNew.setIcon(new ImageIcon("images/new.png"));
		itmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		itmOpen = new JMenuItem(Language.get("menu.file.open"), KeyEvent.VK_O);
		itmOpen.setIcon(new ImageIcon("images/open.png"));
		itmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		itmSave = new JMenuItem(Language.get("menu.file.save"), KeyEvent.VK_S);
		itmSave.setIcon(new ImageIcon("images/save.png"));
		itmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		itmSaveAs = new JMenuItem(Language.get("menu.file.save_as"), KeyEvent.VK_A);
		itmSaveAll = new JMenuItem(Language.get("menu.file.save_all"), KeyEvent.VK_E);
		itmSaveAll.setIcon(new ImageIcon("images/save_all.png"));
		itmSaveAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		itmClose = new JMenuItem(Language.get("menu.file.close"), KeyEvent.VK_C);
		itmClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		itmCloseAll = new JMenuItem(Language.get("menu.file.close_all"), KeyEvent.VK_L);
		itmExit = new JMenuItem(Language.get("menu.file.exit"), KeyEvent.VK_X);
		
		mnuFile.add(itmNew).addActionListener(this);
		mnuFile.add(itmOpen).addActionListener(this);
		mnuFile.addSeparator();
		mnuFile.add(itmSave).addActionListener(this);
		mnuFile.add(itmSaveAs).addActionListener(this);
		mnuFile.add(itmSaveAll).addActionListener(this);
		mnuFile.addSeparator();
		mnuFile.add(itmClose).addActionListener(this);
		mnuFile.add(itmCloseAll).addActionListener(this);
		mnuFile.addSeparator();
		mnuFile.add(itmExit).addActionListener(this);
		
		itmUndo = new JMenuItem(Language.get("menu.edit.undo"), KeyEvent.VK_U);
		itmUndo.setIcon(new ImageIcon("images/undo.png"));
		itmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		itmRedo = new JMenuItem(Language.get("menu.edit.redo"), KeyEvent.VK_R);
		itmRedo.setIcon(new ImageIcon("images/redo.png"));
		itmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		
		itmCut = new JMenuItem(Language.get("menu.edit.cut"), KeyEvent.VK_T);
		itmCut.setIcon(new ImageIcon("images/cut.png"));
		itmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		itmCopy = new JMenuItem(Language.get("menu.edit.copy"), KeyEvent.VK_C);
		itmCopy.setIcon(new ImageIcon("images/copy.png"));
		itmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		itmPaste = new JMenuItem(Language.get("menu.edit.paste"), KeyEvent.VK_P);
		itmPaste.setIcon(new ImageIcon("images/paste.png"));
		itmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		itmDelete = new JMenuItem(Language.get("menu.edit.delete"), KeyEvent.VK_D);
		itmDelete.setIcon(new ImageIcon("images/delete.png"));
		
		itmSelectAll = new JMenuItem(Language.get("menu.edit.select_all"), KeyEvent.VK_A);
		itmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		itmSelectMode = new JRadioButtonMenuItem(Language.get("menu.edit.select_mode"));
		itmSelectMode.setMnemonic(KeyEvent.VK_S);
		itmSelectMode.setIcon(new ImageIcon("images/select.png"));
		itmEditMode = new JRadioButtonMenuItem(Language.get("menu.edit.edit_mode"));
		itmEditMode.setMnemonic(KeyEvent.VK_E);
		itmEditMode.setIcon(new ImageIcon("images/edit.png"));
//		itmEditMode.setSelected(true);
		itmChangeMode = new JMenuItem(Language.get("menu.edit.change_mode"), KeyEvent.VK_M);
		itmChangeMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		
		ButtonGroup grpMode = new ButtonGroup();
		grpMode.add(itmSelectMode);
		grpMode.add(itmEditMode);
		
		itmFind = new JMenuItem(Language.get("menu.edit.find"), KeyEvent.VK_F);
		itmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		itmReplace = new JMenuItem(Language.get("menu.edit.replace"), KeyEvent.VK_H);
		itmReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		
		mnuEdit.add(itmUndo).addActionListener(this);
		mnuEdit.add(itmRedo).addActionListener(this);
		mnuEdit.addSeparator();
		mnuEdit.add(itmCut).addActionListener(this);
		mnuEdit.add(itmCopy).addActionListener(this);
		mnuEdit.add(itmPaste).addActionListener(this);
		mnuEdit.add(itmDelete).addActionListener(this);
		mnuEdit.addSeparator();
		mnuEdit.add(itmSelectAll).addActionListener(this);
		mnuEdit.addSeparator();
		mnuEdit.add(itmSelectMode).addActionListener(this);
		mnuEdit.add(itmEditMode).addActionListener(this);
		mnuEdit.add(itmChangeMode).addActionListener(this);
		mnuEdit.addSeparator();
		mnuEdit.add(itmFind).addActionListener(this);
		mnuEdit.add(itmReplace).addActionListener(this);
		
		itmSideBarOnLeft = new JCheckBoxMenuItem(Language.get("menu.view.sidebar_on_left"));
		itmSideBarOnLeft.setMnemonic(KeyEvent.VK_S);
		itmSideBarOnLeft.setSelected(Settings.getSidebarPosition());
		itmZoomIn = new JMenuItem(Language.get("menu.view.zoom_in"), KeyEvent.VK_I);
		itmZoomIn.setIcon(new ImageIcon("images/zoom_in.png"));
		itmZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		itmZoomOut = new JMenuItem(Language.get("menu.view.zoom_out"), KeyEvent.VK_O);
		itmZoomOut.setIcon(new ImageIcon("images/zoom_out.png"));
		itmZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		
		mnuView.add(itmSideBarOnLeft).addActionListener(this);
		mnuView.addSeparator();
		mnuView.add(itmZoomIn).addActionListener(this);
		mnuView.add(itmZoomOut).addActionListener(this);
		
		itmPlay = new JMenuItem(Language.get("menu.play.play_from_start"), KeyEvent.VK_T);
		itmPlay.setIcon(new ImageIcon("images/play_start.png"));
		itmPlay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		itmPlayCurrent = new JMenuItem(Language.get("menu.play.play_from_current_part"), KeyEvent.VK_P);
		itmPlayCurrent.setIcon(new ImageIcon("images/play.png"));
		itmPlayCurrent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		itmStop = new JMenuItem(Language.get("menu.play.stop"), KeyEvent.VK_S);
		itmStop.setIcon(new ImageIcon("images/stop.png"));
		itmStop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		itmBgmSound = new JCheckBoxMenuItem(Language.get("menu.play.bgm_sound"));
		itmBgmSound.setIcon(new ImageIcon("images/bgm_sound.png"));
		itmBgmSound.setMnemonic(KeyEvent.VK_B);
		itmBgmSound.setSelected(true);
		itmNoteSound = new JCheckBoxMenuItem(Language.get("menu.play.note_sound"));
		itmNoteSound.setIcon(new ImageIcon("images/note_sound.png"));
		itmNoteSound.setMnemonic(KeyEvent.VK_N);
		itmNoteSound.setSelected(true);
		
		mnuPlay.add(itmPlay).addActionListener(this);
		mnuPlay.add(itmPlayCurrent).addActionListener(this);
		mnuPlay.add(itmStop).addActionListener(this);
		mnuPlay.addSeparator();
		mnuPlay.add(itmBgmSound).addActionListener(this);
		mnuPlay.add(itmNoteSound).addActionListener(this);
		
		itmCascade = new JMenuItem(Language.get("menu.window.cascade"), KeyEvent.VK_C);
		itmCascade.setIcon(new ImageIcon("images/cascade.png"));
		itmTileH = new JMenuItem(Language.get("menu.window.tile_horizontally"), KeyEvent.VK_H);
		itmTileH.setIcon(new ImageIcon("images/tile_horizontal.png"));
		itmTileV = new JMenuItem(Language.get("menu.window.tile_vertically"), KeyEvent.VK_V);
		itmTileV.setIcon(new ImageIcon("images/tile_vertical.png"));
		grpWindows = new ButtonGroup();
		charts = new Hashtable<JInternalFrame, InternalFrameMenuItem>();
		
		mnuWindow.add(itmCascade).addActionListener(this);
		mnuWindow.add(itmTileH).addActionListener(this);
		mnuWindow.add(itmTileV).addActionListener(this);
		mnuWindow.addSeparator();
		
		itmAbout = new JMenuItem(Language.get("menu.help.about"), KeyEvent.VK_A);
		
		mnuHelp.add(itmAbout).addActionListener(this);
		
		add(mnuFile);
		add(mnuEdit);
		add(mnuView);
		add(mnuPlay);
		add(mnuWindow);
		add(mnuHelp);
	}
	
	public void setMode(boolean mode)
	{
		if (mode) itmEditMode.setSelected(true);
		else itmSelectMode.setSelected(true);
	}
	
	/**
	 * Play bgm during preview.
	 * @param b set <code>true</code> to play bgm.
	 */
	public void setBgmSound(boolean b)
	{
		itmBgmSound.setSelected(b);
	}
	
	/**
	 * Play note sound during preview.
	 * @param b set <code>true</code> to play note sound.
	 */
	public void setNoteSound(boolean b)
	{
		itmNoteSound.setSelected(b);
	}
	
	public void addFrame(JInternalFrame frame)
	{
		if (frame == null) return;
		
		InternalFrameMenuItem item = new InternalFrameMenuItem(frame);
		mnuWindow.add(item);
		grpWindows.add(item);
		charts.put(frame, item);
		item.setSelected(true);
	}
	
	public void removeFrame(JInternalFrame frame)
	{
		if (frame == null) return;
		
		InternalFrameMenuItem item = charts.get(frame);
		if (item != null)
		{
			item.setSelected(false);
			mnuWindow.remove(item);
			grpWindows.remove(item);
			charts.remove(frame);
		}
	}
	
	public void setSelectedFrame(JInternalFrame frame)
	{
		if (frame == null) return;
		
		InternalFrameMenuItem item = charts.get(frame);
		if (item != null) item.setSelected(true);
	}
	
	public void renameFrame(JInternalFrame frame)
	{
		if (frame == null) return;
		
		InternalFrameMenuItem item = charts.get(frame);
		if (item != null) item.setText(frame.getTitle());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		if (obj == itmNew) ChartFrameManager.getInstance().newChart();
		else if (obj == itmOpen) ChartFrameManager.getInstance().open();
		else if (obj == itmSave) ChartFrameManager.getInstance().save();
		else if (obj == itmSaveAs) ChartFrameManager.getInstance().saveAs();
		else if (obj == itmSaveAll) ChartFrameManager.getInstance().saveAll();
		else if (obj == itmClose) ChartFrameManager.getInstance().close();
		else if (obj == itmCloseAll) ChartFrameManager.getInstance().closeAll();
		else if (obj == itmExit) Main.getInstance().exit();
		
		else if (obj == itmUndo) ;
		else if (obj == itmRedo) ;
		else if (obj == itmCut) ;
		else if (obj == itmCopy) ;
		else if (obj == itmPaste) ;
		else if (obj == itmDelete) ;
		
		else if (obj == itmSelectAll) ;
		else if (obj == itmSelectMode) ChartFrameManager.getInstance().setMode(false);
		else if (obj == itmEditMode) ChartFrameManager.getInstance().setMode(true);
		else if (obj == itmChangeMode) ChartFrameManager.getInstance().switchMode();
		else if (obj == itmFind) ;
		else if (obj == itmReplace) ;
		
		else if (obj == itmSideBarOnLeft) Main.getInstance().switchSideBar();
		else if (obj == itmZoomIn) ;
		else if (obj == itmZoomOut) ;

		else if (obj == itmPlay) ;
		else if (obj == itmPlayCurrent) ;
		else if (obj == itmStop) ;
		else if (obj == itmBgmSound) Toolbar.getInstance().setBgmSound(itmBgmSound.isSelected());
		else if (obj == itmNoteSound) Toolbar.getInstance().setNoteSound(itmNoteSound.isSelected());

		else if (obj == itmCascade) ChartFrameManager.getInstance().cascade();
		else if (obj == itmTileH) ChartFrameManager.getInstance().tileHorizontally();
		else if (obj == itmTileV) ChartFrameManager.getInstance().tileVertically();
		
		else if (obj == itmAbout) AboutDialog.getInstance().setVisible(true);
	}
}
