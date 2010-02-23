package com.dtxmaker.jdtxcreator.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;

import com.dtxmaker.jdtxcreator.JDTXCreator;
import com.dtxmaker.jdtxcreator.data.Audio;
import com.dtxmaker.jdtxcreator.data.DTX;
import com.dtxmaker.jdtxcreator.data.Image;
import com.dtxmaker.jdtxcreator.data.Video;

public class DTXWriter
{
	/** New line. */
	private static final String NL = "\r\n";
	
	public static void write(File file, DTX dtx)
	{
		try
		{
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "Shift_JIS");
			
			try
			{
				writer.write("; Created by " + JDTXCreator.PROGRAM_NAME + " " + JDTXCreator.VERSION + newLine(2));

				// TODO: write dtx content
				
				/*
				 * ----------------- BEGIN: writing general data -----------------
				 */
				
				if (dtx.getTitle().length() > 0) writer.write(DTX.TITLE + ": " + dtx.getTitle() + newLine());
				else writer.write(DTX.TITLE + ": (No title)" + newLine());
				if (dtx.getArtist().length() > 0) writer.write(DTX.ARTIST + ": " + dtx.getArtist() + newLine());
				if (dtx.getGenre().length() > 0) writer.write(DTX.GENRE + ": " + dtx.getGenre() + newLine());
				if (dtx.getComment().length() > 0) writer.write(DTX.COMMENT + ": " + dtx.getComment() + newLine());
				if (dtx.getPanel().length() > 0) writer.write(DTX.PANEL + ": " + dtx.getPanel() + newLine());
				
				writer.write(DTX.BPM + ": " + dtx.getBpm() + newLine());
				if (dtx.getPlaySpeed() != 1) writer.write(DTX.DTXVPLAYSPEED + ": " + dtx.getPlaySpeed() + newLine());

				if (dtx.getDrumLevel() != 0) writer.write(DTX.DLEVEL + ": " + dtx.getDrumLevel() + newLine());
				if (dtx.getGuitarLevel() != 0) writer.write(DTX.GLEVEL + ": " + dtx.getGuitarLevel() + newLine());
				if (dtx.getBassLevel() != 0) writer.write(DTX.BLEVEL + ": " + dtx.getBassLevel() + newLine());
				if (dtx.isHiddenLevel()) writer.write(DTX.HIDDENLEVEL + ": ON" + newLine());
				
				if (dtx.getPreviewSound().length() > 0) writer.write(DTX.PREVIEW + ": " + dtx.getPreviewSound() + newLine());
				if (dtx.getPreviewImage().length() > 0) writer.write(DTX.PREIMAGE + ": " + dtx.getPreviewImage() + newLine());
				if (dtx.getPreviewMovie().length() > 0) writer.write(DTX.PREMOVIE + ": " + dtx.getPreviewMovie() + newLine());
				if (dtx.getLoadingImage().length() > 0) writer.write(DTX.STAGEFILE + ": " + dtx.getLoadingImage() + newLine());
				if (dtx.getBackgroundImage().length() > 0) writer.write(DTX.BACKGROUND + ": " + dtx.getBackgroundImage() + newLine());
				
				if (dtx.getResultImage().length() > 0) writer.write(DTX.RESULTIMAGE + ": " + dtx.getResultImage());
				
				writer.write(newLine(2));
				
				/*
				 * ----------------- END: writing general data -----------------
				 */
				
				/*
				 * ----------------- BEGIN: writing audio data -----------------
				 */
				
				{
					Vector<Audio> vector = dtx.getAudios();
					
					if (vector != null)
					{
						for (int i = 0; i < vector.size(); i++)
						{
							Audio audio = vector.get(i);
							writer.write(Audio.WAV + audio.getNumber() + ": " + audio.getPath());
							if (audio.getLabel().length() > 0) writer.write("\t;" + audio.getLabel() + newLine());
							else writer.write(newLine());
							if (audio.isBgm()) writer.write(Audio.BGMWAV + ": " + audio.getNumber() + newLine());
							if (audio.getVolume() != 100) writer.write(Audio.VOLUME + audio.getNumber() + ": " + audio.getVolume() + newLine());
							if (audio.getPosition() != 0) writer.write(Audio.POSITION + audio.getNumber() + ": " + audio.getPosition() + newLine());
							if (audio.getSize() != 100) writer.write(Audio.SIZE + audio.getNumber() + ": " + audio.getSize() + newLine());
						}
						writer.write(newLine(2));
					}
				}
				
				/*
				 * ----------------- END: writing audio data -----------------
				 */
				
				/*
				 * ----------------- BEGIN: writing image data -----------------
				 */
				
				{
					Vector<Image> vector = dtx.getImages();
					
					if (vector != null)
					{
						for (int i = 0; i < vector.size(); i++)
						{
							Image image = vector.get(i);
							if (image.isTexture()) writer.write(Image.BMP + Image.TEX + image.getNumber() + ": " + image.getPath());
							else writer.write(Image.BMP + image.getNumber() + ": " + image.getPath());
							if (image.getLabel().length() > 0) writer.write("\t;" + image.getLabel() + newLine());
							else writer.write(newLine());
						}
						writer.write(newLine(2));
					}
				}
				
				/*
				 * ----------------- END: writing image data -----------------
				 */
				
				/*
				 * ----------------- BEGIN: writing video data -----------------
				 */
				
				{
					Vector<Video> vector = dtx.getVideos();
					
					if (vector != null)
					{
						for (int i = 0; i < vector.size(); i++)
						{
							Video video = vector.get(i);
							writer.write(Video.AVI + video.getNumber() + ": " + video.getPath());
							if (video.getLabel().length() > 0) writer.write("\t;" + video.getLabel() + newLine());
							else writer.write(newLine());
						}
						writer.write(newLine(2));
					}
				}
				
				/*
				 * ----------------- END: writing video data -----------------
				 */
				
				/*
				 * ----------------- BEGIN: writing bpm data -----------------
				 */
				
				// TODO
				writer.write(newLine(2));
				
				/*
				 * ----------------- END: writing bpm data -----------------
				 */
				
				/*
				 * ----------------- BEGIN: writing score data -----------------
				 */
				
				// TODO
				writer.write(newLine(2));
				
				/*
				 * ----------------- END: writing score data -----------------
				 */
				
				writer.write(dtx.getOthers());
			}
			finally
			{
				writer.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a new line string.
	 * @return A string contain new line.
	 */
	private static String newLine()
	{
		return newLine(1);
	}
	
	/**
	 * Create new line string.
	 * @param n number of new line
	 * @return A string contain new line.
	 */
	private static String newLine(int n)
	{
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < n; i++)
		{
			buffer.append(NL);
		}
		
		return buffer.toString();
	}
}
