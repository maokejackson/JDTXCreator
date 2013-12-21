package jdtxcreator.ui.sidebar;

import javax.swing.JPanel;

import jdtxcreator.data.DTX;

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
		clearData();
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

	/** Clear dtx data. */
	public abstract void clearData();
}
