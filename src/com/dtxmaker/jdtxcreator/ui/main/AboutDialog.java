package com.dtxmaker.jdtxcreator.ui.main;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dtxmaker.jdtxcreator.JDTXCreator;
import com.dtxmaker.jdtxcreator.Language;
import com.dtxmaker.jdtxcreator.ui.AbstractDialog;

public class AboutDialog extends AbstractDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
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
		setTitle("About");
		setModal(true);
		setResizable(false);
		setLayout(new BorderLayout());
		
		lblProgram = new JLabel(JDTXCreator.PROGRAM_NAME);
		lblVersion = new JLabel(JDTXCreator.VERSION);
		lblRelease = new JLabel(JDTXCreator.RELEASE_DATE);
		lblAuthor = new JLabel("Maoke Jackson");
		lblThanks = new JLabel();
		lblOthers = new JLabel();
		
		double f = TableLayout.FILL;
		double p = TableLayout.PREFERRED;
		double b = 10;
		double vs = 5;
		double[][] size = {
				{ b, p, b, f, b },
				{ b, p, vs, p, vs, p, vs, p, vs, p, vs, p, b }
		};
		
		JPanel aboutPanel = new JPanel(new TableLayout(size));
		aboutPanel.add(new JLabel("Program name:"), "1, 1");
		aboutPanel.add(lblProgram, "3, 1");
		aboutPanel.add(new JLabel("Version:"), "1, 3");
		aboutPanel.add(lblVersion, "3, 3");
		aboutPanel.add(new JLabel("Release date:"), "1, 5");
		aboutPanel.add(lblRelease, "3, 5");
		aboutPanel.add(new JLabel("Author:"), "1, 7");
		aboutPanel.add(lblAuthor, "3, 7");
		aboutPanel.add(new JLabel("Thanks:"), "1, 9");
		aboutPanel.add(lblThanks, "3, 9");
		aboutPanel.add(new JLabel(""), "1, 11");
		aboutPanel.add(lblOthers, "3, 11");
		
		btnOk = new JButton(Language.get("dialog.ok"));
		btnOk.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnOk);
		
		add(aboutPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(350, 200);
		setLocationRelativeTo(Main.getInstance());
	}
	
	@Override
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		setLocationRelativeTo(Main.getInstance());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		
		if (obj == btnOk) setVisible(false);
	}
}
