package jdtxcreator.util;

import java.io.File;

public final class Util
{
	private Util()
	{
		// prevent creating instance
	}

	/**
	 * Get the path of a file relative to a directory.
	 * 
	 * @param file target file.
	 * @param dir a directory relative to.
	 * @return The path of a file relative to a directory.
	 */
	public static String getRelativePath(File file, File dir)
	{
		String relative = dir.toURI().relativize(file.toURI()).getPath();
		return relative.replaceAll("/", "\\\\");
	}

}
