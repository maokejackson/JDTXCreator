package jdtxcreator;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import jdtxcreator.ui.main.Menu;

public class Settings
{
	public static final String FILENAME = "default.properties";

	public static final String VERSION = "version";
	public static final String SIDEBAR_POSITION = "sidebar_on_left";
	public static final String DIVIDER_LOCATION = "divider_location";

	public static final String MOST_RECENT_USED = "most_recent_used";

//	public static final Font TABLE_FONT = new Font("SimSun", Font.PLAIN, 11);
	public static final Font TABLE_FONT = new Font("MS Gothic", Font.PLAIN, 12);
//	public static final Font TABLE_FONT = new Font("Miriam Fixed", Font.PLAIN, 11);
//	public static final Font TABLE_FONT = new Font("Courier New", Font.PLAIN, 11);

	private static Properties props = new Properties();

	private static final String separator = ",";

	static
	{
		File file = new File(FILENAME);

		if (!file.exists())
		{
			// create default properties

			setDividerLocation(350);
			setSidebarPosition(true);
//			set(SOUND_LIST + COLUMN_WIDTH, new int[] { 30, 80, 150, 35, 35, 35, 40});
//			set(IMAGE_LIST + COLUMN_WIDTH, new int[] { 30, 80, 150, 35 });
//			set(VIDEO_LIST + COLUMN_WIDTH, new int[] { 30, 80, 150 });
		}
		else
		{
			try
			{
				FileInputStream inputStream = new FileInputStream(file);

				try
				{
					props.load(inputStream);
				}
				finally
				{
					inputStream.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private static String get(String key)
	{
		return props.getProperty(key);
	}

	private static void set(String key, String value)
	{
		props.setProperty(key, value);
	}

	private static void set(String key, int value)
	{
		props.setProperty(key, "" + value);
	}

	private static void set(String key, boolean value)
	{
		props.setProperty(key, "" + value);
	}

	public static void set(String key, int[] data)
	{
		set(key, data.length);

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			set(column, data[i]);
		}
	}

	public static void set(String key, boolean[] data)
	{
		set(key, data.length);

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			set(column, data[i]);
		}
	}

	public static void set(String key, File[] data)
	{
		set(key, data.length);

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			set(column, data[i].getAbsolutePath());
		}
	}

	public static void setIntegerVector(String key, Vector<Integer> data)
	{
		String value = "";
		for (int i = 0; i < data.size(); i++)
		{
			if (data.elementAt(i) == null) value += separator;
			else value += data.elementAt(i) + separator;
		}
		set(key, value.substring(0, value.length() - separator.length()));
	}

	public static void setBooleanVector(String key, Vector<Boolean> data)
	{
		String value = "";
		for (int i = 0; i < data.size(); i++)
		{
			if (data.elementAt(i) == null) value += separator;
			else value += data.elementAt(i) + separator;
		}
		set(key, value.substring(0, value.length() - separator.length()));
	}

	private static int getInteger(String key, int defaultValue)
	{
		String value = get(key);

		if (value != null) defaultValue = Integer.parseInt(value);

		return defaultValue;
	}

	private static boolean getBoolean(String key, boolean defaultValue)
	{
		String value = get(key);

		if (value != null) defaultValue = Boolean.parseBoolean(value);

		return defaultValue;
	}

	public static String[] getStringArray(String key)
	{
		String[] data = new String[getInteger(key, 0)];

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			data[i] = get(column);
		}

		return data;
	}

	public static int[] getIntegerArray(String key)
	{
		int[] data = new int[getInteger(key, 0)];

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			data[i] = getInteger(column, i);
		}

		return data;
	}

	public static int[] getIntegerArray(String key, int defaultValue)
	{
		int[] data = new int[getInteger(key, 0)];

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			data[i] = getInteger(column, defaultValue);
		}

		return data;
	}

	public static boolean[] getBooleanArray(String key, boolean defaultValue)
	{
		boolean[] data = new boolean[getInteger(key, 0)];

		for (int i = 0; i < data.length; i++)
		{
			String column = key + "_" + i;
			data[i] = getBoolean(column, defaultValue);
		}

		return data;
	}

	public static Vector<Integer> getIntegerVector(String key)
	{
		Vector<Integer> vector = new Vector<Integer>();
		String value = get(key);

		if (value == null) return vector;

		String[] values = value.split(separator);

		for (int i = 0; i < values.length; i++)
		{
			try
			{
				int tmp = Integer.parseInt(values[i]);
				vector.add(tmp);
			}
			catch (Exception e)
			{
				vector.add(null);
			}
		}

		return vector;
	}

	public static Vector<Boolean> getBooleanVector(String key)
	{
		Vector<Boolean> vector = new Vector<Boolean>();
		String value = get(key);

		if (value == null) return vector;

		String[] values = value.split(separator);

		for (int i = 0; i < values.length; i++)
		{
			try
			{
				boolean tmp = Boolean.parseBoolean(values[i]);
				vector.add(tmp);
			}
			catch (Exception e)
			{
				vector.add(null);
			}
		}

		return vector;
	}

	public static boolean getSidebarPosition()
	{
		return getBoolean(SIDEBAR_POSITION, true);
	}

	public static void setSidebarPosition(boolean value)
	{
		set(SIDEBAR_POSITION, value);
	}

	public static int getDividerLocation()
	{
		return getInteger(DIVIDER_LOCATION, 350);
	}

	public static void setDividerLocation(int value)
	{
		set(DIVIDER_LOCATION, value);
	}

	public static void store()
	{
		set(MOST_RECENT_USED, Menu.getInstance().getMruFiles());

		try
		{
			File file = new File(FILENAME);

			if (!file.exists())
			{
				file.createNewFile();
			}

			FileOutputStream stream = new FileOutputStream(file);

			try
			{
				props.store(stream, JDTXCreator.PROGRAM_NAME + " properties");
			}
			finally
			{
				stream.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
