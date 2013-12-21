package jdtxcreator.ui.sidebar;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import jdtxcreator.Language;
import jdtxcreator.ui.AudioFileChooser;
import jdtxcreator.ui.ImageFileChooser;
import jdtxcreator.ui.LevelController;
import net.miginfocom.swing.MigLayout;

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

		setLayout(new MigLayout("wrap 2", "[pref!]10px[min:140,grow]", "[sg]"));

		String label = "align right";
		String field = "growx";

		add(lblTitle, label);
		add(txtTitle, field);

		add(lblArtist, label);
		add(txtArtist, field);

		add(lblGenre, label);
		add(txtGenre, field);

		add(lblComment, label);
		add(txtComment, field);

		add(lblPanel, label);
		add(txtPanel, field);

		add(lblBpm, label);
		add(spnBpm, "width 80!");

		add(lblDrumLv, label);
		add(lvlDrum, field);

		add(lblGuitarLv, label);
		add(lvlGuitar, field);

		add(lblBassLv, label);
		add(lvlBass, field);

		add(chkHiddenLevel, "skip");

		add(lblPreviewSound, label);
		add(fsPreviewSound, field);

		add(lblPreviewImage, label);
		add(fsPreviewImage, field);

		add(lblLoadingImage, label);
		add(fsLoadingImage, field);

		add(lblBackgroundImage, label);
		add(fsBackgroundImage, field);

		add(lblResultImage, label);
		add(fsResultImage, field);
	}

	private JLabel getJLabel(String text)
	{
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		return label;
	}

	@Override
	public void setEnabled(boolean enabled)
	{
//		lblTitle.setEnabled(enabled);
//		lblArtist.setEnabled(enabled);
//		lblGenre.setEnabled(enabled);
//		lblComment.setEnabled(enabled);
//		lblPanel.setEnabled(enabled);
//		lblBpm.setEnabled(enabled);
//		lblDrumLv.setEnabled(enabled);
//		lblGuitarLv.setEnabled(enabled);
//		lblBassLv.setEnabled(enabled);
//		lblPreviewSound.setEnabled(enabled);
//		lblPreviewImage.setEnabled(enabled);
//		lblLoadingImage.setEnabled(enabled);
//		lblBackgroundImage.setEnabled(enabled);
//		lblResultImage.setEnabled(enabled);
		txtTitle.setEnabled(enabled);
		txtArtist.setEnabled(enabled);
		txtGenre.setEnabled(enabled);
		txtComment.setEnabled(enabled);
		txtPanel.setEnabled(enabled);
		txtBpm.setEnabled(enabled);
		spnBpm.setEnabled(enabled);
		lvlDrum.setEnabled(enabled);
		lvlGuitar.setEnabled(enabled);
		lvlBass.setEnabled(enabled);
		chkHiddenLevel.setEnabled(enabled);
		fsPreviewSound.setEnabled(enabled);
		fsPreviewImage.setEnabled(enabled);
		fsLoadingImage.setEnabled(enabled);
		fsBackgroundImage.setEnabled(enabled);
		fsResultImage.setEnabled(enabled);
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
	public void clearData()
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
