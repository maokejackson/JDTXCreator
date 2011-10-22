package jdtxcreator.ui.sidebar;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FreeTab extends AbstractTab
{
	private static final long serialVersionUID = -5258420043002038310L;
	
	private static FreeTab instance;
	
	JTextArea textArea;

	public static FreeTab getInstance()
	{
		if (instance == null) instance = new FreeTab();
		return instance;
	}
	
	private FreeTab()
	{
		setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		textArea.setEditable(enabled);
	}

	@Override
	public void captureData()
	{
		dtx.setOthers(textArea.getText());
	}

	@Override
	protected void fillData()
	{
		textArea.setText(dtx.getOthers());
	}

	@Override
	public void clearData()
	{
		textArea.setText("");
	}
}
