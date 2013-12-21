package jdtxcreator;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import jdtxcreator.ui.main.Main;
import jdtxcreator.ui.main.Menu;
import jdtxcreator.util.Laf;

public class JDTXCreator extends JFrame
{
	private static final long serialVersionUID = 4648172894076113183L;

	public static final String PROGRAM_NAME = "JDTXCreator";
	public static final String VERSION = "0.1";
	public static final String RELEASE_DATE = "20100215";

	private static JDTXCreator instance;

    public static JDTXCreator getInstance()
    {
        if (instance == null) instance = new JDTXCreator();
        return instance;
    }

	private JDTXCreator()
	{
		setTitle(PROGRAM_NAME/* + " " + VERSION + "(" + RELEASE_DATE + ")" */);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			@Override
            public void windowClosing(WindowEvent e)
			{
				Main.getInstance().exit();
			}
		});
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

		setJMenuBar(Menu.getInstance());
		setContentPane(Main.getInstance());

		setSize(800, 600);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(new DTXEventQueue());
		Laf.setNimbusLookAndFeel();

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
            public void run()
			{
				JDTXCreator.getInstance();
//				ChartFrameManager.getInstance().newChart();
			}
		});
	}
}
