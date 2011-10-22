package jdtxcreator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdtxcreator.Language;

import net.miginfocom.swing.MigLayout;


public abstract class AbstractFileChooser extends JPanel
{
	private static final long serialVersionUID = -8647764400859557480L;

	JTextField textField;
	JButton button;

	public AbstractFileChooser()
	{
		textField = new JTextField();

		button = new JButton("...");
		button.setToolTipText(Language.get("filechooser.select_file"));
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				openFileChooser();
			}
		});

		setLayout(new MigLayout("insets 0", "[min:pref,grow]rel[pref!]"));
		add(textField, "growx");
		add(button);
	}

	@Override
    public void setToolTipText(String text)
	{
		textField.setToolTipText(text);
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		textField.setEnabled(enabled);
		button.setEnabled(enabled);
	}

	public String getPath()
	{
		return textField.getText();
	}

	public void setPath(String text)
	{
		textField.setText(text);
	}

	public abstract void openFileChooser();
}
