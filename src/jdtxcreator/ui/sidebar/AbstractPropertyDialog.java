package jdtxcreator.ui.sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import jdtxcreator.Language;
import jdtxcreator.ui.EscapableDialog;


public abstract class AbstractPropertyDialog<E> extends EscapableDialog implements ActionListener
{
	private static final long serialVersionUID = -1578865057331203670L;

	JPanel buttonPanel;
	JButton btnOk, btnCancel, btnApply;

	E data;
	boolean approve = false;

	public AbstractPropertyDialog()
	{
		setModal(true);
		setResizable(false);

		btnOk = new JButton(Language.get("dialog.ok"));
		btnOk.setMnemonic(KeyEvent.VK_O);
		btnOk.addActionListener(this);
		btnCancel = new JButton(Language.get("dialog.cancel"));
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.addActionListener(this);
		btnApply = new JButton(Language.get("dialog.apply"));
		btnApply.setMnemonic(KeyEvent.VK_A);
		btnApply.addActionListener(this);

		getRootPane().setDefaultButton(btnOk);

		buttonPanel = new JPanel();
		buttonPanel.add(btnOk);
		buttonPanel.add(btnCancel);
//		buttonPanel.add(btnApply);
	}

	public boolean isApprove()
	{
		return approve;
	}

	protected abstract void fillData(E data);

	protected abstract void captureData();

	public abstract void resetData();

	public E getData()
	{
		captureData();
		return data;
	}

	public void showDialog(E data)
	{
		this.data = data;
		fillData(data);
		approve = false;
		setVisible(true);
	}

	protected void btnOkClicked()
	{
		approve = true;
		setVisible(false);
	}

	protected void btnApplyClicked()
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		if (obj == btnOk) btnOkClicked();
		else if (obj == btnApply) btnApplyClicked();
		else if (obj == btnCancel) setVisible(false);
	}
}
