package jdtxcreator.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jdtxcreator.data.Audio;
import jdtxcreator.data.BPM;
import jdtxcreator.data.DTX;
import jdtxcreator.data.Image;
import jdtxcreator.data.Video;


public class DTXReader
{
	public static DTX read(File file)
	{
		StringBuffer buffer = new StringBuffer();
		DTX dtx = new DTX();
		Hashtable<String, Audio> hashSound = new Hashtable<String, Audio>();
		Hashtable<String, Video> hashVideo = new Hashtable<String, Video>();
		Hashtable<String, Image> hashImage = new Hashtable<String, Image>();
		Hashtable<String, BPM> hashBpm = new Hashtable<String, BPM>();
		
		try
		{
			LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
			
			try
			{
				String line = null;
				
				while ((line = reader.readLine()) != null)
				{
					try
					{
						line = line.trim();
						
						if (line.isEmpty()) continue;			// blank
						if (line.startsWith(";")) continue;		// comment
						
						/*
						 * ----------------- BEGIN: reading general data -----------------
						 */
						
						else if (tagExist(line, DTX.TITLE)) dtx.setTitle(getText(line, DTX.TITLE));
						else if (tagExist(line, DTX.ARTIST)) dtx.setArtist(getText(line, DTX.ARTIST));
						else if (tagExist(line, DTX.COMMENT)) dtx.setComment(getText(line, DTX.COMMENT));
						else if (tagExist(line, DTX.PANEL)) dtx.setPanel(getText(line, DTX.PANEL));
						else if (tagExist(line, DTX.GENRE)) dtx.setGenre(getText(line, DTX.GENRE));
						
						else if (tagExist(line, DTX.BPM)) dtx.setBpm(getDouble(line, DTX.BPM));
						else if (tagExist(line, DTX.DTXVPLAYSPEED)) dtx.setPlaySpeed(getDouble(line, DTX.DTXVPLAYSPEED));
						
						else if (tagExist(line, DTX.DLEVEL)) dtx.setDrumLevel(getInteger(line, DTX.DLEVEL));
						else if (tagExist(line, DTX.GLEVEL)) dtx.setGuitarLevel(getInteger(line, DTX.GLEVEL));
						else if (tagExist(line, DTX.BLEVEL)) dtx.setBassLevel(getInteger(line, DTX.BLEVEL));
						else if (tagExist(line, DTX.HIDDENLEVEL)) dtx.setHiddenLevel(getBoolean(line, DTX.HIDDENLEVEL));
						
						else if (tagExist(line, DTX.PREVIEW)) dtx.setPreviewSound(getText(line, DTX.PREVIEW));
						else if (tagExist(line, DTX.PREIMAGE)) dtx.setPreviewImage(getText(line, DTX.PREIMAGE));
						else if (tagExist(line, DTX.PREMOVIE)) dtx.setPreviewMovie(getText(line, DTX.PREMOVIE));
						else if (tagExist(line, DTX.STAGEFILE)) dtx.setLoadingImage(getText(line, DTX.STAGEFILE));
						else if (tagExist(line, DTX.BACKGROUND)) dtx.setBackgroundImage(getText(line, DTX.BACKGROUND));
						else if (tagExist(line, DTX.RESULTIMAGE)) dtx.setResultImage(getText(line, DTX.RESULTIMAGE));
						
						/*
						 * ----------------- END: reading general data -----------------
						 */
						
						
						/*
						 * ----------------- BEGIN: reading sound data -----------------
						 */
						
						else if (tagNumberExist(line, Audio.WAV))
						{
							String number = getNumberInTag(line, Audio.WAV);
							Audio wav = hashSound.get(number);
							
							if (wav == null)
							{
								wav = new Audio();
								wav.setNumber(number);
								hashSound.put(number, wav);
							}
							
							wav.setPath(getText(line, Audio.WAV + number));
							wav.setLabel(getComment(line, Audio.WAV + number));
						}
						else if (tagNumberExist(line, Audio.VOLUME))
						{
							String number = getNumberInTag(line, Audio.VOLUME);
							Audio wav = hashSound.get(number);
							
							if (wav == null)
							{
								wav = new Audio();
								wav.setNumber(number);
								hashSound.put(number, wav);
							}
							
							wav.setVolume(getInteger(line, Audio.VOLUME + number));
						}
						else if (tagNumberExist(line, Audio.POSITION))
						{
							String number = getNumberInTag(line, Audio.POSITION);
							Audio wav = hashSound.get(number);
							
							if (wav == null)
							{
								wav = new Audio();
								wav.setNumber(number);
								hashSound.put(number, wav);
							}
							
							wav.setPosition(getInteger(line, Audio.POSITION + number));
						}
						else if (tagNumberExist(line, Audio.SIZE))
						{
							String number = getNumberInTag(line, Audio.SIZE);
							Audio wav = hashSound.get(number);
							
							if (wav == null)
							{
								wav = new Audio();
								wav.setNumber(number);
								hashSound.put(number, wav);
							}
							
							wav.setSize(getInteger(line, Audio.SIZE + number));
						}
						else if (tagExist(line, Audio.BGMWAV))
						{
							String number = getNumber(line, Audio.BGMWAV);
							Audio wav = hashSound.get(number);
							
							if (wav == null)
							{
								wav = new Audio();
								wav.setNumber(number);
								hashSound.put(number, wav);
							}
							wav.setBgm(true);
						}
						
						/*
						 * ----------------- END: reading sound data -----------------
						 */
						
						
						/*
						 * ----------------- BEGIN: reading video data -----------------
						 */
						
						else if (tagNumberExist(line, Video.AVI))
						{
							String number = getNumberInTag(line, Video.AVI);
							Video avi = hashVideo.get(number);
							
							if (avi == null)
							{
								avi = new Video();
								avi.setNumber(number);
								hashVideo.put(number, avi);
							}
							
							avi.setPath(getText(line, Audio.WAV + number));
							avi.setLabel(getComment(line, Audio.WAV + number));
						}
						
						/*
						 * ----------------- END: reading video data -----------------
						 */
						
						
						/*
						 * ----------------- BEGIN: reading image data -----------------
						 */
						
						else if (tagNumberExist(line, Image.BMP))
						{
							String number = getNumberInTag(line, Image.BMP);
							Image bmp = hashImage.get(number);
							
							if (bmp == null)
							{
								bmp = new Image();
								bmp.setNumber(number);
								hashImage.put(number, bmp);
							}
							
							bmp.setPath(getText(line, Image.BMP + number));
							bmp.setLabel(getComment(line, Image.BMP + number));
						}
						else if (tagNumberExist(line, Image.BMP + Image.TEX))
						{
							String number = getNumberInTag(line, Image.BMP + Image.TEX);
							Image bmp = hashImage.get(number);
							
							if (bmp == null)
							{
								bmp = new Image();
								bmp.setNumber(number);
								hashImage.put(number, bmp);
							}
							
							bmp.setPath(getText(line, Image.BMP + Image.TEX + number));
							bmp.setLabel(getComment(line, Image.BMP + Image.TEX + number));
							bmp.setTexture(true);
						}
						
						/*
						 * ----------------- END: reading image data -----------------
						 */
						
						
						/*
						 * ----------------- BEGIN: reading bpm data -----------------
						 */
						
						else if (tagNumberExist(line, BPM.BPM))
						{
							String number = getNumberInTag(line, BPM.BPM);
							BPM bmp = hashBpm.get(number);
							
							if (bmp == null)
							{
								bmp = new BPM();
								bmp.setNumber(number);
								hashBpm.put(number, bmp);
							}
							
							bmp.setBpm(getInteger(line, Audio.WAV + number));
						}
						
						/*
						 * ----------------- END: reading bpm data -----------------
						 */
						
						// TODO: read bpm data
						
						/*
						 * ----------------- BEGIN: reading score data -----------------
						 */
						
						// TODO: read score data
						else if (partNumberExist(line))
						{
							String part = getPart(line);
							int channel = getChannelInTag(line, "#" + part);
							
							
						}
						
						/*
						 * ----------------- END: reading score data -----------------
						 */
						
						
						/*
						 * ----------------- BEGIN: reading others data -----------------
						 */
						
						else if (line.startsWith("#"))
						{
							buffer.append(line + "\n");
						}
						
						/*
						 * ----------------- END: reading others data -----------------
						 */
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				
				{
					Enumeration<Audio> elements = hashSound.elements();
					Vector<Audio> vector = new Vector<Audio>();
					
					while (elements.hasMoreElements())
					{
						Audio wav = (Audio) elements.nextElement();
						vector.add(wav);
					}
					
					dtx.setAudios(vector);
				}
				
				{
					Enumeration<Video> elements = hashVideo.elements();
					Vector<Video> vector = new Vector<Video>();
					
					while (elements.hasMoreElements())
					{
						Video avi = (Video) elements.nextElement();
						vector.add(avi);
					}
					
					dtx.setVideos(vector);
				}

				{
					Enumeration<Image> elements = hashImage.elements();
					Vector<Image> vector = new Vector<Image>();
					
					while (elements.hasMoreElements())
					{
						Image bmp = (Image) elements.nextElement();
						vector.add(bmp);
					}
					
					dtx.setImages(vector);
				}

				{
					Enumeration<BPM> elements = hashBpm.elements();
					Vector<BPM> vector = new Vector<BPM>();
					
					while (elements.hasMoreElements())
					{
						BPM bmp = (BPM) elements.nextElement();
						vector.add(bmp);
					}
					
					dtx.setBPMs(vector);
				}
				
				dtx.setOthers(buffer.toString());
			}
			finally
			{
				reader.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return dtx;
	}
	
	private static boolean tagExist(String line, String tag)
	{
		String regex = "^" + tag + "( |:)( )?(\\w|\\S| |\t)+";
		return line.matches(regex);
	}
	
	private static boolean tagNumberExist(String line, String tag)
	{
		String regex = "^" + tag + "[\\w]{2,2}( |:)( )?(\\w|\\S| |\\t)+";
		return line.matches(regex);
	}
	
	private static boolean partNumberExist(String line)
	{
		String regex = "^#[\\d]{3,3}[\\w]{2,2}( |:)( )?(\\w|\\S| |\\t)+";
		return line.matches(regex);
	}
	
	private static String getNumberInTag(String line, String tag)
	{
		return line.substring(tag.length(), tag.length() + 2);
	}
	
	private static int getChannelInTag(String line, String tag)
	{
		String text = getNumberInTag(line, tag);
		return Integer.parseInt(text, 16);
	}
	
	private static String getPart(String line)
	{
		return line.substring(1, 4);
	}
	
	private static String getString(String line, String tag)
	{
		return line.substring(tag.length() + 1).trim();
	}
	
	/**
	 * Get inline text.
	 * @param line a line of text.
	 * @param tag the tag(always start with '#').
	 * @return inline text.
	 */
	private static String getText(String line, String tag)
	{
		line = getString(line, tag);
		int index = line.indexOf(';');
		String text = line.intern();
		
		if (index != -1)	// comment exist
		{
			text = line.substring(0, index).trim();
		}
		
		return text;
	}
	
	/**
	 * Get inline comment.
	 * @param line a line of text.
	 * @param tag the tag(always start with '#').
	 * @return inline comment.
	 */
	private static String getComment(String line, String tag)
	{
		line = getString(line, tag);
		int index = line.indexOf(';');
		String comment = "";
		
		if (index != -1)	// comment exist
		{
			comment = line.substring(index + 1).trim();
		}
		
		return comment;
	}
	
	private static String getNumber(String line, String tag)
	{
		line = getString(line, tag);
		String number = "";
		
		if (line.length() >= 2)
		{
			number = line.substring(0, 2);
		}
		
		return number;
	}
	
	private static boolean getBoolean(String line, String tag)
	{
		String text = getText(line, tag).toUpperCase();
		
		if (text.equals("ON")) return true;
		return false;
	}
	
	private static int getInteger(String line, String tag)
	{
		return (int) getDouble(line, tag);
	}
	
	private static double getDouble(String line, String tag)
	{
		double value = 0;

		String text = getText(line, tag).trim();

		try
		{
			value = Double.parseDouble(text);
		}
		catch (Exception e)
		{
			return 0;
		}

		return value;
	}
	
	private static int convertNumberToInt(String number)
	{
		return Integer.parseInt(number, 36);
	}
	
	private static int convertChannelToInt(String channel)
	{
		return Integer.parseInt(channel, 16);
	}
}
