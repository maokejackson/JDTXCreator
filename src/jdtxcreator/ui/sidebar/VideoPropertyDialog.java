package jdtxcreator.ui.sidebar;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jdtxcreator.data.Video;
import jdtxcreator.ui.VideoFileChooser;

import net.miginfocom.swing.MigLayout;


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

        setLayout(new MigLayout("insets dialog,wrap 2", "[pref!]10px[min:140,grow]", "[sg]"));

        String label = "align right";
        String field = "growx";

		add(lblNumber, label);
		add(txtNumber, field);

		add(lblLabel, label);
		add(txtLabel, field);

		add(lblFile, label);
		add(fsFile, field);

		add(buttonPanel, "south");

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
