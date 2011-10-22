package jdtxcreator.data;

public class BPM
{
	public static final String BPM = "#BPM";

	private String number;		// #BPMzz <BPM value>
	private double bpm;

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public double getBpm()
	{
		return bpm;
	}

	public void setBpm(double bpm)
	{
		this.bpm = bpm;
	}
}
