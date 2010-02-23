package com.dtxmaker.jdtxcreator.ui.sidebar;

import info.clearthought.layout.TableLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

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
	AudioFileChooser fsPreviewSound;
	ImageFileChooser fsPreviewImage, fsLoadingImage, fsBackgroundImage, fsResultImage;

	public static GeneralTab getInstance()
	{
		if (instance == null) instance = new GeneralTab();
		return instance;
	}
	
	private GeneralTab()
	{
		lblTitle = getJLabel("Title");
		lblTitle.setToolTipText("Song title");
		lblArtist = getJLabel("Artist");
		lblArtist.setToolTipText("Artist name");
		lblGenre = getJLabel("Genre");
		lblGenre.setToolTipText("Song genre");
		lblComment = getJLabel("Comment");
		lblComment.setToolTipText("Comment");
		lblPanel = getJLabel("Panel Message");
		lblPanel.setToolTipText("Panel message show in playing screen");
		
		lblBpm = getJLabel("BPM");
		lblBpm.setToolTipText("Song's BPM (Beats per Minute)");
		lblDrumLv = getJLabel("Drum Level");
		lblDrumLv.setToolTipText("<html>Drums level, (easy) 1 to 100 (hart).<br>0 means no drums.</html>");
		lblGuitarLv = getJLabel("Guitar Level");
		lblGuitarLv.setToolTipText("<html>Guitar level, (easy) 1 to 100 (hart).<br>0 means no guitar.</html>");
		lblBassLv = getJLabel("Bass Level");
		lblBassLv.setToolTipText("<html>Bass level, (easy) 1 to 100 (hart).<br>0 means no bass.</html>");
		
		lblPreviewSound = getJLabel("Preview Sound");
		lblPreviewSound.setToolTipText("Sound that plays when a song is selected.");
		lblPreviewImage = getJLabel("Preview Image");
		lblPreviewImage.setToolTipText("<html>Image that show when a song is selected.<br>The image size is 204x269.</html>");
		lblLoadingImage = getJLabel("Loading Image");
		lblLoadingImage.setToolTipText("<html>Background image that show in loading screen.<br>The image size is 640x480.</html>");
		lblBackgroundImage = getJLabel("Background Image");
		lblBackgroundImage.setToolTipText("<html>Background image that show in playing screen.<br>The image size is 640x480.</html>");
		lblResultImage = getJLabel("Result Image");
		lblResultImage.setToolTipText("<html>Image that show in result screen.<br>The image size is 204x269.</html>");
		
		txtTitle = new JTextField();
		txtTitle.setToolTipText("Song title");
		txtArtist = new JTextField();
		txtArtist.setToolTipText("Artist name");
		txtGenre = new JTextField();
		txtGenre.setToolTipText("Song genre");
		txtComment = new JTextField();
		txtComment.setToolTipText("Comment");
		txtPanel = new JTextField();
		txtPanel.setToolTipText("Panel message show in playing screen");
		
		txtBpm = new JTextField();
		spnBpm = new JSpinner(new SpinnerNumberModel(120.0, 0, 1000, 1));
		spnBpm.setToolTipText("Song's BPM (Beats per Minute)");
		
		lvlDrum = new LevelController(50);
		lvlDrum.setToolTipText("<html>Drums level, (easy) 1 to 100 (hart).<br>0 means no drums.</html>");
		lvlGuitar = new LevelController();
		lvlGuitar.setToolTipText("<html>Guitar level, (easy) 1 to 100 (hart).<br>0 means no guitar.</html>");
		lvlBass = new LevelController();
		lvlBass.setToolTipText("<html>Bass level, (easy) 1 to 100 (hart).<br>0 means no bass.</html>");
		
		fsPreviewSound = new AudioFileChooser();
		fsPreviewSound.setToolTipText("Sound that plays when a song is selected.");
		fsPreviewImage = new ImageFileChooser();
		fsPreviewImage.setToolTipText("<html>Image that show when a song is selected.<br>The image size is 204x269.</html>");
		fsLoadingImage = new ImageFileChooser();
		fsLoadingImage.setToolTipText("<html>Background image that show in loading screen.<br>The image size is 640x480.</html>");
		fsBackgroundImage = new ImageFileChooser();
		fsBackgroundImage.setToolTipText("<html>Background image that show in playing screen.<br>The image size is 640x480.</html>");
		fsResultImage = new ImageFileChooser();
		fsResultImage.setToolTipText("<html>Image that show in result screen.<br>The image size is 204x269.</html>");

		double b = 10;
		double f = TableLayout.FILL;
		double p = TableLayout.PREFERRED;
		double vg = 3;

		double size[][] = {
				{ b, p, b, 70, f, b },
				{ b, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, vg, p, b }
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
		
		add(lblPreviewSound, "1, 19");
		add(fsPreviewSound, "3, 19, 4, 19");
		add(lblPreviewImage, "1, 21");
		add(fsPreviewImage, "3, 21, 4, 21");
		add(lblLoadingImage, "1, 23");
		add(fsLoadingImage, "3, 23, 4, 23");
		add(lblBackgroundImage, "1, 25");
		add(fsBackgroundImage, "3, 25, 4, 25");
		add(lblResultImage, "1, 27");
		add(fsResultImage, "3, 27, 4, 27");
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
		
		fsPreviewSound.setPath("");
		fsPreviewImage.setPath("");
		fsLoadingImage.setPath("");
		fsBackgroundImage.setPath("");
		fsResultImage.setPath("");
	}
}
