package com.dtxmaker.jdtxcreator.ui.sidebar;

import info.clearthought.layout.TableLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.ui.AudioFileChooser;
import com.dtxmaker.jdtxcreator.ui.ImageFileChooser;
import com.dtxmaker.jdtxcreator.ui.LevelController;

public class GeneralTab extends AbstractTab
{
	private static final long serialVersionUID = 6547860180111739155L;
	
	private static GeneralTab instance;
	
	JLabel lblTitle, lblArtist, lblGenre, lblComment, lblPanel;
	JLabel lblBpm, lblDrumLv, lblGuitarLv, lblBassLv;
	JLabel lblPreviewSound, lblPreviewImage, lblLoadingImage, lblBackgroundImage, lblResultImage;
	JTextField txtTitle, txtArtist, txtGenre, txtComment, txtPanel;
	JTextField txtBpm;
	JSpinner spnBpm;
	LevelController lvlDrum, lvlGuitar, lvlBass;
	JCheckBox chkHiddenLevel;
	AudioFileChooser fsPreviewSound;
	ImageFileChooser fsPreviewImage, fsLoadingImage, fsBackgroundImage, fsResultImage;

	public static GeneralTab getInstance()
	{
		if (instance == null) instance = new GeneralTab();
		return instance;
	}
	
	private GeneralTab()
	{
		lblTitle = getJLabel(Language.get("general.label.title"));
		lblTitle.setToolTipText(Language.get("general.tooltip.title"));
		lblArtist = getJLabel(Language.get("general.label.artist"));
		lblArtist.setToolTipText(Language.get("general.tooltip.artist"));
		lblGenre = getJLabel(Language.get("general.label.genre"));
		lblGenre.setToolTipText(Language.get("general.tooltip.genre"));
		lblComment = getJLabel(Language.get("general.label.comment"));
		lblComment.setToolTipText(Language.get("general.tooltip.comment"));
		lblPanel = getJLabel(Language.get("general.label.panel"));
		lblPanel.setToolTipText(Language.get("general.tooltip.panel"));
		
		lblBpm = getJLabel(Language.get("general.label.bpm"));
		lblBpm.setToolTipText(Language.get("general.tooltip.bpm"));
		lblDrumLv = getJLabel(Language.get("general.label.drum_level"));
		lblDrumLv.setToolTipText(Language.get("general.tooltip.drum_level"));
		lblGuitarLv = getJLabel(Language.get("general.label.guitar_level"));
		lblGuitarLv.setToolTipText(Language.get("general.tooltip.guitar_level"));
		lblBassLv = getJLabel(Language.get("general.label.bass_level"));
		lblBassLv.setToolTipText(Language.get("general.tooltip.bass_level"));
		
		lblPreviewSound = getJLabel(Language.get("general.label.preview_sound"));
		lblPreviewSound.setToolTipText(Language.get("general.tooltip.preview_sound"));
		lblPreviewImage = getJLabel(Language.get("general.label.preview_image"));
		lblPreviewImage.setToolTipText(Language.get("general.tooltip.preview_image"));
		lblLoadingImage = getJLabel(Language.get("general.label.loading_image"));
		lblLoadingImage.setToolTipText(Language.get("general.tooltip.loading_image"));
		lblBackgroundImage = getJLabel(Language.get("general.label.background_image"));
		lblBackgroundImage.setToolTipText(Language.get("general.tooltip.background_image"));
		lblResultImage = getJLabel(Language.get("general.label.result_image"));
		lblResultImage.setToolTipText(Language.get("general.tooltip.result_image"));
		
		txtTitle = new JTextField();
		txtTitle.setToolTipText(Language.get("general.tooltip.title"));
		txtArtist = new JTextField();
		txtArtist.setToolTipText(Language.get("general.tooltip.artist"));
		txtGenre = new JTextField();
		txtGenre.setToolTipText(Language.get("general.tooltip.genre"));
		txtComment = new JTextField();
		txtComment.setToolTipText(Language.get("general.tooltip.comment"));
		txtPanel = new JTextField();
		txtPanel.setToolTipText(Language.get("general.tooltip.panel"));
		
		txtBpm = new JTextField();
		spnBpm = new JSpinner(new SpinnerNumberModel(120.0, 0, 9999, 1));
		spnBpm.setToolTipText(Language.get("general.tooltip.bpm"));
		
		lvlDrum = new LevelController();
		lvlDrum.setToolTipText(Language.get("general.tooltip.drum_level"));
		lvlGuitar = new LevelController();
		lvlGuitar.setToolTipText(Language.get("general.tooltip.guitar_level"));
		lvlBass = new LevelController();
		lvlBass.setToolTipText(Language.get("general.tooltip.bass_level"));
		
		chkHiddenLevel = new JCheckBox(Language.get("general.label.hidden_level"));
		chkHiddenLevel.setToolTipText(Language.get("general.tooltip.hidden_level"));
		
		fsPreviewSound = new AudioFileChooser();
		fsPreviewSound.setToolTipText(Language.get("general.tooltip.preview_sound"));
		fsPreviewImage = new ImageFileChooser();
		fsPreviewImage.setToolTipText(Language.get("general.tooltip.preview_image"));
		fsLoadingImage = new ImageFileChooser();
		fsLoadingImage.setToolTipText(Language.get("general.tooltip.loading_image"));
		fsBackgroundImage = new ImageFileChooser();
		fsBackgroundImage.setToolTipText(Language.get("general.tooltip.background_image"));
		fsResultImage = new ImageFileChooser();
		fsResultImage.setToolTipText(Language.get("general.tooltip.result_image"));

		double b = 10;
		double f = TableLayout.FILL;
		double p = TableLayout.PREFERRED;
		double vg = 3;

		double size[][] = {
				{ b, p, b, 70, f, b },
				{ b, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, b }
		};
		
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(lblTitle, "1, 1");
		add(txtTitle, "3, 1, 4, 1");
		add(lblArtist, "1, 3");
		add(txtArtist, "3, 3, 4, 3");
		add(lblGenre, "1, 5");
		add(txtGenre, "3, 5, 4, 5");
		add(lblComment, "1, 7");
		add(txtComment, "3, 7, 4, 7");
		add(lblPanel, "1, 9");
		add(txtPanel, "3, 9, 4, 9");
		
		add(lblBpm, "1, 11");
		add(spnBpm, "3, 11");
		
		add(lblDrumLv, "1, 13");
		add(lvlDrum, "3, 13, 4, 13");
		add(lblGuitarLv, "1, 15");
		add(lvlGuitar, "3, 15, 4, 15");
		add(lblBassLv, "1, 17");
		add(lvlBass, "3, 17, 4, 17");
		add(chkHiddenLevel, "3, 19, 4, 19");
		
		add(lblPreviewSound, "1, 21");
		add(fsPreviewSound, "3, 21, 4, 21");
		add(lblPreviewImage, "1, 23");
		add(fsPreviewImage, "3, 23, 4, 23");
		add(lblLoadingImage, "1, 25");
		add(fsLoadingImage, "3, 25, 4, 25");
		add(lblBackgroundImage, "1, 27");
		add(fsBackgroundImage, "3, 27, 4, 27");
		add(lblResultImage, "1, 29");
		add(fsResultImage, "3, 29, 4, 29");
	}
	
	private JLabel getJLabel(String text)
	{
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		return label;
	}

	@Override
	public void captureData()
	{
		dtx.setTitle(txtTitle.getText().trim());
		dtx.setArtist(txtArtist.getText().trim());
		dtx.setGenre(txtGenre.getText().trim());
		dtx.setComment(txtComment.getText().trim());
		dtx.setPanel(txtPanel.getText().trim());
		
		dtx.setBpm((Double) spnBpm.getValue());
		
		dtx.setDrumLevel(lvlDrum.getValue());
		dtx.setGuitarLevel(lvlGuitar.getValue());
		dtx.setBassLevel(lvlBass.getValue());
		dtx.setHiddenLevel(chkHiddenLevel.isSelected());
		
		dtx.setPreviewSound(fsPreviewSound.getPath());
		dtx.setPreviewImage(fsPreviewImage.getPath());
		dtx.setLoadingImage(fsLoadingImage.getPath());
		dtx.setBackgroundImage(fsBackgroundImage.getPath());
		dtx.setResultImage(fsResultImage.getPath());
	}

	@Override
	protected void fillData()
	{
		txtTitle.setText(dtx.getTitle());
		txtArtist.setText(dtx.getArtist());
		txtGenre.setText(dtx.getGenre());
		txtComment.setText(dtx.getComment());
		txtPanel.setText(dtx.getPanel());
		
		spnBpm.setValue(dtx.getBpm());
		
		lvlDrum.setValue(dtx.getDrumLevel());
		lvlGuitar.setValue(dtx.getGuitarLevel());
		lvlBass.setValue(dtx.getBassLevel());
		chkHiddenLevel.setSelected(dtx.isHiddenLevel());
		
		fsPreviewSound.setPath(dtx.getPreviewSound());
		fsPreviewImage.setPath(dtx.getPreviewImage());
		fsLoadingImage.setPath(dtx.getLoadingImage());
		fsBackgroundImage.setPath(dtx.getBackgroundImage());
		fsResultImage.setPath(dtx.getResultImage());
	}

	@Override
	public void resetData()
	{
		txtTitle.setText("");
		txtArtist.setText("");
		txtGenre.setText("");
		txtComment.setText("");
		txtPanel.setText("");
		
		spnBpm.setValue(120);
		
		lvlDrum.setValue(0);
		lvlGuitar.setValue(0);
		lvlBass.setValue(0);
		chkHiddenLevel.setSelected(false);
		
		fsPreviewSound.setPath("");
		fsPreviewImage.setPath("");
		fsLoadingImage.setPath("");
		fsBackgroundImage.setPath("");
		fsResultImage.setPath("");
	}
}
