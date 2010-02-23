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

import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.ui.InternalFrameMenuItem;
import com.dtxmaker.jdtxcreator.ui.chart.InternalChartManager;

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
		mnuFile = new JMenu("File");
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuEdit = new JMenu("Edit");
		mnuEdit.setMnemonic(KeyEvent.VK_E);
		mnuView = new JMenu("View");
		mnuView.setMnemonic(KeyEvent.VK_V);
		mnuPlay = new JMenu("Play");
		mnuPlay.setMnemonic(KeyEvent.VK_P);
		mnuWindow = new JMenu("Window");
		mnuWindow.setMnemonic(KeyEvent.VK_W);
		mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		
		itmNew = new JMenuItem("New", KeyEvent.VK_N);
		itmNew.setIcon(new ImageIcon("images/new.png"));
		itmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		itmOpen = new JMenuItem("Open...", KeyEvent.VK_O);
		itmOpen.setIcon(new ImageIcon("images/open.png"));
		itmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		itmSave = new JMenuItem("Save", KeyEvent.VK_S);
		itmSave.setIcon(new ImageIcon("images/save.png"));
		itmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		itmSaveAs = new JMenuItem("Save as...", KeyEvent.VK_A);
		itmSaveAll = new JMenuItem("Save all", KeyEvent.VK_E);
		itmSaveAll.setIcon(new ImageIcon("images/save_all.png"));
		itmSaveAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		itmClose = new JMenuItem("Close", KeyEvent.VK_C);
		itmClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		itmCloseAll = new JMenuItem("Close all", KeyEvent.VK_L);
		itmExit = new JMenuItem("Exit", KeyEvent.VK_X);
		
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
		
		itmUndo = new JMenuItem("Undo", KeyEvent.VK_U);
		itmUndo.setIcon(new ImageIcon("images/undo.png"));
		itmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		itmRedo = new JMenuItem("Redo", KeyEvent.VK_R);
		itmRedo.setIcon(new ImageIcon("images/redo.png"));
		itmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		
		itmCut = new JMenuItem("Cut", KeyEvent.VK_T);
		itmCut.setIcon(new ImageIcon("images/cut.png"));
		itmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		itmCopy = new JMenuItem("Copy", KeyEvent.VK_C);
		itmCopy.setIcon(new ImageIcon("images/copy.png"));
		itmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		itmPaste = new JMenuItem("Paste", KeyEvent.VK_P);
		itmPaste.setIcon(new ImageIcon("images/paste.png"));
		itmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		itmDelete = new JMenuItem("Delete", KeyEvent.VK_D);
		itmDelete.setIcon(new ImageIcon("images/delete.png"));
		
		itmSelectAll = new JMenuItem("Select All", KeyEvent.VK_A);
		itmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		itmSelectMode = new JRadioButtonMenuItem("Select Mode");
		itmSelectMode.setMnemonic(KeyEvent.VK_S);
		itmSelectMode.setIcon(new ImageIcon("images/select.png"));
		itmEditMode = new JRadioButtonMenuItem("Edit Mode");
		itmEditMode.setMnemonic(KeyEvent.VK_E);
		itmEditMode.setIcon(new ImageIcon("images/edit.png"));
//		itmEditMode.setSelected(true);
		itmChangeMode = new JMenuItem("Change Mode", KeyEvent.VK_M);
		itmChangeMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		
		ButtonGroup grpMode = new ButtonGroup();
		grpMode.add(itmSelectMode);
		grpMode.add(itmEditMode);
		
		itmFind = new JMenuItem("Find...", KeyEvent.VK_F);
		itmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		itmReplace = new JMenuItem("Replace...", KeyEvent.VK_H);
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
		
		itmSideBarOnLeft = new JCheckBoxMenuItem("Sidebar on left");
		itmSideBarOnLeft.setMnemonic(KeyEvent.VK_S);
		itmSideBarOnLeft.setSelected(Settings.getSidebarPosition());
		itmZoomIn = new JMenuItem("Zoom In", KeyEvent.VK_I);
		itmZoomIn.setIcon(new ImageIcon("images/zoom_in.png"));
		itmZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		itmZoomOut = new JMenuItem("Zoom Out", KeyEvent.VK_O);
		itmZoomOut.setIcon(new ImageIcon("images/zoom_out.png"));
		itmZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		
		mnuView.add(itmSideBarOnLeft).addActionListener(this);
		mnuView.addSeparator();
		mnuView.add(itmZoomIn).addActionListener(this);
		mnuView.add(itmZoomOut).addActionListener(this);
		
		itmPlay = new JMenuItem("Play from start", KeyEvent.VK_T);
		itmPlay.setIcon(new ImageIcon("images/play_start.png"));
		itmPlay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		itmPlayCurrent = new JMenuItem("Play from current part", KeyEvent.VK_P);
		itmPlayCurrent.setIcon(new ImageIcon("images/play.png"));
		itmPlayCurrent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		itmStop = new JMenuItem("Stop", KeyEvent.VK_S);
		itmStop.setIcon(new ImageIcon("images/stop.png"));
		itmStop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		itmBgmSound = new JCheckBoxMenuItem("Bgm sound");
		itmBgmSound.setIcon(new ImageIcon("images/bgm_sound.png"));
		itmBgmSound.setMnemonic(KeyEvent.VK_B);
		itmBgmSound.setSelected(true);
		itmNoteSound = new JCheckBoxMenuItem("Note sound");
		itmNoteSound.setIcon(new ImageIcon("images/note_sound.png"));
		itmNoteSound.setMnemonic(KeyEvent.VK_N);
		itmNoteSound.setSelected(true);
		
		mnuPlay.add(itmPlay).addActionListener(this);
		mnuPlay.add(itmPlayCurrent).addActionListener(this);
		mnuPlay.add(itmStop).addActionListener(this);
		mnuPlay.addSeparator();
		mnuPlay.add(itmBgmSound).addActionListener(this);
		mnuPlay.add(itmNoteSound).addActionListener(this);
		
		itmCascade = new JMenuItem("Cascade", KeyEvent.VK_C);
		itmCascade.setIcon(new ImageIcon("images/cascade.png"));
		itmTileH = new JMenuItem("Tile Horizontally", KeyEvent.VK_H);
		itmTileH.setIcon(new ImageIcon("images/tile_horizontal.png"));
		itmTileV = new JMenuItem("Tile Vertically", KeyEvent.VK_V);
		itmTileV.setIcon(new ImageIcon("images/tile_vertical.png"));
		grpWindows = new ButtonGroup();
		charts = new Hashtable<JInternalFrame, InternalFrameMenuItem>();
		
		mnuWindow.add(itmCascade).addActionListener(this);
		mnuWindow.add(itmTileH).addActionListener(this);
		mnuWindow.add(itmTileV).addActionListener(this);
		mnuWindow.addSeparator();
		
		itmAbout = new JMenuItem("About", KeyEvent.VK_A);
		
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
		
		if (obj == itmNew) InternalChartManager.getInstance().newChart();
		else if (obj == itmOpen) InternalChartManager.getInstance().open();
		else if (obj == itmSave) InternalChartManager.getInstance().save();
		else if (obj == itmSaveAs) InternalChartManager.getInstance().saveAs();
		else if (obj == itmSaveAll) InternalChartManager.getInstance().saveAll();
		else if (obj == itmClose) InternalChartManager.getInstance().close();
		else if (obj == itmCloseAll) InternalChartManager.getInstance().closeAll();
		else if (obj == itmExit) Main.getInstance().exit();
		
		else if (obj == itmUndo) ;
		else if (obj == itmRedo) ;
		else if (obj == itmCut) ;
		else if (obj == itmCopy) ;
		else if (obj == itmPaste) ;
		else if (obj == itmDelete) ;
		
		else if (obj == itmSelectAll) ;
		else if (obj == itmSelectMode) InternalChartManager.getInstance().setMode(false);
		else if (obj == itmEditMode) InternalChartManager.getInstance().setMode(true);
		else if (obj == itmChangeMode) InternalChartManager.getInstance().switchMode();
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

		else if (obj == itmCascade) InternalChartManager.getInstance().cascade();
		else if (obj == itmTileH) InternalChartManager.getInstance().tileHorizontally();
		else if (obj == itmTileV) InternalChartManager.getInstance().tileVertically();
		
		else if (obj == itmAbout) AboutDialog.getInstance().setVisible(true);
	}
}
