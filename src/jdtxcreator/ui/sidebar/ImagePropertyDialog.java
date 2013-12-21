package jdtxcreator.ui.sidebar;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jdtxcreator.data.Image;
import jdtxcreator.ui.ImageFileChooser;
import net.miginfocom.swing.MigLayout;

public class ImagePropertyDialog extends AbstractPropertyDialog<Image>
{
	private static final long serialVersionUID = -2835270657673513991L;

	private static ImagePropertyDialog instance;

	JLabel lblNumber, lblLabel, lblFile;
	JTextField txtNumber, txtLabel;
	ImageFileChooser fsFile;
	JCheckBox chkTexture;

	public static ImagePropertyDialog getInstance()
	{
		if (instance == null) instance = new ImagePropertyDialog();
		return instance;
	}

	private ImagePropertyDialog()
	{
		setTitle("Image Property");

		lblNumber = new JLabel("Image No.");
		lblLabel = new JLabel("Label");
		lblFile = new JLabel("File");

		txtNumber = new JTextField();
		txtNumber.setEditable(false);
		txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtLabel = new JTextField();

		fsFile = new ImageFileChooser();
		chkTexture = new JCheckBox("Texture");

        setLayout(new MigLayout("insets dialog,wrap 2", "[pref!]10px[min:140,grow]", "[sg]"));

        String label = "align right";
        String field = "growx";

		add(lblNumber, label);
		add(txtNumber, field);

		add(lblLabel, label);
		add(txtLabel, field);

		add(lblFile, label);
		add(fsFile, field);

		add(chkTexture, "skip");

		add(buttonPanel, "south");

		pack();
		Dimension dimension = getPreferredSize();
		dimension.width = 330;
		setSize(dimension);
		setLocationRelativeTo(ImageTab.getInstance());
	}

	@Override
	protected void captureData()
	{
		if (data == null) data = new Image();

		data.setNumber(txtNumber.getText());
		data.setLabel(txtLabel.getText());
		data.setPath(fsFile.getPath());
		data.setTexture(chkTexture.isSelected());
	}

	@Override
	protected void fillData(Image data)
	{
		txtNumber.setText(data.getNumber());
		txtLabel.setText(data.getLabel().trim());
		fsFile.setPath(data.getPath().trim());
		chkTexture.setSelected(data.isTexture());
		txtLabel.requestFocus();
	}

	@Override
	public void resetData()
	{
		txtNumber.setText("");
		txtLabel.setText("");
		fsFile.setPath("");
		chkTexture.setSelected(false);
	}
}
