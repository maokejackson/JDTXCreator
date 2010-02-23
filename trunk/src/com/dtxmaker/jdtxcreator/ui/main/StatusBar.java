package com.dtxmaker.jdtxcreator.ui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class StatusBar extends JPanel
{
	private static final long serialVersionUID = -1498488069152843354L;

	private static StatusBar instance;

	public static StatusBar getInstance()
	{
		if (instance == null) instance = new StatusBar();
		return instance;
	}
	
	private StatusBar()
	{
		super(new BorderLayout());
		setPreferredSize(new Dimension(10, 20));
		
		
	}
}
