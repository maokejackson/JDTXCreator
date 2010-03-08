package com.dtxmaker.jdtxcreator.ui;
/*===============================================================================
 * SlightBevelBorder.java
 *===============================================================================
 * auth:     Jason
 * CSDN ID:  Unagain
 * Email:    tl21cen@hotmail.com
 * date:    2006-4-11
 *===============================================================================
 * from http://blog.csdn.net/UnAgain/archive/2006/04.aspx
 *===============================================================================
 */

import java.awt.Color;

import java.awt.Component;

import java.awt.Graphics;

import javax.swing.border.SoftBevelBorder;

public class SlightBevelBorder extends SoftBevelBorder
{

	private static final long serialVersionUID = 1L;

	public SlightBevelBorder(int bevelType)
	{
		super(bevelType);
	}

	public SlightBevelBorder(int bevelType, Color highlight, Color shadow)
	{
		super(bevelType, highlight, shadow);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		Color oldColor = g.getColor();
		g.translate(x, y);

		if (bevelType == RAISED)
		{
			g.setColor(getHighlightOuterColor(c));
			g.drawLine(0, 0, width - 2, 0);
			g.drawLine(0, 1, 0, height - 2);
			g.setColor(getShadowOuterColor(c));
			g.drawLine(0, height - 1, width - 1, height - 1);
			g.drawLine(width - 1, 0, width - 1, height - 1);
		}
		else if (bevelType == LOWERED)
		{
			g.setColor(getShadowOuterColor(c));
			g.drawLine(0, 0, width - 2, 0);
			g.drawLine(0, 0, 0, height - 2);
			g.setColor(getHighlightOuterColor(c));
			g.drawLine(0, height - 1, width - 1, height - 1);
			g.drawLine(width - 1, 0, width - 1, height);
		}

		g.translate(-x, -y);
		g.setColor(oldColor);
	}
}
