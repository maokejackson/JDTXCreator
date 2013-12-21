package jdtxcreator.ui.sidebar;

import java.util.Vector;

import jdtxcreator.data.Image;
import jdtxcreator.ui.BooleanCellRenderer;

public class ImageTab extends AbstractTableTab<Image>
{
	private static final long serialVersionUID = -1372502697865953553L;

	private static ImageTab instance;

	private static final String[] header =
	{
		"No",
		"Label",
		"File",
		"Tex",
	};

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
		super(header, "image_list");
//		table.addColumn("No", RowNumber.getVector());
//		table.addColumn("Label", "");
//		table.addColumn("File", "");
//		table.addColumn("Tex", false);

		table.setCellRendererAt(new BooleanCellRenderer(), COLUMN_TEXTURE);
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
	protected Vector<Image> getData()
	{
		return dtx.getImages();
	}

	@Override
	protected void fillDataAt(Image image, int row)
	{
		table.setModelValueAt(image.getPath(), row, COLUMN_PATH);
		table.setModelValueAt(image.getLabel(), row, COLUMN_LABEL);
		table.setModelValueAt(image.isTexture(), row, COLUMN_TEXTURE);
	}

	@Override
	protected Image getDataAt(int row)
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
	protected Object[] convertToArray(Image data)
	{
		Object[] array = new Object[header.length];

		array[COLUMN_NUMBER] = data.getNumber();
		array[COLUMN_PATH] = data.getPath();
		array[COLUMN_LABEL] = data.getLabel();
		array[COLUMN_TEXTURE] = data.isTexture();

		return array;
	}

	@Override
	protected void edit(Image data)
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
