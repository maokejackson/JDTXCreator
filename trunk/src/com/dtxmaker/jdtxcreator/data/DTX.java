package com.dtxmaker.jdtxcreator.data;

import java.util.Vector;

public class DTX
{
	public static final String TITLE = "#TITLE";
	public static final String ARTIST = "#ARTIST";
	public static final String COMMENT = "#COMMENT";
	public static final String PANEL = "#PANEL";
	public static final String GENRE = "#GENRE";
	
	public static final String BPM = "#BPM";
	public static final String DTXVPLAYSPEED = "#DTXVPLAYSPEED";

	public static final String DLEVEL = "#DLEVEL";
	public static final String GLEVEL = "#GLEVEL";
	public static final String BLEVEL = "#BLEVEL";
	public static final String HIDDENLEVEL = "#HIDDENLEVEL";

	public static final String PREVIEW = "#PREVIEW";
	public static final String PREIMAGE = "#PREIMAGE";
	public static final String PREMOVIE = "#PREMOVIE";
	public static final String STAGEFILE = "#STAGEFILE";
	public static final String BACKGROUND = "#BACKGROUND";
	public static final String BACKGROUND_GR = "#BACKGROUND_GR";

	public static final String RESULTIMAGE = "#RESULTIMAGE";
	public static final String RESULTIMAGE_SS = "#RESULTIMAGE_SS";
	public static final String RESULTIMAGE_S = "#RESULTIMAGE_S";
	public static final String RESULTIMAGE_A = "#RESULTIMAGE_A";
	public static final String RESULTIMAGE_B = "#RESULTIMAGE_B";
	public static final String RESULTIMAGE_C = "#RESULTIMAGE_C";
	public static final String RESULTIMAGE_D = "#RESULTIMAGE_D";
	
	public static final String RESULTMOVIE = "#RESULTMOVIE";
	public static final String RESULTMOVIE_SS = "#RESULTMOVIE_SS";
	public static final String RESULTMOVIE_S = "#RESULTMOVIE_A";
	public static final String RESULTMOVIE_A = "#RESULTMOVIE_B";
	public static final String RESULTMOVIE_B = "#RESULTMOVIE_C";
	public static final String RESULTMOVIE_C = "#RESULTMOVIE_D";
	public static final String RESULTMOVIE_D = "#RESULTMOVIE_E";
	
	public static final String RESULTSOUND = "#RESULTSOUND";
	public static final String RESULTSOUND_SS = "#RESULTSOUND_SS";
	public static final String RESULTSOUND_S = "#RESULTSOUND_S";
	public static final String RESULTSOUND_A = "#RESULTSOUND_A";
	public static final String RESULTSOUND_B = "#RESULTSOUND_B";
	public static final String RESULTSOUND_C = "#RESULTSOUND_C";
	public static final String RESULTSOUND_D = "#RESULTSOUND_D";

	public static final String SOUND_NOWLOADING = "#SOUND_NOWLOADING";
	public static final String SOUND_STAGEFAILED = "#SOUND_STAGEFAILED";
	public static final String SOUND_FULLCOMBO = "#SOUND_FULLCOMBO";
	
//	private File file;
//	private String pathname;
//	private String workingDir;
	
	private String title = "";		// #TITLE <song title>
	private String artist = "";		// #ARTIST <artist name>
	private String genre = "";		// #GENRE <genre name>
	private String comment = "";	// #COMMENT <comments of yours>
	private String panel = "";		// #PANEL <comments on playing screen>
	
	private double bpm = 120;		// #BPM <the value of BPM>
	private double playSpeed = 1;	// #DTXVPLAYSPEED <play speed>
	
	private int drumLevel = 0;		// #DLEVEL <level value>
	private int guitarLevel = 0;	// #GLEVEL <level value>
	private int bassLevel = 0;		// #BLEVEL <level value>
	private boolean hiddenLevel;	// #HIDDENLEVEL ON (DTXMania Release 065b070610 or later)
	
	private String previewSound = "";			// #PREVIEW <preview sound filename>
	private String previewImage = "";			// #PREIMAGE <preview image filename>
	private String previewMovie = "";			// #PREMOVIE <preview movie filename> (DTXMania059 or later)
	private String loadingImage = "";			// #STAGEFILE <now loading image filename>
	private String backgroundImage = "";		// #BACKGROUND <waillpaper filename>
	private String backgroundImageGuitar = "";	// #BACKGROUND_GR <waillpaper filename for Guitar Revolution mode> (DTXMania Release 063b060518 or later)
	
	private String resultImage = "";	// #RESULTIMAGE <result screen image filename> (DTXMania Release 049a or later)
	private String resultImageSS;		// #RESULTIMAGE_xx <result screen image filename> (DTXMania Release 049a or later)
	private String resultImageS;
	private String resultImageA;
	private String resultImageB;
	private String resultImageC;
	private String resultImageD;
	
	private String resultMovie;			// #RESULTMOVIE <result movie filename> (DTXMania Release 059 or later)
	private String resultMovieSS;		// #RESULTMOVIE_xx <result movie filename> (DTXMania Release 059 or later)
	private String resultMovieS;
	private String resultMovieA;
	private String resultMovieB;
	private String resultMovieC;
	private String resultMovieD;
	
	private String resultSound;			// #RESULTSOUND <result screen sound filename> (DTXMania Release 065b070610 or later)
	private String resultSoundSS;		// #RESULTSOUND_xx <result screen sound filename> (DTXMania Release 065b070610 or later)
	private String resultSoundS;
	private String resultSoundA;
	private String resultSoundB;
	private String resultSoundC;
	private String resultSoundD;
	
	private String loadingSound;		// #SOUND_NOWLOADING <nowloading sound filename> (Release 068 (080511) or later)
	private String stageFailedSound;	// #SOUND_STAGEFAILED <stage failed sound filename> (Release 068 (080511) or later)
	private String FullComboSound;		// #SOUND_FULLCOMBO <full combo sound filename> (Release 068 (080511) or later
	
	private Vector<Audio> audios;
	private Vector<Image> images;
	private Vector<Video> videos;
	private Vector<BPM> BPMs;
	
	private String others = "";

//	public File getFile()
//	{
//		return file;
//	}
//
//	public void setFile(File file)
//	{
//		this.file = file;
//	}
//
//	public String getPathname()
//	{
//		return pathname;
//	}
//
//	public void setPathname(String pathname)
//	{
//		this.pathname = pathname;
//	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getPanel()
	{
		return panel;
	}

	public void setPanel(String panel)
	{
		this.panel = panel;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public double getBpm()
	{
		return bpm;
	}

	public void setBpm(double bpm)
	{
		this.bpm = bpm;
	}

	public double getPlaySpeed()
	{
		return playSpeed;
	}

	public void setPlaySpeed(double playSpeed)
	{
		this.playSpeed = playSpeed;
	}

	public int getDrumLevel()
	{
		return drumLevel;
	}

	public void setDrumLevel(int drumLevel)
	{
		this.drumLevel = drumLevel;
	}

	public int getGuitarLevel()
	{
		return guitarLevel;
	}

	public void setGuitarLevel(int guitarLevel)
	{
		this.guitarLevel = guitarLevel;
	}

	public int getBassLevel()
	{
		return bassLevel;
	}

	public void setBassLevel(int bassLevel)
	{
		this.bassLevel = bassLevel;
	}

	public boolean isHiddenLevel()
	{
		return hiddenLevel;
	}

	public void setHiddenLevel(boolean hiddenLevel)
	{
		this.hiddenLevel = hiddenLevel;
	}

	public String getPreviewSound()
	{
		return previewSound;
	}

	public void setPreviewSound(String previewSound)
	{
		this.previewSound = previewSound;
	}

	public String getPreviewImage()
	{
		return previewImage;
	}

	public void setPreviewImage(String previewImage)
	{
		this.previewImage = previewImage;
	}

	public String getPreviewMovie()
	{
		return previewMovie;
	}

	public void setPreviewMovie(String previewMovie)
	{
		this.previewMovie = previewMovie;
	}

	public String getLoadingImage()
	{
		return loadingImage;
	}

	public void setLoadingImage(String loadingImage)
	{
		this.loadingImage = loadingImage;
	}

	public String getBackgroundImage()
	{
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage)
	{
		this.backgroundImage = backgroundImage;
	}

	public String getResultImage()
	{
		return resultImage;
	}

	public void setResultImage(String resultImage)
	{
		this.resultImage = resultImage;
	}

	public Vector<Audio> getAudios()
	{
		return audios;
	}

	public void setAudios(Vector<Audio> sounds)
	{
		this.audios = sounds;
	}

	public Vector<Image> getImages()
	{
		return images;
	}

	public void setImages(Vector<Image> images)
	{
		this.images = images;
	}

	public Vector<Video> getVideos()
	{
		return videos;
	}

	public void setVideos(Vector<Video> videos)
	{
		this.videos = videos;
	}

	public String getOthers()
	{
		return others;
	}

	public void setOthers(String others)
	{
		this.others = others;
	}

	public Vector<BPM> getBPMs()
	{
		return BPMs;
	}

	public void setBPMs(Vector<BPM> BPMs)
	{
		this.BPMs = BPMs;
	}
}
