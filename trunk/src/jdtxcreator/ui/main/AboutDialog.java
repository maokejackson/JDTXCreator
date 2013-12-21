package jdtxcreator.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import jdtxcreator.JDTXCreator;
import jdtxcreator.Language;
import net.miginfocom.swing.MigLayout;

public class AboutDialog extends JWindow implements ActionListener
{
    private static final long serialVersionUID = -8325526947422284817L;

    private static AboutDialog instance;

	JLabel lblProgram, lblVersion, lblRelease, lblAuthor, lblThanks, lblOthers;
	JButton btnOk;

	public static AboutDialog getInstance()
	{
		if (instance == null) instance = new AboutDialog();
		return instance;
	}

	private AboutDialog()
	{
		lblProgram = new JLabel(JDTXCreator.PROGRAM_NAME);
		lblVersion = new JLabel(JDTXCreator.VERSION);
		lblRelease = new JLabel(JDTXCreator.RELEASE_DATE);
		lblAuthor = new JLabel("Maoke Jackson");
		lblThanks = new JLabel();
		lblOthers = new JLabel();

		btnOk = new JButton(Language.get("dialog.ok"));
		btnOk.addActionListener(this);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());

        setLayout(new MigLayout("insets dialog,wrap 2", "[pref!]10px[min:140,grow]"));

        String label = "sg 1";
        String field = "growx, sg 1";

		add(new JLabel("Program name:"), label);
		add(lblProgram, field);

		add(new JLabel("Version:"), label);
		add(lblVersion, field);

		add(new JLabel("Release date:"), label);
		add(lblRelease, field);

		add(new JLabel("Author:"), label);
		add(lblAuthor, field);

		add(new JLabel("Thanks:"), label);
		add(lblThanks, field);

		add(new JLabel(""), label);
		add(lblOthers, field);

        add(new JLabel(), "span, split 3, growx, pushx");
        add(btnOk, "width pref!");
        add(new JLabel(), "growx, pushx");

        pack();
//		setSize(350, 200);
		setLocationRelativeTo(Main.getInstance());
	}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
    }
}
