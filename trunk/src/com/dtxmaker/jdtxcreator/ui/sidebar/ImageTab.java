package com.dtxmaker.jdtxcreator.ui.sidebar;

import java.util.Vector;

import com.dtxmaker.jdtxcreator.Settings;
import com.dtxmaker.jdtxcreator.data.RowNumber;
import com.dtxmaker.jdtxcreator.data.Image;
import com.dtxmaker.jdtxcreator.ui.BooleanCellRenderer;

public class ImageTab extends AbstractTableTab<Image>
{
	private static final long serialVersionUID = -1372502697865953553L;
	
	private static ImageTab instance;

	private static final int COLUMN_NUMBER = 0;
	private static final int COLUMN_LABEL = 1;
	private static final int COLUMN_PATH = 2;
	private static final int COLUMN_TEXTURE = 3;
	
	public static ImageTab getInstance()
	{
		if (instance == null) instance = new ImageTab();
		return instance;
	}
	
	private ImageTab()
	{
		table.addColumn("No", RowNumber.getVector());
		table.addColumn("Label", "");
		table.addColumn("File", "");
		table.addColumn("Tex", false);
		
		table.getColumnExt(COLUMN_TEXTURE).setCellRenderer(new BooleanCellRenderer());
		
		// set default column index
		int[] index = Settings.getIntegerArray(Settings.IMAGE_LIST + Settings.COLUMN_INDEX);
		for (int i = 0; i < index.length; i++)
		{
			table.setColumnViewIndex(i, index[i]);
		}
		
		// set default column width
		int[] width = Settings.getIntegerArray(Settings.IMAGE_LIST + Settings.COLUMN_WIDTH, 75);
		for (int i = 0; i < width.length; i++)
		{
			table.setColumnWidth(i, width[i]);
		}
	}

	@Override
	public void captureData()
	{
		int size = table.getRowCount();
		Vector<Image> vector = new Vector<Image>();
		
		for (int row = 0; row < size; row++)
		{
			Image image = getDataAt(row);
			String path = image.getPath();
			if (path == null || path.length() == 0) continue;
			vector.add(image);
		}
		
		dtx.setImages(null);	// gc
		dtx.setImages(vector);
	}

	@Override
	protected void fillData()
	{
		Vector<Image> vector = dtx.getImages();
		
		if (vector == null) return;
		
		for (int i = 0; i < vector.size(); i++)
		{
			Image image = vector.get(i);
			int row = RowNumber.getRowIndex(image.getNumber());
			if (row != -1) fillDataAt(image, row);
		}
	}

	@Override
	protected void fillDataAt(Image image, int row)
	{
		table.setModelValueAt(image.getPath(), row, COLUMN_PATH);
		table.setModelValueAt(image.getLabel(), row, COLUMN_LABEL);
		table.setModelValueAt(image.isTexture(), row, COLUMN_TEXTURE);
	}

	@Override
	public Image getDataAt(int row)
	{
		String number = (String) table.getModelValueAt(row, COLUMN_NUMBER);
		String path = (String) table.getModelValueAt(row, COLUMN_PATH);
		String label = (String) table.getModelValueAt(row, COLUMN_LABEL);
		boolean texture = (Boolean) table.getModelValueAt(row, COLUMN_TEXTURE);
		
		Image image = new Image();
		image.setNumber(number);
		image.setPath(path);
		image.setLabel(label);
		image.setTexture(texture);
		
		return image;
	}

	@Override
	protected void resetDataAt(int row)
	{
		table.setModelValueAt("", row, COLUMN_PATH);
		table.setModelValueAt("", row, COLUMN_LABEL);
		table.setModelValueAt(false, row, COLUMN_TEXTURE);
	}

	@Override
	protected void openDialog(Image data)
	{
		int row = table.getSelectedRow();
		ImagePropertyDialog dialog = ImagePropertyDialog.getInstance();
		
		dialog.showDialog(data);
		if (!dialog.isApprove()) return;
		
		fillDataAt(dialog.getData(), row);
	}

	@Override
	protected void mouseClickedEvent()
	{
		// do nothing
	}
}
