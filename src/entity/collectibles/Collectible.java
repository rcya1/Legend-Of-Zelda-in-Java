package entity.collectibles;

import entity.MapObject;

import java.awt.*;

public abstract class Collectible
{
	int x;
	int y;

	int width;
	int height;

	public abstract void update();

	public abstract void draw(Graphics2D g2d);

	public Rectangle getRectangle()
	{
		return new Rectangle(x, y, width, height);
	}
}
