package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.ui.ToolbarButton;

public class ChartToolbar extends JToolBar implements ActionListener
{
	private static final long serialVersionUID = -2814402558152764306L;

	JButton btnZoomIn, btnZoomOut;
	JComboBox cbxMargin, cbxPlaySpeed;

	ChartPanel chart;
	
	public ChartToolbar(ChartPanel chart)
	{
		this.chart = chart;
		
		setFloatable(false);
		setRollover(true);
		
		btnZoomIn = new ToolbarButton("images/zoom_in.png", Language.get("toolbar.tooltip.zoom_in"));
		btnZoomOut = new ToolbarButton("images/zoom_out.png", Language.get("toolbar.tooltip.zoom_out"));

		btnZoomIn.addActionListener(this);
		btnZoomOut.addActionListener(this);

		cbxMargin = new JComboBox(ChartPanel.MARGIN);
		cbxMargin.setToolTipText(Language.get("toolbar.tooltip.select_margin"));
		cbxMargin.setSelectedIndex(ChartPanel.MARGIN_16);
		cbxMargin.addActionListener(this);
		
		Double[] speed = { 1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3 };
		cbxPlaySpeed = new JComboBox(speed);
//		cbxPlaySpeed.setEditable(true);
		cbxPlaySpeed.setToolTipText(Language.get("toolbar.tooltip.select_play_speed"));

		add(btnZoomIn);
		add(btnZoomOut);
		addSeparator();
		add(new JLabel(" " + Language.get("toolbar.tooltip.margin") + " "));
		add(cbxMargin);
		addSeparator();
		add(new JLabel(" " + Language.get("toolbar.tooltip.play_speed") + " "));
		add(cbxPlaySpeed);
	}
	
	void setMargin(int margin)
	{
		cbxMargin.setSelectedIndex(margin);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		if (obj == btnZoomIn) chart.zoomIn();
		else if (obj == btnZoomOut) chart.zoomOut();
		else if (obj == cbxMargin) chart.setMargin(cbxMargin.getSelectedIndex());
	}
}
