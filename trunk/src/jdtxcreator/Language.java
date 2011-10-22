package jdtxcreator;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language
{
	private static ResourceBundle resource;

	public static void setLanguage(Locale locale)
	{
		try
		{
			resource = ResourceBundle.getBundle("lang", locale);
		}
		catch (Exception e)
		{
			resource = ResourceBundle.getBundle("lang", Locale.ENGLISH);
		}
	}

	public static String get(String key)
	{
		if (resource == null) setLanguage(Locale.getDefault());

		try
		{
			return new String(resource.getString(key).getBytes("8859_1"), "UTF8");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String get(String key, String variable)
	{
		String text = get(key);
		text = text.replace("{0}", variable);
		return text;
	}
}
