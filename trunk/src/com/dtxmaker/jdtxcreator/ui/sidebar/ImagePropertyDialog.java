package com.dtxmaker.jdtxcreator.ui.sidebar;

import info.clearthought.layout.TableLayout;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.dtxmaker.jdtxcreator.data.Image;
import com.dtxmaker.jdtxcreator.ui.ImageFileChooser;

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
		
		double b = 10;
		double f = TableLayout.FILL;
		double p = TableLayout.PREFERRED;
		double vg = 5;

		double size[][] = {
				{ b, p, b, f, b },
				{ b, p, vg, p, vg, p, vg, p, vg, p, vg }
		};
		
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(lblNumber, "1, 1");
		add(txtNumber, "3, 1");
		add(lblLabel, "1, 3");
		add(txtLabel, "3, 3");
		add(lblFile, "1, 5");
		add(fsFile, "3, 5");
		add(chkTexture, "3, 7");
		add(buttonPanel, "1, 9, 3, 9");
		
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
		txtLabel.setText(data.getLabel());
		fsFile.setPath(data.getPath());
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
