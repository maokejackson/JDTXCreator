package com.dtxmaker.jdtxcreator.ui.sidebar;

import com.dtxmaker.jdtxcreator.data.BPM;
import com.dtxmaker.jdtxcreator.data.RowNumber;

public class BpmTab extends AbstractTableTab<BPM>
{
	private static final long serialVersionUID = -1757109947485021591L;
	
	private static BpmTab instance;

	public static BpmTab getInstance()
	{
		if (instance == null) instance = new BpmTab();
		return instance;
	}
	
	private BpmTab()
	{
		table.addColumn("No", RowNumber.getVector());
		table.addColumn("BPM", -1);
	}

	@Override
	public void captureData()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillData()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillDataAt(BPM data, int row)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BPM getDataAt(int row)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetDataAt(int row)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void openDialog(BPM data)
	{
		
	}

	@Override
	protected void mouseClickedEvent()
	{
		
	}
}
