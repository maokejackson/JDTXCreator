package jdtxcreator.util;

import javax.swing.UIManager;

public class Laf
{
    private Laf()
    {
        throw new Error(getClass() + " has static methods only.");
    }

    /**
     * Loads the LookAndFeel specified by the given class name.
     *
     * @param className a string specifying the name of the class that
     *            implements the look and feel
     * @return true when successfully set the look and feel.
     */
    public static boolean setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setSystemLookAndFeel()
    {
        return setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    public static boolean setNimbusLookAndFeel()
    {
        return setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    }
}
