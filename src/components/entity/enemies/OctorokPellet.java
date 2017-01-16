package components.entity.enemies;

import components.OverWorld;
import components.entity.Direction;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

class OctorokPellet
{
	private final OverWorld overWorld;

	private double x;
	private double y;

	private final double velX;
	private final double velY;

	private final int width;
	private final int height;

	private final Direction direction;

	private final int damage;

	OctorokPellet(double x, double y, Direction direction, OverWorld overWorld)
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
		int drawX = (int) x - overWorld.getCameraX();
		int drawY = (int) y - overWorld.getCameraY();

		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), drawX, drawY);
		g2d.drawImage(Images.Enemies.OCTOROK_PELLET, drawX - width / 2, drawY - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	Rectangle getRectangle()
	{
		return new Rectangle((int) x - width / 2, (int) y - height / 2, width, height);
	}

	int getDamage()
	{
		return damage;
	}
}
