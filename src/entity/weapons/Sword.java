package entity.weapons;

import components.OverWorld;
import entity.Direction;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sword extends Weapon
{
	private OverWorld overWorld;

	private int velX;
	private int velY;

	private Direction direction;

	public Sword(int x, int y, Direction direction, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		velX = 0;
		velY = 0;

		this.direction = direction;

		this.overWorld = overWorld;

		width = 16;
		height = 16;

		damage = 2;
	}

	public void update()
	{
		x += velX;
		y += velY;
	}

	public void draw(Graphics2D g2d)
	{
		int drawX = x - overWorld.getCameraX();
		int drawY = y - overWorld.getCameraY();

		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), drawX, drawY);
		g2d.drawImage(Images.Link.SWORD, drawX - width / 2, drawY - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	public void retract()
	{
		int[] vector = direction.getVector(4);

		velX = -vector[0];
		velY = -vector[1];
	}
}
