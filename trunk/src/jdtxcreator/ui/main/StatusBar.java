package jdtxcreator.ui.main;

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jdtxcreator.ui.SlightBevelBorder;

import net.miginfocom.swing.MigLayout;


public class StatusBar extends JPanel
{
	private static final long serialVersionUID = -1498488069152843354L;

	private static StatusBar instance;

	JLabel lblChannel, lblPart, lblSelected;
	JLabel lblLocation1, lblLocation2;

	public static StatusBar getInstance()
	{
		if (instance == null) instance = new StatusBar();
		return instance;
	}

	private StatusBar()
	{
		lblChannel = new JLabel("Channel");
		lblChannel.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblPart = new JLabel("Part");
		lblPart.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblSelected = new JLabel();
		lblSelected.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblLocation1 = new JLabel();
		lblLocation1.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));
		lblLocation2 = new JLabel();
		lblLocation2.setBorder(new SlightBevelBorder(SlightBevelBorder.LOWERED));

		setLayout(new MigLayout("insets 2", "[]2px[]", "[sg]"));

		int height = lblChannel.getPreferredSize().height;
		String constrain = "width 20%, height " + height;

		add(lblChannel, constrain);
		add(lblPart, constrain);
		add(lblSelected, constrain);
		add(lblLocation1, constrain);
		add(lblLocation2, constrain);
	}

	public void setPoint1(Point point)
	{
		String text = "(x, y) = (" + point.x + ", " + point.y + ")";
		lblLocation1.setText(text);
	}

	public void setPoint2(Point point)
	{
		String text = "(x, y) = (" + point.x + ", " + point.y + ")";
		lblLocation2.setText(text);
	}

	public void setSelectedCount(int count)
	{
		String text = count + " note(s) selected.";
		lblSelected.setText(text);
	}
}
