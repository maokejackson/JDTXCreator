package jdtxcreator.ui.chart;

import java.util.ArrayList;
import java.util.List;

public class Measure
{
	/** The number of notes when part length is 1. */
	public static final int STANDARD_NOTE_COUNT = 192;
	
	int number;				// measure number 0~3599
	double length = 1;		// 
	int zoom = 1;			// 
	List<Note> notes;		// All notes in this measure.
	
	public Measure(int number)
	{
		if (number < 0 || number > 3599)
		{
			String message = "Number must between 0 and 3599. --> [" + number + "]";
			throw new IllegalArgumentException(message);
		}
		this.number = number;
		
		notes = new ArrayList<Note>();
	}
	
}
