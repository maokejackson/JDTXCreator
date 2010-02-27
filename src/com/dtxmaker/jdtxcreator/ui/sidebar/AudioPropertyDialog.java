package com.dtxmaker.jdtxcreator.ui.sidebar;

import info.clearthought.layout.TableLayout;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.data.Audio;
import com.dtxmaker.jdtxcreator.ui.AudioFileChooser;
import com.dtxmaker.jdtxcreator.ui.LevelController;

public class AudioPropertyDialog extends AbstractPropertyDialog<Audio>
{
	private static final long serialVersionUID = 5344450580326623215L;
	
	private static AudioPropertyDialog instance;
	
	JLabel lblNumber, lblLabel, lblFile, lblVolume, lblPosition, lblSize;
	JTextField txtNumber, txtLabel;
	AudioFileChooser fsFile;
	JCheckBox chkBgm;
	LevelController lvlVolume, lvlPosition, lvlSize;

	public static AudioPropertyDialog getInstance()
	{
		if (instance == null) instance = new AudioPropertyDialog();
		return instance;
	}
	
	private AudioPropertyDialog()
	{
		setTitle(Language.get("audio.dialog.title"));
		setModal(true);
		setResizable(false);
		
		lblNumber = new JLabel(Language.get("audio.dialog.number"));
		lblNumber.setToolTipText(Language.get("audio.dialog.tooltip.number"));
		lblLabel = new JLabel(Language.get("audio.dialog.label"));
		lblLabel.setToolTipText(Language.get("audio.dialog.tooltip.label"));
		lblFile = new JLabel(Language.get("audio.dialog.file"));
		lblFile.setToolTipText(Language.get("audio.dialog.tooltip.file"));
		lblVolume = new JLabel(Language.get("audio.dialog.volume"));
		lblVolume.setToolTipText(Language.get("audio.dialog.tooltip.volume"));
		lblPosition = new JLabel(Language.get("audio.dialog.position"));
		lblPosition.setToolTipText(Language.get("audio.dialog.tooltip.position"));
		lblSize = new JLabel(Language.get("audio.dialog.size"));
		lblSize.setToolTipText(Language.get("audio.dialog.tooltip.size"));
		
		txtNumber = new JTextField();
		txtNumber.setEditable(false);
		txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumber.setToolTipText(Language.get("audio.dialog.tooltip.number"));
		
		txtLabel = new JTextField();
		txtLabel.setToolTipText(Language.get("audio.dialog.tooltip.label"));
		fsFile = new AudioFileChooser();
		fsFile.setToolTipText(Language.get("audio.dialog.tooltip.file"));
		chkBgm = new JCheckBox(Language.get("audio.dialog.bgm"));
		chkBgm.setToolTipText(Language.get("audio.dialog.tooltip.bgm"));
		
		lvlVolume = new LevelController(100);
		lvlVolume.setToolTipText(Language.get("audio.dialog.tooltip.volume"));
		lvlPosition = new LevelController(0, -100, 100);
		lvlPosition.setToolTipText(Language.get("audio.dialog.tooltip.position"));
		lvlSize = new LevelController(100, 1, 100);
		lvlSize.setToolTipText(Language.get("audio.dialog.tooltip.size"));
		
		double b = 10;
		double f = TableLayout.FILL;
		double p = TableLayout.PREFERRED;
		double vg = 5;

		double size[][] = {
				{ b, p, b, f, b },
				{ b, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, b, p, vg }
		};
		
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(lblNumber, "1, 1");
		add(txtNumber, "3, 1");
		add(lblLabel, "1, 3");
		add(txtLabel, "3, 3");
		add(lblFile, "1, 5");
		add(fsFile, "3, 5");
		add(chkBgm, "3, 7");
		add(lblVolume, "1, 9");
		add(lvlVolume, "3, 9");
		add(lblPosition, "1, 11");
		add(lvlPosition, "3, 11");
		add(lblSize, "1, 13");
		add(lvlSize, "3, 13");
		add(buttonPanel, "1, 15, 3, 15");
		
		pack();
		Dimension dimension = getPreferredSize();
		dimension.width = 330;
		setSize(dimension);
		setLocationRelativeTo(AudioTab.getInstance());
	}

	@Override
	protected void captureData()
	{
		if (data == null) data = new Audio();
		
		data.setNumber(txtNumber.getText());
		data.setLabel(txtLabel.getText().trim());
		data.setPath(fsFile.getPath().trim());
		data.setBgm(chkBgm.isSelected());
		data.setVolume(lvlVolume.getValue());
		data.setPosition(lvlPosition.getValue());
		data.setSize(lvlSize.getValue());
	}

	@Override
	protected void fillData(Audio data)
	{
		txtNumber.setText(data.getNumber());
		txtLabel.setText(data.getLabel());
		fsFile.setPath(data.getPath());
		chkBgm.setSelected(data.isBgm());
		lvlVolume.setValue(data.getVolume());
		lvlPosition.setValue(data.getPosition());
		lvlSize.setValue(data.getSize());
		txtLabel.requestFocus();
	}

	@Override
	public void resetData()
	{
		txtNumber.setText("");
		txtLabel.setText("");
		fsFile.setPath("");
		chkBgm.setSelected(false);
		lvlVolume.setValue(100);
		lvlPosition.setValue(0);
		lvlSize.setValue(100);
	}
}
