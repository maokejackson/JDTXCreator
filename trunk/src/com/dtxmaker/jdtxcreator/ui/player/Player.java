package com.dtxmaker.jdtxcreator.ui.player;

import javax.swing.JFrame;

import com.dtxmaker.jdtxcreator.ui.main.Menu;
import com.dtxmaker.jdtxcreator.ui.main.Toolbar;

public class Player extends JFrame
{
	private static final long serialVersionUID = -3653581970592171072L;

	private static Player instance;
	
	boolean bgmSound, noteSound, videoPlayback;

	public static Player getInstance()
	{
		if (instance == null) instance = new Player();
		return instance;
	}
	
	private Player()
	{
		super("DTXPlayer");
		
		setSize(640, 480);
		setVisible(true);
	}
	
	/**
	 * Play bgm during preview.
	 * @param b set <code>true</code> to play bgm.
	 */
	public void setBgmSound(boolean b)
	{
		bgmSound = b;
		Menu.getInstance().setBgmSound(b);
		Toolbar.getInstance().setBgmSound(b);
	}
	
	/**
	 * Play note sound during preview.
	 * @param b set <code>true</code> to play note sound.
	 */
	public void setNoteSound(boolean b)
	{
		noteSound = b;
		Menu.getInstance().setNoteSound(b);
		Toolbar.getInstance().setNoteSound(b);
	}
	
	public void setVideoPlayback(boolean b)
	{
		videoPlayback = b;
		Menu.getInstance().setVideoPlayback(b);
		Toolbar.getInstance().setVideoPlayback(b);
	}
}
