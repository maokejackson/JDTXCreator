package com.dtxmaker.jdtxcreator.ui;

import info.clearthought.layout.TableLayout;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class LevelController extends JPanel implements FocusListener, AdjustmentListener, MouseWheelListener
{
	private static final long serialVersionUID = -8031843111773365163L;
	
	NumericTextField textField;
	JScrollBar scrollbar;
	
	final int min, max;
	
	public LevelController()
	{
		this(0, 0, 100);
	}
	
	public LevelController(int value)
	{
		this(value, 0, 100);
	}
	
	public LevelController(int value, int min, int max)
	{
		if (min > max)
		{
			int hold = min;
			min = max;
			max = hold;
		}
		this.min = min;
		this.max = max;
		
		textField = new NumericTextField();
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.addFocusListener(this);
		textField.addMouseWheelListener(this);
		
		scrollbar = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollbar.setMinimum(min);
		scrollbar.setMaximum(max + 10);
		scrollbar.addAdjustmentListener(this);
		
		setValue(value);
		
		double[][] size = {
				{ 40, 2, TableLayout.FILL },
				{ TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL }
		};
		TableLayout layout = new TableLayout(size);
		setLayout(layout);
		
		add(textField, "0, 0, 0, 2");
		add(scrollbar, "2, 1");
	}
	
	public void setToolTipText(String text)
	{
		textField.setToolTipText(text);
		scrollbar.setToolTipText(text);
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		textField.setEnabled(enabled);
		scrollbar.setEnabled(enabled);
	}
	
	public int getValue()
	{
		int value = 0;

		value = textField.getIntValue();

		if (value < min) value = min;
		else if (value > max) value = max;
		
		return value;
	}
	
	public void setValue(int value)
	{
		if (value < min) value = min;
		else if (value > max) value = max;
		
		textField.setValue(value);
		scrollbar.setValue(value);
	}

	@Override
	public void focusGained(FocusEvent e)
	{
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		scrollbar.setValue(getValue());
		textField.setValue(scrollbar.getValue());
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		textField.setValue(scrollbar.getValue());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (!textField.isFocusOwner()) return;
		int value = scrollbar.getValue() - e.getWheelRotation();
		setValue(value);
	}
}
