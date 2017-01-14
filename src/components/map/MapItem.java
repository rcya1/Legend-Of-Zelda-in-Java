package components.map;

import components.OverWorld;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MapItem
{
	public OverWorld overWorld;

	public int x;
	public int y;

	public int width;
	public int height;

	public BufferedImage bufferedImage;

	public abstract void update();

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(bufferedImage, x - width / 2 - overWorld.getCameraX(),
				y - height / 2 - overWorld.getCameraY(), width, height, null);
	}

	public Rectangle getRectangle()
	{
		return new Rectangle(x, y, width, height);
	}
}
