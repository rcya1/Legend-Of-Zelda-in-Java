package utility;

import main.GamePanel;

import java.awt.*;

public class TextHelper
{
	public static void drawCenteredString(String string, int y, Graphics2D g2d)
	{
		for(String line : string.split("-"))
		{
			int stringWidth = (int) g2d.getFontMetrics().getStringBounds(line, g2d).getWidth();
			g2d.drawString(line, (GamePanel.WIDTH - stringWidth) / 2,
					(y += (g2d.getFontMetrics().getHeight() + 2)));
		}
	}
}
