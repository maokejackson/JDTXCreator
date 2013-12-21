package jdtxcreator.ui.sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import jdtxcreator.JDTXCreator;
import jdtxcreator.Language;
import jdtxcreator.ui.EscapableDialog;
import net.miginfocom.swing.MigLayout;

public abstract class AbstractPropertyDialog<E> extends EscapableDialog implements ActionListener
{
	private static final long serialVersionUID = -1578865057331203670L;

	JPanel buttonPanel;
	JButton btnOk, btnCancel;

	E data;
	boolean approve = false;

	public AbstractPropertyDialog()
	{
	    super(JDTXCreator.getInstance());
		setModal(true);
		setResizable(false);

		btnOk = new JButton(Language.get("dialog.ok"));
		btnOk.setMnemonic(KeyEvent.VK_O);
		btnOk.addActionListener(this);
		btnCancel = new JButton(Language.get("dialog.cancel"));
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.addActionListener(this);

		getRootPane().setDefaultButton(btnOk);

		buttonPanel = new JPanel(new MigLayout("insets 5"));
		buttonPanel.add(new JLabel(), "pushx");
		buttonPanel.add(btnOk, "sgx button");
		buttonPanel.add(btnCancel, "sgx button");
		buttonPanel.add(new JSeparator(), "north");
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		if (obj == btnOk) btnOkClicked();
		else if (obj == btnCancel) setVisible(false);
	}
}
