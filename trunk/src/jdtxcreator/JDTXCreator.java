package jdtxcreator;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jdtxcreator.ui.main.Main;
import jdtxcreator.ui.main.Menu;


public class JDTXCreator extends JFrame
{
	private static final long serialVersionUID = 4648172894076113183L;
	
	public static final String PROGRAM_NAME = "JDTXCreator";
	public static final String VERSION = "0.1";
	public static final String RELEASE_DATE = "20100215";

	public JDTXCreator()
	{
		setTitle(PROGRAM_NAME/* + " " + VERSION + "(" + RELEASE_DATE + ")" */);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				Main.getInstance().exit();
			}
		});
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

		setJMenuBar(Menu.getInstance());
//		getContentPane().add(Main.getInstance());
		setContentPane(Main.getInstance());

		setSize(800, 600);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(new DTXEventQueue());

		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new JDTXCreator();
//				ChartManager.getInstance().newChart();
			}
		});
	}
}
