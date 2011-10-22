package jdtxcreator.ui.sidebar;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import jdtxcreator.Language;
import jdtxcreator.data.DTX;


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
		
		general = GeneralTab.getInstance();
		audio = AudioTab.getInstance();
		image = ImageTab.getInstance();
		video = VideoTab.getInstance();
		free = FreeTab.getInstance();
		bpm = BpmTab.getInstance();
		
		JScrollPane scrollPane = new JScrollPane(general);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(Language.get("sidebar.tab.general"), general);
		add(Language.get("sidebar.tab.audio"), audio);
		add(Language.get("sidebar.tab.image"), image);
		add(Language.get("sidebar.tab.video"), video);
		add(Language.get("sidebar.tab.free"), free);
		
		setEnabled(false);
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
		general.clearData();
		audio.clearData();
		image.clearData();
		video.clearData();
		free.clearData();
		bpm.clearData();
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		general.setEnabled(enabled);
		audio.setEnabled(enabled);
		image.setEnabled(enabled);
		video.setEnabled(enabled);
		free.setEnabled(enabled);
		bpm.setEnabled(enabled);
	}
}
