package entity;

import reference.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

class Sword
{
	private int x;
	private int y;

	private int velX;
	private int velY;

	private int width;
	private int height;

	private int direction;

	Sword(int x, int y, int direction)
	{
		this.x = x;
		this.y = y;

		velX = 0;
		velY = 0;

		this.direction = direction;

		width = 16;
		height = 16;
	}

	void update()
	{
		x += velX;
		y += velY;
	}

	void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		switch(direction)
		{
		case 0:
			g2d.rotate(0, x + width / 2, y + height / 2);
			break;
		case 1:
			g2d.rotate(Math.PI / 2, x + width / 2, y + height / 2);
			break;
		case 2:
			g2d.rotate(Math.PI, x + width / 2, y + height / 2);
			break;
		case 3:
			g2d.rotate(Math.PI * 1.5, x + width / 2, y + height / 2);
			break;
		default:
			break;
		}
		g2d.drawImage(Images.SWORD, x, y, width, height, null);
		g2d.setTransform(transform);
	}

	void setVector(int velX, int velY)
	{
		this.velX = velX;
		this.velY = velY;
	}
}
