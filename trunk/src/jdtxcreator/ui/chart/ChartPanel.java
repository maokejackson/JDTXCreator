package jdtxcreator.ui.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jdtxcreator.ui.chart.Lane.TYPE;
import jdtxcreator.ui.main.StatusBar;


public class ChartPanel extends JPanel
{
	private static final long serialVersionUID = 3008335903707360858L;
	
	public static final int LANE_WIDTH = 30;
	public static final int HEADER_HEIGHT = 42;
	
	public static final int MARGIN_4 = 0;
	public static final int MARGIN_8 = 1;
	public static final int MARGIN_16 = 2;
	public static final int MARGIN_24 = 3;
	public static final int MARGIN_32 = 4;
	public static final int MARGIN_48 = 5;
	public static final int MARGIN_64 = 6;
	public static final int MARGIN_FREE = 7;
	
	public static final String[] MARGIN = {
		"1/4", "1/8", "1/16", "1/24", "1/32", "1/48", "1/64", "free"
	};
	
	private static final Color RED = new Color(255, 0, 0, 25);
	private static final Color GREEN = new Color(0, 255, 0, 25);
	private static final Color BLUE = new Color(0, 127, 255, 25);
	private static final Color CYAN = new Color(0, 255, 255, 25);
	private static final Color YELLOW = new Color(255, 255, 0, 25);
	private static final Color PINK = new Color(255, 127, 127, 25);
	private static final Color PURPLE = new Color(255, 0, 255, 25);
	private static final Color GRAY = new Color(160, 160, 160, 25);
	
	// lane
	// BPM | LC HH SN BD HT LT FT CY | FI | BGM AVI | SE1 ~ SE5 | 
	// GtV GtR GtG GtB | GtW | BsV BsR BsG BsB | BsW | SE6 ~ SE15 | BG1 ~ BG5
	
	private static final Lane[] LANES = {
		new Lane(TYPE.BPM, "BPM", GRAY, false, 0x08, 0x03),
		new Lane(TYPE.AUDIO, "LC", CYAN, true, 0x1a, 0x1a),
		new Lane(TYPE.AUDIO, "HH", CYAN, false, 0x11, 0x18),
		new Lane(TYPE.AUDIO, "SD", YELLOW, false, 0x12, 0x12),
		new Lane(TYPE.AUDIO, "BD", PINK, false, 0x13, 0x13),
		new Lane(TYPE.AUDIO, "HT", GREEN, false, 0x20, 0x20),
		new Lane(TYPE.AUDIO, "LT", RED, false, 0x15, 0x15),
		new Lane(TYPE.AUDIO, "FT", PURPLE, false, 0x17, 0x17),
		new Lane(TYPE.AUDIO, "CY", CYAN, false, 0x16, 0x19),
		new Lane(TYPE.FI, "FI", YELLOW, true, 0x53, 0x53),
		new Lane(TYPE.AUDIO, "BGM", GRAY, true, 0x01, 0x01),
		new Lane(TYPE.VIDEO, "AVI", GRAY, false, 0x54, 0x54),
		new Lane(TYPE.AUDIO, "SE1", GRAY, true, 0x61, 0x61),
		new Lane(TYPE.AUDIO, "SE2", GRAY, false, 0x62, 0x62),
		new Lane(TYPE.AUDIO, "SE3", GRAY, false, 0x63, 0x63),
		new Lane(TYPE.AUDIO, "SE4", GRAY, false, 0x64, 0x64),
		new Lane(TYPE.AUDIO, "SE5", GRAY, false, 0x65, 0x65),
		new Lane(TYPE.AUDIO, "SE6", GRAY, false, 0x66, 0x66),
		new Lane(TYPE.AUDIO, "SE7", GRAY, false, 0x67, 0x67),
		new Lane(TYPE.AUDIO, "SE8", GRAY, false, 0x68, 0x68),
		new Lane(TYPE.GtV, "GtV", GRAY, true, 0, 0),
		new Lane(TYPE.GtR, "GtR", RED, false, 0, 0),
		new Lane(TYPE.GtG, "GtG", GREEN, false, 0, 0),
		new Lane(TYPE.GtB, "GtB", BLUE, false, 0, 0),
		new Lane(TYPE.GtW, "GtW", GRAY, true, 0x28, 0x28),
		new Lane(TYPE.BsV, "BsV", GRAY, true, 0, 0),
		new Lane(TYPE.BsR, "BsR", RED, false, 0, 0),
		new Lane(TYPE.BsG, "BsG", GREEN, false, 0, 0),
		new Lane(TYPE.BsB, "BsB", BLUE, false, 0, 0),
		new Lane(TYPE.BsW, "BsW", GRAY, true, 0xa8, 0xa8),
		new Lane(TYPE.AUDIO, "SE9", GRAY, true, 0x69, 0x69),
		new Lane(TYPE.AUDIO, "S10", GRAY, false, 0x6a, 0x6a),
		new Lane(TYPE.AUDIO, "S11", GRAY, false, 0x6b, 0x6b),
		new Lane(TYPE.AUDIO, "S12", GRAY, false, 0x6c, 0x6c),
		new Lane(TYPE.AUDIO, "S13", GRAY, false, 0x6d, 0x6d),
		new Lane(TYPE.AUDIO, "S14", GRAY, false, 0x6e, 0x6e),
		new Lane(TYPE.AUDIO, "S15", GRAY, false, 0x6f, 0x6f),
		new Lane(TYPE.IMAGE, "BG1", GRAY, true, 0x04, 0xc4),
		new Lane(TYPE.IMAGE, "BG2", GRAY, false, 0x07, 0xc7),
		new Lane(TYPE.IMAGE, "BG3", GRAY, false, 0x55, 0xd5),
		new Lane(TYPE.IMAGE, "BG4", GRAY, false, 0x56, 0xd6),
		new Lane(TYPE.IMAGE, "BG5", GRAY, false, 0x57, 0xd7)
	};
	
	private static final int NARROW = 1;
	private static final int WIDE = 2;
	
	private static final Color LINE_COLOR_BRIGHT = Color.WHITE;
	private static final Color LINE_COLOR_NORMAL = new Color(128, 128, 128);
	private static final Color LINE_COLOR_DARK = new Color(50, 50, 50);
	
	private static final Font DEFAULT_CHANNEL_NOTE_FONT = new Font("", Font.PLAIN, 7);
	private static final Font MEASURE_NUMBER_FONT = new Font("", Font.PLAIN, 50);
	
	// 192 notes when part length = 1
	
	JScrollPane scrollPane;
	
	private int margin = 2;

	public ChartPanel()
	{
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
		
		scrollPane = new JScrollPane(this);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
	}
	
	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}
	
	void setMargin(int margin)
	{
		this.margin = margin;
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		int width =  LANE_WIDTH * LANES.length;
		
		return new Dimension(width, 100);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
		
		// background
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// grid line
		g2.setColor(LINE_COLOR_DARK);
		
		// lane separator and color
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		for (int i = 0, x = 0; i < LANES.length; i++)
		{
			Lane lane = LANES[i];
			
			// fill color
			if (lane.background != null)
			{
				g2.setColor(lane.background);
				g2.fillRect(x, 0, LANE_WIDTH, getHeight());
			}
			
			// draw separator
			if (lane.leftSeparatorBold)
			{
				g2.setColor(LINE_COLOR_BRIGHT);
				g2.fillRect(x, 0, WIDE, getHeight());
			}
			else
			{
				g2.setColor(LINE_COLOR_NORMAL);
				g2.drawLine(x, 0, x, getHeight());
			}
			
			x += LANE_WIDTH;
		}
		
		// column header border
		g2.setPaint(new LinearGradientPaint(0, 0, 0, HEADER_HEIGHT,
				new float[] { 0f, 0.25f, 1f },
				new Color[] { new Color(0, 0, 70), new Color(80, 80, 230), new Color(0, 0, 0, 0) }
		));
		Rectangle2D rect = new Rectangle2D.Float(0, 0, getWidth(), HEADER_HEIGHT);
		g2.fill(rect);
		
		// column header text
		FontMetrics fm = g2.getFontMetrics();
		FontRenderContext frc = g2.getFontRenderContext();
		Color textColor = new Color(220, 220, 220);
		Color shadowColor = new Color(0, 0, 0, 200);
		
		for (int i = 0, left = 0; i < LANES.length; i++)
		{
			Lane lane = LANES[i];
			
			int width = fm.stringWidth(lane.name);	// text width
			int height = fm.getHeight();			// text height
			int space = (LANE_WIDTH - width) / 2;
			int x = left + space;					// text position x
			int y = height + 3;						// text position y

			TextLayout textLayout = new TextLayout(lane.name, g2.getFont(), frc);
			AffineTransform at = AffineTransform.getTranslateInstance(x + 2, y + 2);
			Shape outline = textLayout.getOutline(at);
			g2.setPaint(shadowColor);
			g2.fill(outline);
			g2.setPaint(textColor);
			textLayout.draw(g2, x, y);
			
			left += LANE_WIDTH;
		}
		
		g2.dispose();
	}
	
	void zoomIn()
	{
		// TODO: zoom in
	}
	
	void zoomOut()
	{
		// TODO: zoom out
	}
	
	MouseAdapter mouseListener = new MouseAdapter()
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("mouseClicked");
			if (e.isControlDown())
			{
				System.out.println("isControlDown");
			}
		};
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			System.out.println("mousePressed");
		};
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			System.out.println("mouseReleased");
		};
	};
	
	MouseMotionListener mouseMotionListener = new MouseMotionListener()
	{
		@Override
		public void mouseDragged(MouseEvent e)
		{
			StatusBar.getInstance().setPoint2(e.getLocationOnScreen());
		};
		
		@Override
		public void mouseMoved(MouseEvent e)
		{
			StatusBar.getInstance().setPoint1(e.getPoint());
		};
	};
}

