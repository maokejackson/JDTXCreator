package jdtxcreator.ui.sidebar;

import java.util.Vector;

import jdtxcreator.data.BPM;

public class BpmTab extends AbstractTableTab<BPM>
{
	private static final long serialVersionUID = -1757109947485021591L;

	private static BpmTab instance;

	private static final String[] header =
	{
		"No",
		"BPM",
	};

	public static BpmTab getInstance()
	{
		if (instance == null) instance = new BpmTab();
		return instance;
	}

	private BpmTab()
	{
		super(header, "bpm_list");
//		table.addColumn("No", RowNumber.getVector());
//		table.addColumn("BPM", -1);
	}

	@Override
	public void captureData()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected Vector<BPM> getData()
	{
		return dtx.getBPMs();
	}

	@Override
	protected void fillDataAt(BPM data, int row)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected BPM getDataAt(int row)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] convertToArray(BPM data)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void edit(BPM data)
	{

	}

	@Override
	protected void mouseClickedEvent()
	{

	}

	@Override
	protected void add()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void remove()
	{
		// TODO Auto-generated method stub

	}
}
