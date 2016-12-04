package entity.enemies;

import entity.Direction;
import reference.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

class OctorokPellet
{
	private int x;
	private int y;

	private int velX;
	private int velY;

	private int width;
	private int height;

	private Direction direction;

	OctorokPellet(int x, int y, Direction direction)
	{
		this.x = x;
		this.y = y;

		int[] vector = direction.getVector(4);

		velX = vector[0];
		velY = vector[1];

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
		g2d.rotate(direction.getRadians(), x, y);
		g2d.drawImage(Images.Enemies.OCTOROK_PELLET, x - width / 2, y - width / 2, width, height, null);
		g2d.setTransform(transform);
	}
}
