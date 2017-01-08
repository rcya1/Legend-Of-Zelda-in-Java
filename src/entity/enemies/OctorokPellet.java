package entity.enemies;

import components.OverWorld;
import entity.Direction;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

class OctorokPellet
{
	private final OverWorld overWorld;

	private int x;
	private int y;

	private final int velX;
	private final int velY;

	private final int width;
	private final int height;

	private final Direction direction;

	private final int damage;

	OctorokPellet(int x, int y, Direction direction, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		int[] vector = direction.getVector(4);

		velX = vector[0];
		velY = vector[1];

		this.direction = direction;

		this.overWorld = overWorld;

		width = 16;
		height = 16;

		damage = 4;
	}

	void update()
	{
		x += velX;
		y += velY;
	}

	void draw(Graphics2D g2d)
	{
		int drawX = x - overWorld.getCameraX();
		int drawY = y - overWorld.getCameraY();

		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), drawX, drawY);
		g2d.drawImage(Images.Enemies.OCTOROK_PELLET, drawX - width / 2, drawY - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	Rectangle getRectangle()
	{
		return new Rectangle(x - width / 2, y - height / 2, width, height);
	}

	int getDamage()
	{
		return damage;
	}
}
