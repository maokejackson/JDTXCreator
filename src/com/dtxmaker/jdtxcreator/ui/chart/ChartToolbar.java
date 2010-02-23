package com.dtxmaker.jdtxcreator.ui.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.dtxmaker.jdtxcreator.ui.ToolbarButton;

public class ChartToolbar extends JToolBar implements ActionListener
{
	private static final long serialVersionUID = -2814402558152764306L;

	JButton btnZoomIn, btnZoomOut;
	JComboBox cbxMargin, cbxPlaySpeed;

	ChartPane chart;
	
	public ChartToolbar(ChartPane chart)
	{
		this.chart = chart;
		
		setFloatable(false);
		setRollover(true);
		
		btnZoomIn = new ToolbarButton("images/zoom_in.png", "Zoom in");
		btnZoomOut = new ToolbarButton("images/zoom_out.png", "Zoom out");

		btnZoomIn.addActionListener(this);
		btnZoomOut.addActionListener(this);

		String[] margin = { "1/4", "1/8", "1/16", "1/24", "1/32", "1/48", "1/64", "free" };
		cbxMargin = new JComboBox(margin);
		cbxMargin.setToolTipText("Grid margin");
		cbxMargin.setSelectedIndex(2);
		cbxMargin.addActionListener(this);
		
		Double[] speed = { 1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3 };
		cbxPlaySpeed = new JComboBox(speed);
//		cbxPlaySpeed.setEditable(true);
		cbxPlaySpeed.setToolTipText("Play speed (#DTXVPLAYSPEED)");

		add(btnZoomIn);
		add(btnZoomOut);
		addSeparator();
		add(new JLabel(" Margin: "));
		add(cbxMargin);
		addSeparator();
		add(new JLabel(" Play speed: "));
		add(cbxPlaySpeed);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		if (obj == btnZoomIn) chart.zoomIn();
		else if (obj == btnZoomOut) chart.zoomOut();
		else if (obj == cbxMargin) ;
	}
}
