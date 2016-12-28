package entity.enemies;

import entity.Direction;
import components.OverWorld;
import reference.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

class OctorokPellet
{
	private OverWorld overWorld;

	private int x;
	private int y;

	private int velX;
	private int velY;

	private int width;
	private int height;

	private Direction direction;

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
}
