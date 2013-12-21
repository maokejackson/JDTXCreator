package jdtxcreator.ui.sidebar;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jdtxcreator.Language;
import jdtxcreator.data.Audio;
import jdtxcreator.ui.AudioFileChooser;
import jdtxcreator.ui.LevelController;
import net.miginfocom.swing.MigLayout;

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

		setLayout(new MigLayout("insets dialog,wrap 2", "[pref!]10px[min:140,grow]", "[sg]"));

		String label = "align right";
        String field = "growx";

		add(lblNumber, label);
		add(txtNumber, field);

		add(lblLabel, label);
		add(txtLabel, field);

		add(lblFile, label);
		add(fsFile, field);

		add(chkBgm, "skip");

		add(lblVolume, label);
		add(lvlVolume, field);

		add(lblPosition, label);
		add(lvlPosition, field);

		add(lblSize, label);
		add(lvlSize, field);

		add(buttonPanel, "south");

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
