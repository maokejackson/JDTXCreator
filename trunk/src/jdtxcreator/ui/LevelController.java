package jdtxcreator.ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class LevelController extends JPanel implements FocusListener, AdjustmentListener, MouseWheelListener
{
	private static final long serialVersionUID = -8031843111773365163L;

	NumericTextField textField;
	JScrollBar scrollbar;

	int min = 0;
	int max = 100;

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
		textField = new NumericTextField();
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.addFocusListener(this);
		textField.addMouseWheelListener(this);

		scrollbar = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollbar.addAdjustmentListener(this);

        setLayout(new MigLayout("insets 0", "[40!]rel[min:pref,grow]"));
        add(textField, "width 40!");
        add(scrollbar, "growx");

        setMinimum(min);
        setMaximum(max);
		setValue(value);
	}

	public int getMinimum()
	{
	    return min;
	}

	public void setMinimum(int min)
    {
	    this.min = Math.min(min, max);
	    scrollbar.setMinimum(this.min);
    }

    public int getMaximum()
    {
        return max;
    }

    public void setMaximum(int max)
    {
        this.max = Math.max(min, max);
        scrollbar.setMaximum(this.max + 10);
    }

	@Override
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
		int value = textField.getIntValue();

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
