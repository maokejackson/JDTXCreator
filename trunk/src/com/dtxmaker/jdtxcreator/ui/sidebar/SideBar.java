package com.dtxmaker.jdtxcreator.ui.sidebar;

import javax.swing.JTabbedPane;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.data.DTX;

public class SideBar extends JTabbedPane
{
	private static final long serialVersionUID = -1336890197927249545L;
	
	private static SideBar instance;
	
	GeneralTab general;
	AudioTab audio;
	ImageTab image;
	VideoTab video;
	FreeTab free;
	BpmTab bpm;

	public static SideBar getInstance()
	{
		if (instance == null) instance = new SideBar();
		return instance;
	}
	
	private SideBar()
	{
		setFocusable(false); 
		add(Language.get("sidebar.tab.general"), general = GeneralTab.getInstance());
		add(Language.get("sidebar.tab.audio"), audio = AudioTab.getInstance());
		add(Language.get("sidebar.tab.image"), image = ImageTab.getInstance());
		add(Language.get("sidebar.tab.video"), video = VideoTab.getInstance());
		add(Language.get("sidebar.tab.free"), free = FreeTab.getInstance());
		bpm = BpmTab.getInstance();
	}
	
	public void load(DTX dtx)
	{
		general.load(dtx);
		audio.load(dtx);
		image.load(dtx);
		video.load(dtx);
		free.load(dtx);
		bpm.load(dtx);
	}
	
	public void captureData()
	{
		general.captureData();
		audio.captureData();
		image.captureData();
		video.captureData();
		free.captureData();
		bpm.captureData();
	}
	
	public void resetData()
	{
		general.resetData();
		audio.resetData();
		image.resetData();
		video.resetData();
		free.resetData();
		bpm.resetData();
	}
}
