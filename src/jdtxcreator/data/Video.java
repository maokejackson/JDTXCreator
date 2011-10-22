package jdtxcreator.data;

public class Video
{
	public static final String AVI = "#AVI";

	private String number;		// #AVIzz <AVI filename>
	private String path;
	private String label;

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}
}
