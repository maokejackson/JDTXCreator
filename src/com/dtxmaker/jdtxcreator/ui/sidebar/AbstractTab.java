package com.dtxmaker.jdtxcreator.ui.sidebar;

import javax.swing.JPanel;

import com.dtxmaker.jdtxcreator.data.DTX;

public abstract class AbstractTab extends JPanel
{
	private static final long serialVersionUID = -1206551628518677053L;

	DTX dtx;
	
	public AbstractTab()
	{
		super(true);
	}
	
	public void load(DTX dtx)
	{
		this.dtx = dtx;
		resetData();
		fillData();
	}
	
	public DTX getDTX()
	{
		return dtx;
	}
	
	/** Fill dtx data. */
	protected abstract void fillData();
	
	/** Capture dtx data. */
	public abstract void captureData();
	
	/** Reset dtx data. */
	public abstract void resetData();
}
