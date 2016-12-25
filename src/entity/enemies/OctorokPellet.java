package entity.enemies;

import entity.Direction;
import map.TileMap;
import reference.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

class OctorokPellet
{
	private TileMap tileMap;

	private int x;
	private int y;

	private int velX;
	private int velY;

	private int width;
	private int height;

	private Direction direction;

	OctorokPellet(int x, int y, Direction direction, TileMap tileMap)
	{
		this.x = x;
		this.y = y;

		int[] vector = direction.getVector(4);

		velX = vector[0];
		velY = vector[1];

		this.direction = direction;

		this.tileMap = tileMap;

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
		int drawX = x - tileMap.getX();
		int drawY = y - tileMap.getY();

		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), drawX, drawY);
		g2d.drawImage(Images.Enemies.OCTOROK_PELLET, drawX - width / 2, drawY - width / 2, width, height, null);
		g2d.setTransform(transform);
	}
}
