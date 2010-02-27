package com.dtxmaker.jdtxcreator.ui.sidebar;

import info.clearthought.layout.TableLayout;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.dtxmaker.jdtxcreator.data.Video;
import com.dtxmaker.jdtxcreator.ui.VideoFileChooser;

public class VideoPropertyDialog extends AbstractPropertyDialog<Video>
{
	private static final long serialVersionUID = -2835270657673513991L;

	private static VideoPropertyDialog instance;
	
	JLabel lblNumber, lblLabel, lblFile;
	JTextField txtNumber, txtLabel;
	VideoFileChooser fsFile;

	public static VideoPropertyDialog getInstance()
	{
		if (instance == null) instance = new VideoPropertyDialog();
		return instance;
	}
	
	private VideoPropertyDialog()
	{
		setTitle("Video Property");
		
		lblNumber = new JLabel("Video No.");
		lblLabel = new JLabel("Label");
		lblFile = new JLabel("File");
		
		txtNumber = new JTextField();
		txtNumber.setEditable(false);
		txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtLabel = new JTextField();
		
		fsFile = new VideoFileChooser();
		
		double b = 10;
		double f = TableLayout.FILL;
		double p = TableLayout.PREFERRED;
		double vg = 5;

		double size[][] = {
				{ b, p, b, f, b },
				{ b, p, vg, p, vg, p, vg, p, vg }
		};
		
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(lblNumber, "1, 1");
		add(txtNumber, "3, 1");
		add(lblLabel, "1, 3");
		add(txtLabel, "3, 3");
		add(lblFile, "1, 5");
		add(fsFile, "3, 5");
		add(buttonPanel, "1, 7, 3, 7");
		
		pack();
		Dimension dimension = getPreferredSize();
		dimension.width = 330;
		setSize(dimension);
		setLocationRelativeTo(VideoTab.getInstance());
	}

	@Override
	protected void captureData()
	{
		if (data == null) data = new Video();
		
		data.setNumber(txtNumber.getText());
		data.setLabel(txtLabel.getText().trim());
		data.setPath(fsFile.getPath().trim());
	}

	@Override
	protected void fillData(Video data)
	{
		txtNumber.setText(data.getNumber());
		txtLabel.setText(data.getLabel());
		fsFile.setPath(data.getPath());
		txtLabel.requestFocus();
	}

	@Override
	public void resetData()
	{
		txtNumber.setText("");
		txtLabel.setText("");
		fsFile.setPath("");
	}
}
